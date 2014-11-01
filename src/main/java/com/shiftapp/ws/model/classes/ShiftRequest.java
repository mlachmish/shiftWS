package com.shiftapp.ws.model.classes;

import java.util.ArrayList;
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

/**
 * Every week all {@link User}s create a {@link ShiftRequest} describing the {@link BusinessShift} they don't want to work in.
 * @author Matan Lachmish
 */
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
		if (businessShifts == null) {
			businessShifts = new ArrayList<BusinessShift>();
		}
		return businessShifts;
	}

	public void setBusinessShifts(List<BusinessShift> businessShifts) {
		this.businessShifts = businessShifts;
	}

	public void addBusinessShift (BusinessShift businessShift) {
		this.getBusinessShifts().add(businessShift);
		if (!businessShift.getShiftRequests().contains(this)) {
			businessShift.addShiftRequest(this);
		}
	}
	
	public void removeBusinessShift (BusinessShift businessShift) {
		this.getBusinessShifts().remove(businessShift);
		businessShift.removeShiftRequest(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (shiftRequestId ^ (shiftRequestId >>> 32));
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
		ShiftRequest other = (ShiftRequest) obj;
		if (shiftRequestId != other.shiftRequestId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShiftRequest [shiftRequestId="
				+ shiftRequestId
				+ ", "
				+ (business != null ? "business=" + business + ", " : "")
				+ (businessEmployee != null ? "businessEmployee="
						+ businessEmployee + ", " : "")
				+ (businessShifts != null ? "businessShifts=" + businessShifts
						: "") + "]";
	}

}
