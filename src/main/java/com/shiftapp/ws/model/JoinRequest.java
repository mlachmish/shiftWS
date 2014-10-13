package com.shiftapp.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JoinRequest {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column (name="employee_id")
	private long employeeId;
	
	@Column (name="business_id")
	private long businessId;
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public JoinRequest() {}

	public JoinRequest(long employeeId, long businessId,
			RequestStatusEnum requestStatus) {
		super();
		this.employeeId = employeeId;
		this.businessId = businessId;
		this.requestStatus = requestStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public RequestStatusEnum getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatusEnum requestStatus) {
		this.requestStatus = requestStatus;
	}
}
