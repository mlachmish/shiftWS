package com.shiftapp.ws.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shiftapp.ws.model.classes.PhoneNumberAuthentication;
import com.shiftapp.ws.model.classes.User;

public class SignUpService implements ISignUpService{

	private static final Logger logger = Logger.getLogger(SignUpService.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly=true)
	public boolean lookup(String countryCode, String phoneNumber) {
		List<User> results = null;

		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("countryCode", countryCode));
		cr.add(Restrictions.eq("phoneNumber", phoneNumber));
		results = cr.list();

		if (results.size() == 1) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public boolean signup(String firstName, String lastName, String countryCode, String phoneNumber, String pic) {
		if (lookup(countryCode, phoneNumber)) {
			logger.error("Cannot signup: User already exist with phone :" + countryCode + phoneNumber);
			return false;
		}

//		User newUser = new User(firstName, lastName, countryCode, phoneNumber, pic);
//		Session session = sessionFactory.getCurrentSession();
//		session.persist(newUser);
		return true;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean login(String countryCode, String phoneNumber, String password) {
		if (!lookup(countryCode, phoneNumber)) {
			logger.error("Cannot login: User does not exist with phone :" + countryCode + phoneNumber);
			return false;
		}
		
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(PhoneNumberAuthentication.class);
		cr.add(Restrictions.eq("countryCode", countryCode));
		cr.add(Restrictions.eq("phoneNumber", phoneNumber));
		List<PhoneNumberAuthentication> userPass = cr.list();
		
		if (userPass.size() != 1) {
			logger.error("Cannot login: User dont have a genatrated password for Phone number: "+ countryCode + phoneNumber);
			return false;
		}
		
		PhoneNumberAuthentication numberAuthentication = userPass.iterator().next();
			return numberAuthentication.getAuthenticationCode().equals(password);
	}

}
