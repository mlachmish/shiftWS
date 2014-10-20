package com.shiftapp.ws.model.classes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
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
		return businessCategories;
	}

	public void setBusinessCategories(List<BusinessCategory> businessCategories) {
		this.businessCategories = businessCategories;
	}

	public List<BusinessShift> getBusinessShifts() {
		return businessShifts;
	}

	public void setBusinessShifts(List<BusinessShift> businessShifts) {
		this.businessShifts = businessShifts;
	}

	public List<BusinessEmployee> getBusinessEmployees() {
		return businessEmployees;
	}

	public void setBusinessEmployees(List<BusinessEmployee> businessEmployees) {
		this.businessEmployees = businessEmployees;
	}

	public List<ShiftRequest> getShiftRequests() {
		return shiftRequests;
	}

	public void setShiftRequests(List<ShiftRequest> shiftRequests) {
		this.shiftRequests = shiftRequests;
	}

	public List<ExtraAbsenceRequest> getExtraAbseceRequests() {
		return extraAbseceRequests;
	}

	public void setExtraAbseceRequests(List<ExtraAbsenceRequest> extraAbseceRequests) {
		this.extraAbseceRequests = extraAbseceRequests;
	}

	public List<EmployeeMissingShiftResponse> getMissingShifts() {
		return missingShifts;
	}

	public void setMissingShifts(List<EmployeeMissingShiftResponse> missingShifts) {
		this.missingShifts = missingShifts;
	}

	public List<JoinRequest> getJoinRequests() {
		return joinRequests;
	}

	public void setJoinRequests(List<JoinRequest> joinRequests) {
		this.joinRequests = joinRequests;
	}

	public WeeklySchedule getWeeklySchedule() {
		return weeklySchedule;
	}

	public void setWeeklySchedule(WeeklySchedule weeklySchedule) {
		this.weeklySchedule = weeklySchedule;
	}

}
