package com.shiftapp.ws.messaging;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.shiftapp.ws.model.classes.User;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class SMSMessagingService implements IMessagingService{

	private static final Logger logger = Logger.getLogger(SMSMessagingService.class);

	@Autowired
	private TwilioRestClient client;
	
	private boolean isSMSEnabled;
	
	private String fromNumber; 
	
	public static final String VERIFICATION_MESSAGE_BODY = "Welcome to shift! Here is your verification code: "; 
	
	public SMSMessagingService() {
		super();
	}

	@Override
	public boolean sendMessgae(User user, String messageBody) {

		// Build the parameters 
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
		String fullPhoneNumber = user.getCountryCode() + user.getPhoneNumber();
		params.add(new BasicNameValuePair("To", fullPhoneNumber)); 
		params.add(new BasicNameValuePair("From", fromNumber)); 
		params.add(new BasicNameValuePair("Body", messageBody));   

		MessageFactory messageFactory = client.getAccount().getMessageFactory(); 
		Message message;
		if (isSMSEnabled) {
		try {
			message = messageFactory.create(params);
		} catch (TwilioRestException e) {
			logger.error("Unable to send SMS to: " + fullPhoneNumber + "with params: " + params);
			e.printStackTrace();
			return false;
		} 
		logger.info("SMS message was sent to: " + fullPhoneNumber +  ". message SID: " + message.getSid());
		} else {
			logger.debug("SMS service is off! SMS message was supposed to be sent to: " + fullPhoneNumber +  ". message body: " + messageBody);
		}
		return true;
	}

	@Override
	public boolean sendVerificationMessgae(User user) {
		String messageBody = VERIFICATION_MESSAGE_BODY + user.getPhoneNumberAuthentication().getAuthenticationCode();
		boolean messageSentSuccesfuly = sendMessgae(user, messageBody);
		return messageSentSuccesfuly;
	}

	public String getFromNumber() {
		return fromNumber;
	}

	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}

	public boolean isSMSEnabled() {
		return isSMSEnabled;
	}

	public void setSMSEnabled(boolean isSMSEnabled) {
		this.isSMSEnabled = isSMSEnabled;
	}

}
