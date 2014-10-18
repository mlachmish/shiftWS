package com.shiftapp.ws.service;

public interface ISignUpService {

	public boolean lookup(String countryCode, String phoneNumber);
	public boolean signup(String firstName, String lastName, String countryCode, String phoneNumber, String pic);
	public boolean login(String countryCode, String phoneNumber, String password);
}
