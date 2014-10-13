package com.shiftapp.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MissingShift {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column (name="shift_id")
	private long shiftId;
	
	@Column (name="employee_id")
	private long employeeId;
	
	@Column (name="reason")
	private String reason;
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public MissingShift() {}

	public MissingShift(long shiftId, long employeeId, String reason,
			RequestStatusEnum requestStatus) {
		super();
		this.shiftId = shiftId;
		this.employeeId = employeeId;
		this.reason = reason;
		this.requestStatus = requestStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getShiftId() {
		return shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getReason() {
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
