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
public class ExtraAbsenceRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="extra_absence_request_id")
	private long extraAbsenceRequestId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id")
	private Business business;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_employee_id")
	private BusinessEmployee businessEmployee;
	
	@Column (name="extra_amount")
	private int extraAmount;
	
	@Column (name="reason")
	private String reason;
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public ExtraAbsenceRequest() {}

	public ExtraAbsenceRequest(Business business,
			BusinessEmployee businessEmployee, int extraAmount, String reason,
			RequestStatusEnum requestStatus) {
		super();
		this.business = business;
		this.businessEmployee = businessEmployee;
		this.extraAmount = extraAmount;
		this.reason = reason;
		this.requestStatus = requestStatus;
	}

	public long getExtraAbsenceRequestId() {
		return extraAbsenceRequestId;
	}

	public void setExtraAbsenceRequestId(long extraAbsenceRequestId) {
		this.extraAbsenceRequestId = extraAbsenceRequestId;
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

	public int getExtraAmount() {
		return extraAmount;
	}

	public void setExtraAmount(int extraAmount) {
		this.extraAmount = extraAmount;
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
