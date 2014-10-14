package com.shiftapp.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NumberAuthentication {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column (name="country_code")
	private String countryCode;
	
	@Column (name="phone_number")
	private String phoneNumber;
	
	@Column (name="authentication_code")
	private String authenticationCode;
	
	public NumberAuthentication() {}

	public NumberAuthentication(String countryCode, String phoneNumber,
			String authenticationCode) {
		super();
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.authenticationCode = authenticationCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAuthenticationCode() {
		return authenticationCode;
	}

	public void setAuthenticationCode(String authenticationCode) {
		this.authenticationCode = authenticationCode;
	}
}