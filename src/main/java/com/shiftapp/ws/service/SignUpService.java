package com.shiftapp.ws.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shiftapp.ws.model.NumberAuthentication;
import com.shiftapp.ws.model.User;

public class SignUpService implements ISignUpService{

	private static final Logger logger = LogManager.getLogger(SignUpService.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly=true)
	public boolean lookup(String countryCode, String phoneNumber) {
		logger.info("Testing logger!");
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
			//TODO: log user already exist
			logger.error("Cannot signup: User already exist with phone :" + countryCode + phoneNumber);
			return false;
		}

		User newUser = new User(firstName, lastName, countryCode, phoneNumber, pic);
		Session session = sessionFactory.getCurrentSession();
		session.persist(newUser);
		return true;
	}

	@Override
	@Transactional(readOnly=true)
	public boolean login(String countryCode, String phoneNumber, String password) {
		if (!lookup(countryCode, phoneNumber)) {
			//TODO: log user already exist
			logger.error("Cannot login: User does not exist with phone :" + countryCode + phoneNumber);
			return false;
		}
		
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(NumberAuthentication.class);
		cr.add(Restrictions.eq("countryCode", countryCode));
		cr.add(Restrictions.eq("phoneNumber", phoneNumber));
		List<NumberAuthentication> userPass = cr.list();
		
		if (userPass.size() != 1) {
			logger.error("Cannot login: User dont have a genatrated password for Phone number: "+ countryCode + phoneNumber);
			return false;
		}
		
		NumberAuthentication numberAuthentication = userPass.iterator().next();
			return numberAuthentication.getAuthenticationCode().equals(password);
	}

}
