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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Represent employee in a {@link Business}.
 * A {@link User} will have a BusinessEmployee object for each business that he works in.
 * @author Matan Lachmish
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "user_id", "business_id" })})
public class BusinessEmployee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="business_employee_id")
	private long businessEmployeeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id")
	private Business business;
	
	@Column (name="is_manager")
	private boolean isManager;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private BusinessEmployee promotingEmployee;
	
	@Column (name="is_suspended")
	private boolean isSuspended;
	
	@Column (name="default_absences")
	private int defaultAbsences;
	
	@Column (name="extra_absences")
	private int extraAbsences;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinTable(name = "employee_category", joinColumns = { 
			@JoinColumn(name = "business_employee_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "business_category_id", 
					nullable = false, updatable = false) })
	private List<BusinessCategory> businessCategories;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "businessEmployee")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private ShiftRequest shiftRequest;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessEmployee")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<ExtraAbsenceRequest> extraAbsenceRequests;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessEmployee")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<EmployeeMissingShiftResponse> missingShifts;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "employee_crew", joinColumns = { 
			@JoinColumn(name = "business_employee_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "schedule_crew_id", 
					nullable = false, updatable = false) })
	private List<ScheduleCrew> scheduleCrews;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "promotingEmployee")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<BusinessEmployee> promotedBusinessEmployees;
	
	public BusinessEmployee() {}


	public BusinessEmployee(User user, Business business, boolean isManager,
			BusinessEmployee promotingEmployee, boolean isSuspended,
			int defaultAbsences, int extraAbsences,
			List<BusinessCategory> businessCategories,
			ShiftRequest shiftRequest,
			List<ExtraAbsenceRequest> extraAbsenceRequests,
			List<EmployeeMissingShiftResponse> missingShifts,
			List<ScheduleCrew> scheduleCrews,
			List<BusinessEmployee> promotedBusinessEmployees) {
		super();
		this.user = user;
		this.business = business;
		this.isManager = isManager;
		this.promotingEmployee = promotingEmployee;
		this.isSuspended = isSuspended;
		this.defaultAbsences = defaultAbsences;
		this.extraAbsences = extraAbsences;
		this.businessCategories = businessCategories;
		this.shiftRequest = shiftRequest;
		this.extraAbsenceRequests = extraAbsenceRequests;
		this.missingShifts = missingShifts;
		this.scheduleCrews = scheduleCrews;
		this.promotedBusinessEmployees = promotedBusinessEmployees;
	}

	public long getBusinessEmployeeId() {
		return businessEmployeeId;
	}

	public void setBusinessEmployeeId(long businessEmployeeId) {
		this.businessEmployeeId = businessEmployeeId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public BusinessEmployee getPromotingEmployee() {
		return promotingEmployee;
	}

	public void setPromotingEmployee(BusinessEmployee promotingEmployee) {
		this.promotingEmployee = promotingEmployee;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public int getDefaultAbsences() {
		return defaultAbsences;
	}

	public void setDefaultAbsences(int defaultAbsences) {
		this.defaultAbsences = defaultAbsences;
	}

	public int getExtraAbsences() {
		return extraAbsences;
	}

	public void setExtraAbsences(int extraAbsences) {
		this.extraAbsences = extraAbsences;
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
		businessCategory.addBusinessEmployee(this);
	}
	
	public void removeBusinessCategory (BusinessCategory businessCategory) {
		this.getBusinessCategories().remove(businessCategory);
		businessCategory.removeBusinessEmployee(this);
	}

	public ShiftRequest getShiftRequest() {
		return shiftRequest;
	}

	public void setShiftRequest(ShiftRequest shiftRequest) {
		this.shiftRequest = shiftRequest;
	}

	public List<ExtraAbsenceRequest> getExtraAbsenceRequests() {
		if (extraAbsenceRequests == null) {
			extraAbsenceRequests = new ArrayList<ExtraAbsenceRequest>();
		}
		return extraAbsenceRequests;
	}

	public void setExtraAbsenceRequests(
			List<ExtraAbsenceRequest> extraAbsenceRequests) {
		this.extraAbsenceRequests = extraAbsenceRequests;
	}
	
	public void addExtraAbsenceRequest (ExtraAbsenceRequest extraAbsenceRequest) {
		this.getExtraAbsenceRequests().add(extraAbsenceRequest);
		extraAbsenceRequest.setBusinessEmployee(this);
	}
	
	public void removeExtraAbsenceRequest (ExtraAbsenceRequest extraAbsenceRequest) {
		this.getExtraAbsenceRequests().remove(extraAbsenceRequest);
		extraAbsenceRequest.setBusinessEmployee(null);
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
	
	public void addMissingShift (EmployeeMissingShiftResponse missingShift) {
		this.getMissingShifts().add(missingShift);
		missingShift.setBusinessEmployee(this);
	}
	
	public void removeMissingShift (EmployeeMissingShiftResponse missingShift) {
		this.getMissingShifts().remove(missingShift);
		missingShift.setBusinessEmployee(null);
	}

	public List<ScheduleCrew> getScheduleCrews() {
		if (scheduleCrews == null) {
			scheduleCrews = new ArrayList<ScheduleCrew>();
		}
		return scheduleCrews;
	}

	public void setScheduleCrews(List<ScheduleCrew> scheduleCrews) {
		this.scheduleCrews = scheduleCrews;
	}
	
	public void addScheduleCrew (ScheduleCrew scheduleCrew) {
		this.getScheduleCrews().add(scheduleCrew);
		scheduleCrew.addBusinessEmployee(this);
	}
	
	public void removeScheduleCrew (ScheduleCrew scheduleCrew) {
		this.getScheduleCrews().remove(scheduleCrew);
		scheduleCrew.removeBusinessEmployee(this);
	}

	public List<BusinessEmployee> getPromotedBusinessEmployees() {
		if (promotedBusinessEmployees == null) {
			promotedBusinessEmployees = new ArrayList<BusinessEmployee>();
		}
		return promotedBusinessEmployees;
	}

	public void setPromotedBusinessEmployees(
			List<BusinessEmployee> promotedBusinessEmployees) {
		this.promotedBusinessEmployees = promotedBusinessEmployees;
	}

	public void addPromotedBusinessEmployee (BusinessEmployee promotedBusinessEmployee) {
		this.getPromotedBusinessEmployees().add(promotedBusinessEmployee);
		promotedBusinessEmployee.setPromotingEmployee(this);
	}
	
	public void removePromotedBusinessEmployee (BusinessEmployee promotedBusinessEmployee) {
		this.getPromotedBusinessEmployees().remove(promotedBusinessEmployee);
		promotedBusinessEmployee.setPromotingEmployee(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (businessEmployeeId ^ (businessEmployeeId >>> 32));
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
		BusinessEmployee other = (BusinessEmployee) obj;
		if (businessEmployeeId != other.businessEmployeeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BusinessEmployee [businessEmployeeId=" + businessEmployeeId
				+ ", user=" + user + ", business=" + business + ", isManager="
				+ isManager + ", promotingEmployee=" + promotingEmployee
				+ ", isSuspended=" + isSuspended + ", defaultAbsences="
				+ defaultAbsences + ", extraAbsences=" + extraAbsences
				+ ", businessCategories=" + businessCategories
				+ ", shiftRequest=" + shiftRequest + ", extraAbsenceRequests="
				+ extraAbsenceRequests + ", missingShifts=" + missingShifts
				+ ", scheduleCrews=" + scheduleCrews
				+ ", promotedBusinessEmployees=" + promotedBusinessEmployees
				+ "]";
	}


}
