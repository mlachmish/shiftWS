package com.shiftapp.ws.model.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Represent a business object. Every business have exactly one {@link Business} object in the model.
 * @author Matan Lachmish
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "business_name", "address" })})
public class Business {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="business_id")
	private long businessId;
	
	@Column (name="business_name")
	private String businessName;
	
	@Column (name="address")
	private String address;
	
	@Column (name="logo")
	private String logo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<BusinessCategory> businessCategories;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<BusinessShift> businessShifts;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<BusinessEmployee> businessEmployees;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<ShiftRequest> shiftRequests;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<ExtraAbsenceRequest> extraAbseceRequests;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<EmployeeMissingShiftResponse> missingShifts;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "business")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<JoinRequest> joinRequests;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "business")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private WeeklySchedule weeklySchedule;
	
	public Business() {}

	public Business(String businessName, String address, String logo,
			List<BusinessCategory> businessCategories,
			List<BusinessShift> businessShifts,
			List<BusinessEmployee> businessEmployees,
			List<ShiftRequest> shiftRequests,
			List<ExtraAbsenceRequest> extraAbseceRequests,
			List<EmployeeMissingShiftResponse> missingShifts, List<JoinRequest> joinRequests,
			WeeklySchedule weeklySchedule) {
		super();
		this.businessName = businessName;
		this.address = address;
		this.logo = logo;
		this.businessCategories = businessCategories;
		this.businessShifts = businessShifts;
		this.businessEmployees = businessEmployees;
		this.shiftRequests = shiftRequests;
		this.extraAbseceRequests = extraAbseceRequests;
		this.missingShifts = missingShifts;
		this.joinRequests = joinRequests;
		this.weeklySchedule = weeklySchedule;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<BusinessCategory> getBusinessCategories() {
		if (businessCategories == null) {
			businessCategories = new ArrayList<BusinessCategory>();
		}
		return businessCategories;
	}

	public void setBusinessCategories(List<BusinessCategory> businessCategories) {
		this.businessCategories = businessCategories;
	}
	
	public void addBusinessCategory (BusinessCategory businessCategory) {
		this.getBusinessCategories().add(businessCategory);
		businessCategory.setBusiness(this);
	}
	
	public void removeBusinessCategory (BusinessCategory businessCategory) {
		this.getBusinessCategories().remove(businessCategory);
		businessCategory.setBusiness(null);
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
		businessShift.setBusiness(this);
	}
	
	public void removeBusinessShift (BusinessShift businessShift) {
		this.getBusinessShifts().remove(businessShift);
		businessShift.setBusiness(null);
	}
	
	public List<BusinessEmployee> getBusinessEmployees() {
		if (businessEmployees == null) {
			businessEmployees = new ArrayList<BusinessEmployee>();
		}
		return businessEmployees;
	}

	public void setBusinessEmployees(List<BusinessEmployee> businessEmployees) {
		this.businessEmployees = businessEmployees;
	}

	public void addBusinessEmployee (BusinessEmployee businessEmployee) {
		this.getBusinessEmployees().add(businessEmployee);
		businessEmployee.setBusiness(this);
	}
	
	public void removeBusinessEmployee (BusinessEmployee businessEmployee) {
		this.getBusinessEmployees().remove(businessEmployee);
		businessEmployee.setBusiness(null);
	}

	public List<ShiftRequest> getShiftRequests() {
		if (shiftRequests == null) {
			shiftRequests = new ArrayList<ShiftRequest>();
		}
		return shiftRequests;
	}

	public void setShiftRequests(List<ShiftRequest> shiftRequests) {
		this.shiftRequests = shiftRequests;
	}

	public void addShiftRequest (ShiftRequest shiftRequest) {
		this.getShiftRequests().add(shiftRequest);
		shiftRequest.setBusiness(this);
	}
	
	public void removeShiftRequest (ShiftRequest shiftRequest) {
		this.getShiftRequests().remove(shiftRequest);
		shiftRequest.setBusiness(null);
	}

	public List<ExtraAbsenceRequest> getExtraAbseceRequests() {
		if (extraAbseceRequests == null) {
			extraAbseceRequests = new ArrayList<ExtraAbsenceRequest>();
		}
		return extraAbseceRequests;
	}

	public void setExtraAbseceRequests(List<ExtraAbsenceRequest> extraAbseceRequests) {
		this.extraAbseceRequests = extraAbseceRequests;
	}

	public void addExtraAbsenceRequest (ExtraAbsenceRequest extraAbsenceRequest) {
		this.getExtraAbseceRequests().add(extraAbsenceRequest);
		extraAbsenceRequest.setBusiness(this);
	}
	
	public void removeExtraAbsenceRequest (ExtraAbsenceRequest extraAbsenceRequest) {
		this.getExtraAbseceRequests().remove(extraAbsenceRequest);
		extraAbsenceRequest.setBusiness(null);
	}

	public List<EmployeeMissingShiftResponse> getMissingShifts() {
		if (missingShifts == null) {
			missingShifts = new ArrayList<EmployeeMissingShiftResponse>();
		}
		return missingShifts;
	}

	public void setMissingShifts(List<EmployeeMissingShiftResponse> missingShifts) {
		this.missingShifts = missingShifts;
	}
	
	public void addMissingShift (EmployeeMissingShiftResponse employeeMissingShiftResponse) {
		this.getMissingShifts().add(employeeMissingShiftResponse);
		employeeMissingShiftResponse.setBusiness(this);
	}
	
	public void removeMissingShift (EmployeeMissingShiftResponse employeeMissingShiftResponse) {
		this.getMissingShifts().remove(employeeMissingShiftResponse);
		employeeMissingShiftResponse.setBusiness(null);
	}

	public List<JoinRequest> getJoinRequests() {
		if (joinRequests == null) {
			joinRequests = new ArrayList<JoinRequest>();
		}
		return joinRequests;
	}

	public void setJoinRequests(List<JoinRequest> joinRequests) {
		this.joinRequests = joinRequests;
	}

	public void addJoinRequest (JoinRequest joinRequest) {
		this.getJoinRequests().add(joinRequest);
		joinRequest.setBusiness(this);
	}
	
	public void removeJoinRequest (JoinRequest joinRequest) {
		this.getJoinRequests().remove(joinRequest);
		joinRequest.setBusiness(null);
	}
	public WeeklySchedule getWeeklySchedule() {
		return weeklySchedule;
	}

	public void setWeeklySchedule(WeeklySchedule weeklySchedule) {
		this.weeklySchedule = weeklySchedule;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (businessId ^ (businessId >>> 32));
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
		Business other = (Business) obj;
		if (businessId != other.businessId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Business [businessId=" + businessId + ", businessName="
				+ businessName + ", address=" + address + ", logo=" + logo
				+ ", businessCategories=" + businessCategories
				+ ", businessShifts=" + businessShifts + ", businessEmployees="
				+ businessEmployees + ", shiftRequests=" + shiftRequests
				+ ", extraAbseceRequests=" + extraAbseceRequests
				+ ", missingShifts=" + missingShifts + ", joinRequests="
				+ joinRequests + ", weeklySchedule=" + weeklySchedule + "]";
	}

}
