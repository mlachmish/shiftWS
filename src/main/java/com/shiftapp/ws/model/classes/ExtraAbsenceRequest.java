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

/**
 * When a {@link BusinessEmployee} need to ask for extra absence request an {@link ExtraAbsenceRequest} object will be created.
 * @author Matan Lachmish
 */
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (extraAbsenceRequestId ^ (extraAbsenceRequestId >>> 32));
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
		ExtraAbsenceRequest other = (ExtraAbsenceRequest) obj;
		if (extraAbsenceRequestId != other.extraAbsenceRequestId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExtraAbsenceRequest [extraAbsenceRequestId="
				+ extraAbsenceRequestId
				+ ", "
				+ (business != null ? "business=" + business + ", " : "")
				+ (businessEmployee != null ? "businessEmployee="
						+ businessEmployee + ", " : "")
				+ "extraAmount="
				+ extraAmount
				+ ", "
				+ (reason != null ? "reason=" + reason + ", " : "")
				+ (requestStatus != null ? "requestStatus=" + requestStatus
						: "") + "]";
	}

}
