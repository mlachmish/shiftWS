package com.shiftapp.ws.model.classes;

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
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "scheduleCrews")
	private List<BusinessEmployee> businessEmployee;
	
	@Enumerated (EnumType.STRING)
	@Column (name="weekDay")
	private WeekDayEnum weekDay;
	
	public ScheduleCrew() {}

	public ScheduleCrew(WeeklySchedule weeklySchedule,
			BusinessShift businessShift,
			List<BusinessEmployee> businessEmployee, WeekDayEnum weekDay) {
		super();
		this.weeklySchedule = weeklySchedule;
		this.businessShift = businessShift;
		this.businessEmployee = businessEmployee;
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

	public List<BusinessEmployee> getBusinessEmployee() {
		return businessEmployee;
	}

	public void setBusinessEmployee(List<BusinessEmployee> businessEmployee) {
		this.businessEmployee = businessEmployee;
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
				+ businessShift + ", businessEmployee=" + businessEmployee
				+ ", weekDay=" + weekDay + "]";
	}

}
