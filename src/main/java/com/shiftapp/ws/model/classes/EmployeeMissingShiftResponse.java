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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.shiftapp.ws.model.enums.RequestStatusEnum;

/**
 * In case of a {@link BusinessShift} with missing employees,
 * {@link EmployeeMissingShiftResponse} object will be created for all {@link BusinessEmployee} that don't work in that {@link BusinessShift}.
 * @author Matan Lachmish
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "business_shift_id", "business_employee_id" })})
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (employeeMissingShiftResponeId ^ (employeeMissingShiftResponeId >>> 32));
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
		EmployeeMissingShiftResponse other = (EmployeeMissingShiftResponse) obj;
		if (employeeMissingShiftResponeId != other.employeeMissingShiftResponeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeMissingShiftResponse [employeeMissingShiftResponeId="
				+ employeeMissingShiftResponeId
				+ ", "
				+ (businessShift != null ? "businessShift=" + businessShift
						+ ", " : "")
				+ (business != null ? "business=" + business + ", " : "")
				+ (businessEmployee != null ? "businessEmployee="
						+ businessEmployee + ", " : "")
				+ (reason != null ? "reason=" + reason + ", " : "")
				+ (requestStatus != null ? "requestStatus=" + requestStatus
						: "") + "]";
	}

}
