package com.shiftapp.ws.model.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.shiftapp.ws.model.enums.RequestStatusEnum;

@Entity
public class JoinRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="join_request_id")
	private long joinRequestId;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id")
	private Business business;
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public JoinRequest() {}

	public JoinRequest(User user, Business business,
			RequestStatusEnum requestStatus) {
		super();
		this.user = user;
		this.business = business;
		this.requestStatus = requestStatus;
	}

	public long getJoinRequestId() {
		return joinRequestId;
	}

	public void setJoinRequestId(long joinRequestId) {
		this.joinRequestId = joinRequestId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public RequestStatusEnum getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatusEnum requestStatus) {
		this.requestStatus = requestStatus;
	}

}
