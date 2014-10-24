package com.shiftapp.ws.model.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.shiftapp.ws.model.enums.WeekDayEnum;

/**
 * Represent a crew of {@link BusinessEmployee} that are working in a {@link BusinessShift}.
 * @author Matan Lachmish
 */
@Entity
public class ScheduleCrew {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="schedule_crew_id")
	private long scheduleCrewId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weeklySchedule_id")
	private WeeklySchedule weeklySchedule;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_shift_id")
	private BusinessShift businessShift;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_category_id")
	private BusinessCategory businessCategory;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "scheduleCrews")
	private List<BusinessEmployee> businessEmployees;
	
	@Enumerated (EnumType.STRING)
	@Column (name="weekDay")
	private WeekDayEnum weekDay;
	
	public ScheduleCrew() {}

	public ScheduleCrew(WeeklySchedule weeklySchedule,
			BusinessShift businessShift, BusinessCategory businessCategory,
			List<BusinessEmployee> businessEmployees, WeekDayEnum weekDay) {
		super();
		this.weeklySchedule = weeklySchedule;
		this.businessShift = businessShift;
		this.businessCategory = businessCategory;
		this.businessEmployees = businessEmployees;
		this.weekDay = weekDay;
	}

	public long getScheduleCrewId() {
		return scheduleCrewId;
	}

	public void setScheduleCrewId(long scheduleCrewId) {
		this.scheduleCrewId = scheduleCrewId;
	}

	public WeeklySchedule getWeeklySchedule() {
		return weeklySchedule;
	}

	public void setWeeklySchedule(WeeklySchedule weeklySchedule) {
		this.weeklySchedule = weeklySchedule;
	}

	public BusinessShift getBusinessShift() {
		return businessShift;
	}

	public void setBusinessShift(BusinessShift businessShift) {
		this.businessShift = businessShift;
	}

	public BusinessCategory getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(BusinessCategory businessCategory) {
		this.businessCategory = businessCategory;
	}

	public List<BusinessEmployee> getBusinessEmployees() {
		if (businessEmployees == null) {
			businessEmployees = new ArrayList<BusinessEmployee>();
		}
		return businessEmployees;
	}

	public void setBusinessEmployees(List<BusinessEmployee> businessEmployee) {
		this.businessEmployees = businessEmployee;
	}
	
	public void addBusinessEmployee (BusinessEmployee businessEmployee) {
		this.getBusinessEmployees().add(businessEmployee);
		businessEmployee.addScheduleCrew(this);
	}
	
	public void removeBusinessEmployee (BusinessEmployee businessEmployee) {
		this.getBusinessEmployees().remove(businessEmployee);
		businessEmployee.removeScheduleCrew(this);
	}

	public WeekDayEnum getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(WeekDayEnum weekDay) {
		this.weekDay = weekDay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (scheduleCrewId ^ (scheduleCrewId >>> 32));
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
		ScheduleCrew other = (ScheduleCrew) obj;
		if (scheduleCrewId != other.scheduleCrewId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ScheduleCrew [scheduleCrewId=" + scheduleCrewId
				+ ", weeklySchedule=" + weeklySchedule + ", businessShift="
				+ businessShift + ", businessCategory=" + businessCategory
				+ ", businessEmployee=" + businessEmployees + ", weekDay="
				+ weekDay + "]";
	}

}
