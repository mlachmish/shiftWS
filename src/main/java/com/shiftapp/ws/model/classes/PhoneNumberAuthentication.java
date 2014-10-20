package com.shiftapp.ws.model.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class PhoneNumberAuthentication {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="number_authentication_id")
	private long numberAuthenticationId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;
	
	@Column (name="authentication_code")
	private String authenticationCode;
	
	public PhoneNumberAuthentication() {}

	public PhoneNumberAuthentication(User user, String authenticationCode) {
		super();
		this.user = user;
		this.authenticationCode = authenticationCode;
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
}