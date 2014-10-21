package com.shiftapp.ws.messaging;

import com.shiftapp.ws.model.classes.User;

public interface IMessagingService {
	
	public boolean sendMessgae(User user, String messageBody);
	public boolean sendVerificationMessgae(User user);

}
