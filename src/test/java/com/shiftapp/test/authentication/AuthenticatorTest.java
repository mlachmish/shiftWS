package com.shiftapp.test.authentication;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.shiftapp.ws.authentication.IAuthenticator;
import com.shiftapp.ws.exceptions.TokenOutOfDateException;
import com.shiftapp.ws.exceptions.UnauthorizedTokenException;
import com.shiftapp.ws.model.classes.User;
import com.shiftapp.ws.model.enums.CountryCodeEnum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config.xml",
									"classpath:spring-config-test.xml"})
public class AuthenticatorTest {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private IAuthenticator authenticator;
	
	@Test
	@Transactional
	public void successfulAuthenticationTest() throws UnauthorizedTokenException, TokenOutOfDateException {
		Session session = sessionFactory.getCurrentSession();
		String accessToken = "abcd1234";
		User user = new User("Jhon", "Dho", CountryCodeEnum.getInstance("+972"), "1234567", null, null, null, accessToken, null, null);
		session.save(user);

		User authenticatedUser = authenticator.authenticateToken(accessToken);
		Assert.assertEquals(user, authenticatedUser);
	}
	
	@Test (expected=UnauthorizedTokenException.class)
	@Transactional
	public void unauthorizedAuthenticationTest() throws UnauthorizedTokenException, TokenOutOfDateException {
		Session session = sessionFactory.getCurrentSession();
		String accessToken = "abcd1234";
		User user = new User("Jhon", "Dho", CountryCodeEnum.getInstance("+972"), "1234567", null, null, null, accessToken, null, null);
		session.save(user);

		String unauthorizedAccessToken = "4321";
		User authenticatedUser = authenticator.authenticateToken(unauthorizedAccessToken);
		Assert.assertEquals(user, authenticatedUser);
	}

}
