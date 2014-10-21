package com.shiftapp.ws.model.classes;

import java.util.List;
import java.util.Set;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Represent employee in a {@link Business}.
 * A {@link User} will have a BusinessEmployee object for each business that he works in.
 * @author Matan Lachmish
 */
@Entity
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
	private User promotingUser;
	
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
	private Set<BusinessCategory> businessCategories;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "businessEmployee")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private ShiftRequest shiftRequest;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessEmployee")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<ExtraAbsenceRequest> extraAbsenceRequests;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "employee_crew", joinColumns = { 
			@JoinColumn(name = "business_employee_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "schedule_crew_id", 
					nullable = false, updatable = false) })
	private Set<ScheduleCrew> scheduleCrews;
	
	public BusinessEmployee() {}

	public BusinessEmployee(User user, Business business, boolean isManager,
			User promotingUser, boolean isSuspended, int defaultAbsences,
			int extraAbsences, Set<BusinessCategory> businessCategories,
			ShiftRequest shiftRequest,
			List<ExtraAbsenceRequest> extraAbsenceRequests,
			Set<ScheduleCrew> scheduleCrews) {
		super();
		this.user = user;
		this.business = business;
		this.isManager = isManager;
		this.promotingUser = promotingUser;
		this.isSuspended = isSuspended;
		this.defaultAbsences = defaultAbsences;
		this.extraAbsences = extraAbsences;
		this.businessCategories = businessCategories;
		this.shiftRequest = shiftRequest;
		this.extraAbsenceRequests = extraAbsenceRequests;
		this.scheduleCrews = scheduleCrews;
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

	public User getPromotingUser() {
		return promotingUser;
	}

	public void setPromotingUser(User promotingUser) {
		this.promotingUser = promotingUser;
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

	public Set<BusinessCategory> getBusinessCategories() {
		return businessCategories;
	}

	public void setBusinessCategories(Set<BusinessCategory> businessCategories) {
		this.businessCategories = businessCategories;
	}

	public ShiftRequest getShiftRequest() {
		return shiftRequest;
	}

	public void setShiftRequest(ShiftRequest shiftRequest) {
		this.shiftRequest = shiftRequest;
	}

	public List<ExtraAbsenceRequest> getExtraAbsenceRequests() {
		return extraAbsenceRequests;
	}

	public void setExtraAbsenceRequests(
			List<ExtraAbsenceRequest> extraAbsenceRequests) {
		this.extraAbsenceRequests = extraAbsenceRequests;
	}

	public Set<ScheduleCrew> getScheduleCrews() {
		return scheduleCrews;
	}

	public void setScheduleCrews(Set<ScheduleCrew> scheduleCrews) {
		this.scheduleCrews = scheduleCrews;
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
				+ isManager + ", promotingUser=" + promotingUser
				+ ", isSuspended=" + isSuspended + ", defaultAbsences="
				+ defaultAbsences + ", extraAbsences=" + extraAbsences
				+ ", businessCategories=" + businessCategories
				+ ", shiftRequest=" + shiftRequest + ", extraAbsenceRequests="
				+ extraAbsenceRequests + ", scheduleCrews=" + scheduleCrews
				+ "]";
	}

}
