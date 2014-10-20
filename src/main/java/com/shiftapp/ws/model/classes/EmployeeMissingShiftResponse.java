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

import com.shiftapp.ws.model.enums.RequestStatusEnum;

@Entity
public class EmployeeMissingShiftResponse {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="employee_missing_shift_response_id")
	private long employeeMissingShiftResponeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_shift_id")
	private BusinessShift businessShift;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id")
	private Business business;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_employee_id")
	private BusinessEmployee businessEmployee;

	@Column (name="reason")
	private String reason;
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public EmployeeMissingShiftResponse() {}

	public EmployeeMissingShiftResponse(BusinessShift businessShift,
			Business business, BusinessEmployee businessEmployee,
			String reason, RequestStatusEnum requestStatus) {
		super();
		this.businessShift = businessShift;
		this.business = business;
		this.businessEmployee = businessEmployee;
		this.reason = reason;
		this.requestStatus = requestStatus;
	}

	public long getEmployeeMissingShiftResponeId() {
		return employeeMissingShiftResponeId;
	}

	public void setEmployeeMissingShiftResponeId(long employeeMissingShiftResponeId) {
		this.employeeMissingShiftResponeId = employeeMissingShiftResponeId;
	}

	public BusinessShift getBusinessShift() {
		return businessShift;
	}

	public void setBusinessShift(BusinessShift businessShift) {
		this.businessShift = businessShift;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public BusinessEmployee getBusinessEmployee() {
		return businessEmployee;
	}

	public void setBusinessEmployee(BusinessEmployee businessEmployee) {
		this.businessEmployee = businessEmployee;
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
