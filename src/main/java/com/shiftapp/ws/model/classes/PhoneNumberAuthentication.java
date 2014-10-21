package com.shiftapp.ws.model.classes;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

@Entity
public class PhoneNumberAuthentication {
	
	private static Random rand = new Random();

	@Id
	@GenericGenerator(name = "generator", strategy = "foreign", 
	parameters = @Parameter(name = "property", value = "user"))
	@GeneratedValue(generator = "generator")
	@Column (name="number_authentication_id")
	private long numberAuthenticationId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;
	
	@Column (name="authentication_code")
	private String authenticationCode;
	
	@Type(type = "yes_no")
	@Column (name="is_authenticaed")
	private boolean isAuthenticated;
	
	public PhoneNumberAuthentication() {}

	public PhoneNumberAuthentication(User user, boolean isAuthenticated) {
		super();
		this.user = user;
		this.authenticationCode = Integer.toString(rand.nextInt(10000));
		this.isAuthenticated = isAuthenticated;
	}

	public long getNumberAuthenticationId() {
		return numberAuthenticationId;
	}

	public void setNumberAuthenticationId(long numberAuthenticationId) {
		this.numberAuthenticationId = numberAuthenticationId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuthenticationCode() {
		return authenticationCode;
	}

	public void setAuthenticationCode(String authenticationCode) {
		this.authenticationCode = authenticationCode;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

}