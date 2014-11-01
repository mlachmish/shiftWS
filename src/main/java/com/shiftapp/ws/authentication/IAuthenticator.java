package com.shiftapp.ws.authentication;

import com.shiftapp.ws.exceptions.TokenOutOfDateException;
import com.shiftapp.ws.exceptions.UnauthorizedTokenException;
import com.shiftapp.ws.model.classes.User;

public interface IAuthenticator {
	
	public User authenticateToken(String access_token) throws UnauthorizedTokenException, TokenOutOfDateException;

}
