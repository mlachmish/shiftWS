package com.shiftapp.ws.authentication;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.shiftapp.ws.exceptions.TokenOutOfDateException;
import com.shiftapp.ws.exceptions.UnauthorizedTokenException;
import com.shiftapp.ws.model.classes.User;

public class Authenticator implements IAuthenticator{
	
	private static final Logger logger = Logger.getLogger(Authenticator.class);

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	@Transactional
	public User authenticateToken(String access_token) throws UnauthorizedTokenException, TokenOutOfDateException{
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("accessToken", access_token));
		@SuppressWarnings("unchecked")
		List<User> results = cr.list();

		if (results.size() == 1) {
			User user = results.iterator().next();
			logger.debug("User: " + user + " authenticated succecfully");
			return user;
		}
		logger.warn("Unautorized request by access_token: " + access_token);
		throw new UnauthorizedTokenException();
	}

}
