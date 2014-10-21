package com.shiftapp.ws.model.classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.shiftapp.ws.model.enums.CountryCodeEnum;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="user_id")
	private long userId;
	
	@Column (name="first_name")
	private String firstName;
	
	@Column (name="last_name")
	private String lastName;
	
	@Enumerated (EnumType.STRING)
	@Column (name="country_code")
	private CountryCodeEnum countryCode;
	
	@Column (name="phone_number")
	private String phoneNumber;
	
	@Column (name="pic")
	private String pic;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<BusinessEmployee> businessEmployees;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "promotingUser")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<BusinessEmployee> promotedBusinessEmployees;
	
	@Column (name="fb_access_token")
	private String fbAccessToken;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private PhoneNumberAuthentication phoneNumberAuthentication;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private JoinRequest joinRequest;
	
	public User() {}

	public User(String firstName, String lastName, CountryCodeEnum countryCode,
			String phoneNumber, String pic,
			List<BusinessEmployee> businessEmployees,
			List<BusinessEmployee> promotedBusinessEmployees,
			String fbAccessToken,
			PhoneNumberAuthentication phoneNumberAuthentication,
			JoinRequest joinRequest) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.pic = pic;
		this.businessEmployees = businessEmployees;
		this.promotedBusinessEmployees = promotedBusinessEmployees;
		this.fbAccessToken = fbAccessToken;
		this.phoneNumberAuthentication = phoneNumberAuthentication;
		this.joinRequest = joinRequest;
	}

	public User(String firstName, String lastName, CountryCodeEnum countryCode,
			String phoneNumber, String pic) {
		this(firstName, lastName, countryCode, phoneNumber, pic, null, null, null, null, null);
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CountryCodeEnum getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(CountryCodeEnum countryCode) {
		this.countryCode = countryCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public List<BusinessEmployee> getBusinessEmployees() {
		return businessEmployees;
	}

	public void setBusinessEmployees(List<BusinessEmployee> businessEmployees) {
		this.businessEmployees = businessEmployees;
	}

	public List<BusinessEmployee> getPromotedBusinessEmployees() {
		return promotedBusinessEmployees;
	}

	public void setPromotedBusinessEmployees(
			List<BusinessEmployee> promotedBusinessEmployees) {
		this.promotedBusinessEmployees = promotedBusinessEmployees;
	}

	public String getFbAccessToken() {
		return fbAccessToken;
	}

	public void setFbAccessToken(String fbAccessToken) {
		this.fbAccessToken = fbAccessToken;
	}

	public PhoneNumberAuthentication getPhoneNumberAuthentication() {
		return phoneNumberAuthentication;
	}

	public void setPhoneNumberAuthentication(
			PhoneNumberAuthentication phoneNumberAuthentication) {
		this.phoneNumberAuthentication = phoneNumberAuthentication;
	}

	public JoinRequest getJoinRequest() {
		return joinRequest;
	}

	public void setJoinRequest(JoinRequest joinRequest) {
		this.joinRequest = joinRequest;
	}

}
