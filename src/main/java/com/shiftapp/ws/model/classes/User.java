package com.shiftapp.ws.model.classes;

import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.shiftapp.ws.model.enums.CountryCodeEnum;

/**
 * Represent a user object. Every user have exactly one {@link User} object in the model.
 * @author Matan Lachmish
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "country_code", "phone_number" }),
							 @UniqueConstraint( columnNames = { "access_token" })})
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
	
	@Column (name="fb_access_token")
	private String fbAccessToken;
	
	@Column (name="access_token")
	private String accessToken;
	
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
			String fbAccessToken,
			String accessToken,
			PhoneNumberAuthentication phoneNumberAuthentication,
			JoinRequest joinRequest) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
		this.pic = pic;
		this.businessEmployees = businessEmployees;
		this.fbAccessToken = fbAccessToken;
		this.accessToken =accessToken;
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
		if (businessEmployees == null) {
			businessEmployees = new ArrayList<BusinessEmployee>();
		}
		return businessEmployees;
	}

	public void setBusinessEmployees(List<BusinessEmployee> businessEmployees) {
		this.businessEmployees = businessEmployees;
	}

	public void addBusinessEmployee (BusinessEmployee businessEmployee) {
		this.getBusinessEmployees().add(businessEmployee);
		businessEmployee.setUser(this);
	}
	
	public void removeBusinessEmployee (BusinessEmployee businessEmployee) {
		this.getBusinessEmployees().remove(businessEmployee);
		businessEmployee.setUser(null);
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
		if (phoneNumberAuthentication.getUser() != this) {
			phoneNumberAuthentication.setUser(this);
		}
	}

	public JoinRequest getJoinRequest() {
		return joinRequest;
	}

	public void setJoinRequest(JoinRequest joinRequest) {
		this.joinRequest = joinRequest;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId="
				+ userId
				+ ", "
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (countryCode != null ? "countryCode=" + countryCode + ", "
						: "")
				+ (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", "
						: "")
				+ (pic != null ? "pic=" + pic + ", " : "")
				+ (businessEmployees != null ? "businessEmployees="
						+ businessEmployees + ", " : "")
				+ (fbAccessToken != null ? "fbAccessToken=" + fbAccessToken
						+ ", " : "")
				+ (accessToken != null ? "accessToken=" + accessToken + ", "
						: "")
				+ (phoneNumberAuthentication != null ? "phoneNumberAuthentication="
						+ phoneNumberAuthentication + ", "
						: "")
				+ (joinRequest != null ? "joinRequest=" + joinRequest : "")
				+ "]";
	}

}
