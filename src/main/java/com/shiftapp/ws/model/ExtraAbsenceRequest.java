package com.shiftapp.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExtraAbsenceRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column (name="employee_id")
	private long employeeId;
	
	@Column (name="extra_amount")
	private int extraAmount;
	
	@Column (name="reason")
	private String reason;
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public ExtraAbsenceRequest() {}

	public ExtraAbsenceRequest(long employeeId, int extraAmount,
			String reason, RequestStatusEnum requestStatus) {
		super();
		this.employeeId = employeeId;
		this.extraAmount = extraAmount;
		this.reason = reason;
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

	public int isExtraAmount() {
		return extraAmount;
	}

	public void setExtraAmount(int extraAmount) {
		this.extraAmount = extraAmount;
	}

	public String isReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public RequestStatusEnum getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatusEnum requestStatus) {
		this.requestStatus = requestStatus;
	}
}
