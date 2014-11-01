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

/**
 * When a {@link User} try to sign up or login a {@link PhoneNumberAuthentication} will be created with a generated one time pass. 
 * @author Matan Lachmish
 */
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
	
	public PhoneNumberAuthentication() {
		super();
		this.authenticationCode = Integer.toString(rand.nextInt(10000));
		this.isAuthenticated = false;
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
		if (user.getPhoneNumberAuthentication() != this) {
			user.setPhoneNumberAuthentication(this);
		}
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
	
	public void regenrate() {
		this.authenticationCode = Integer.toString(rand.nextInt(10000));
		this.isAuthenticated = false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (numberAuthenticationId ^ (numberAuthenticationId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneNumberAuthentication other = (PhoneNumberAuthentication) obj;
		if (numberAuthenticationId != other.numberAuthenticationId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PhoneNumberAuthentication [numberAuthenticationId="
				+ numberAuthenticationId
				+ ", "
				+ (user != null ? "user=" + user + ", " : "")
				+ (authenticationCode != null ? "authenticationCode="
						+ authenticationCode + ", " : "") + "isAuthenticated="
				+ isAuthenticated + "]";
	}

}