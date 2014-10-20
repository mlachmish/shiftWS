package com.shiftapp.ws.model.classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class ShiftRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="shift_request_id")
	private long shiftRequestId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id")
	private Business business;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private BusinessEmployee businessEmployee;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "shiftRequests")
	private List<BusinessShift> businessShifts;
	
	public ShiftRequest() {}

	public ShiftRequest(Business business, BusinessEmployee businessEmployee,
			List<BusinessShift> businessShifts) {
		super();
		this.business = business;
		this.businessEmployee = businessEmployee;
		this.businessShifts = businessShifts;
	}

	public long getShiftRequestId() {
		return shiftRequestId;
	}

	public void setShiftRequestId(long shiftRequestId) {
		this.shiftRequestId = shiftRequestId;
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

	public List<BusinessShift> getBusinessShifts() {
		return businessShifts;
	}

	public void setBusinessShifts(List<BusinessShift> businessShifts) {
		this.businessShifts = businessShifts;
	}

}
