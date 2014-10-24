package com.shiftapp.ws.model.classes;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.shiftapp.ws.model.enums.WeekDayEnum;

/**
 * Represent a shift pattern definition in a {@link Business}.
 * @author Matan Lachmish
 */
@Entity
public class BusinessShift {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="business_shift_id")
	private long businessShiftId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id")
	private Business business;

	@ElementCollection
	@CollectionTable(name="shiftWeekDays", joinColumns=@JoinColumn(name="business_shift_id"))
	@Column (name="weekDays")
	@Enumerated (EnumType.STRING)
	private Set<WeekDayEnum> weekDays;

	@Column (name="start_time")
	private Time startTime;

	@Column (name="end_time")
	private Time endTime;

	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	@JoinTable(name = "business_shift_requests", joinColumns = { 
			@JoinColumn(name = "business_shift_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "shift_request_id", 
					nullable = false, updatable = false) })
	private List<ShiftRequest> shiftRequests;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessShift")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<ScheduleCrew> crews;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessShift")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<EmployeeMissingShiftResponse> employeeMissingShiftRespones;
	
	public BusinessShift() {}

	public BusinessShift(Business business, Set<WeekDayEnum> weekDays,
			Time startTime, Time endTime, List<ShiftRequest> shiftRequests,
			List<ScheduleCrew> crews) {
		super();
		this.business = business;
		this.weekDays = weekDays;
		this.startTime = startTime;
		this.endTime = endTime;
		this.shiftRequests = shiftRequests;
		this.crews = crews;
	}

	public long getBusinessShiftId() {
		return businessShiftId;
	}

	public void setBusinessShiftId(long businessShiftId) {
		this.businessShiftId = businessShiftId;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public Set<WeekDayEnum> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(Set<WeekDayEnum> weekDays) {
		this.weekDays = weekDays;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
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
		shiftRequest.addBusinessShift(this);
	}
	
	public void removeShiftRequest (ShiftRequest shiftRequest) {
		this.getShiftRequests().remove(shiftRequest);
		shiftRequest.removeBusinessShift(this);
	}
	
	public List<ScheduleCrew> getCrews() {
		if (crews == null) {
			crews = new ArrayList<ScheduleCrew>();
		}
		return crews;
	}

	public void setCrews(List<ScheduleCrew> crews) {
		this.crews = crews;
	}
	
	public void addScheduleCrew (ScheduleCrew crew) {
		this.getCrews().add(crew);
		crew.setBusinessShift(this);
	}
	
	public void removeScheduleCrew (ScheduleCrew crew) {
		this.getCrews().remove(crew);
		crew.setBusinessShift(null);
	}

	public List<EmployeeMissingShiftResponse> getEmployeeMissingShiftRespones() {
		if (employeeMissingShiftRespones == null) {
			employeeMissingShiftRespones = new ArrayList<EmployeeMissingShiftResponse>();
		}
		return employeeMissingShiftRespones;
	}

	public void setEmployeeMissingShiftRespones(
			List<EmployeeMissingShiftResponse> employeeMissingShiftRespones) {
		this.employeeMissingShiftRespones = employeeMissingShiftRespones;
	}

	public void addEmployeeMissingShiftResponse (EmployeeMissingShiftResponse employeeMissingShiftRespone) {
		this.getEmployeeMissingShiftRespones().add(employeeMissingShiftRespone);
		employeeMissingShiftRespone.setBusinessShift(this);
	}
	
	public void removeEmployeeMissingShiftResponse (EmployeeMissingShiftResponse employeeMissingShiftRespone) {
		this.getEmployeeMissingShiftRespones().remove(employeeMissingShiftRespone);
		employeeMissingShiftRespone.setBusinessShift(null);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (businessShiftId ^ (businessShiftId >>> 32));
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
		BusinessShift other = (BusinessShift) obj;
		if (businessShiftId != other.businessShiftId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BusinessShift [businessShiftId=" + businessShiftId
				+ ", business=" + business + ", weekDays=" + weekDays
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", shiftRequests=" + shiftRequests + ", crews=" + crews
				+ ", employeeMissingShiftRespones="
				+ employeeMissingShiftRespones + "]";
	}

}
