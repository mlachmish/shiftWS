package com.shiftapp.ws.service.identification;

import java.io.InputStream;

import com.shiftapp.ws.model.classes.User;
import com.sun.jersey.core.header.FormDataContentDisposition;

public interface IIdentificationService {

	public User lookup(String countryCode, String phoneNumber);
	public boolean loginAStep(String countryCode, String phoneNumber);
	public boolean loginBStep(String countryCode, String phoneNumber, String password);
	public boolean signup(String firstName, String lastName, String countryCode, String phoneNumber, InputStream uploadedPicInputStream, FormDataContentDisposition picFileDetail);

}
