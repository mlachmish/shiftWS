package com.shiftapp.ws.service.identification;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shiftapp.ws.messaging.IMessagingService;
import com.shiftapp.ws.model.classes.PhoneNumberAuthentication;
import com.shiftapp.ws.model.classes.User;
import com.shiftapp.ws.model.enums.CountryCodeEnum;
import com.sun.jersey.core.header.FormDataContentDisposition;

public class IdentificationService implements IIdentificationService{

	private static final Logger logger = Logger.getLogger(IdentificationService.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private IMessagingService messagingService;
	
	private static String userPicPath;
	
	@Override
	@Transactional(readOnly=true)
	public User lookup(String countryCode, String phoneNumber) {
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("countryCode", CountryCodeEnum.getInstance(countryCode)));
		cr.add(Restrictions.eq("phoneNumber", phoneNumber));
		@SuppressWarnings("unchecked")
		List<User> results = cr.list();

		if (results.size() == 1) {
			return results.iterator().next();
		}
		return null;
	}

	@Override
	@Transactional
	public boolean signup(String firstName, String lastName, String countryCode, String phoneNumber, InputStream uploadedPicInputStream, FormDataContentDisposition picFileDetail) {
		if (lookup(countryCode, phoneNumber) != null) {
			logger.error("Cannot signup: User already exist with phone :" + countryCode + phoneNumber);
			return false;
		}
		String picPath = saveUserPicToFile(uploadedPicInputStream, countryCode + phoneNumber);
		User newUser = new User(firstName, lastName, CountryCodeEnum.getInstance(countryCode), phoneNumber, picPath);
		PhoneNumberAuthentication phoneNumberAuthentication = new PhoneNumberAuthentication();
		newUser.setPhoneNumberAuthentication(phoneNumberAuthentication);
		
		sendAuthenticationCodeUsingSecondFactor(newUser);
		
		Session session = sessionFactory.getCurrentSession();
		session.persist(newUser);
		return true;
	}

	@Override
	@Transactional
	public boolean loginAStep(String countryCode, String phoneNumber) {
		User user = lookup(countryCode, phoneNumber);
		if (user == null) {
			logger.error("Cannot login: User does not exist with phone :" + countryCode + phoneNumber);
			return false;
		}
		
		logger.debug("Generating a new authentication code for number: " + countryCode + phoneNumber);
		PhoneNumberAuthentication numberAuthentication = user.getPhoneNumberAuthentication();
		numberAuthentication.regenrate();
		sendAuthenticationCodeUsingSecondFactor(user);
		Session session = sessionFactory.getCurrentSession();
		session.persist(user);
		return true;
	}
	
	@Override
	@Transactional
	public boolean loginBStep(String countryCode, String phoneNumber, String password) {
		User user = lookup(countryCode, phoneNumber);
		if (user == null) {
			logger.error("Cannot login: User does not exist with phone :" + countryCode + phoneNumber);
			return false;
		}
		PhoneNumberAuthentication numberAuthentication = user.getPhoneNumberAuthentication();
		if (numberAuthentication.isAuthenticated()) {
			logger.warn("Second factor authentication code already been in use for user: " + user);
			return false;
		}
		boolean passCheck = numberAuthentication.getAuthenticationCode().equals(password);
		if (!passCheck) {
			logger.warn("Authentication failed for phone number: " + countryCode + phoneNumber + " (entered pass: " + password + ")");
		} else {
			logger.debug(countryCode + phoneNumber + " authenticated successfuly");
			numberAuthentication.setAuthenticated(true);
			Session session = sessionFactory.getCurrentSession();
			session.persist(user);
		}
			return passCheck;
	}
	
	private void sendAuthenticationCodeUsingSecondFactor(User user) {
		messagingService.sendVerificationMessgae(user);
	}
	
	private String saveUserPicToFile(InputStream uploadedInputStream, String fileName) {
		if (uploadedInputStream == null) {
			return null;
		}
		
		String uploadedFileLocation = getUserPicPath() + fileName + ".jpg";
			try {
				OutputStream out = new FileOutputStream(new File(
						uploadedFileLocation ));
				int read = 0;
				byte[] bytes = new byte[1024];
	 
				out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error("Unable to save picture with name: " + fileName);
				e.printStackTrace();
				return null;
			}
			return uploadedFileLocation;
		}

	public static String getUserPicPath() {
		return userPicPath;
	}

	public static void setUserPicPath(String userPicPath) {
		IdentificationService.userPicPath = userPicPath;
	}

}
