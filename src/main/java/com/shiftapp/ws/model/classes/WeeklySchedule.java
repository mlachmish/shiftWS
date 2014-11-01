package com.shiftapp.ws.model.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.shiftapp.ws.model.enums.RequestStatusEnum;

/**
 * Every {@link Business} have a {@link WeeklySchedule} representing which {@link BusinessEmployee} work in in what {@link BusinessShift}.
 * @author Matan Lachmish
 */
@Entity
public class WeeklySchedule {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="weekly_schedule_id")
	private long weeklyScheduleId;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Business business;
	
	@Column (name="date")
	private Date date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "weeklySchedule")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<BusinessShift> businessShifts;
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public WeeklySchedule() {}


	public WeeklySchedule(Business business, Date date,
			List<BusinessShift> businessShifts, RequestStatusEnum requestStatus) {
		super();
		this.business = business;
		this.date = date;
		this.businessShifts = businessShifts;
		this.requestStatus = requestStatus;
	}

	public long getWeeklyScheduleId() {
		return weeklyScheduleId;
	}

	public void setWeeklyScheduleId(long weeklyScheduleId) {
		this.weeklyScheduleId = weeklyScheduleId;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
		businessShift.setWeeklySchedule(this);
	}
	
	public void removeBusinessShift (BusinessShift businessShift) {
		this.getBusinessShifts().remove(businessShift);
		businessShift.setWeeklySchedule(null);
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
		result = prime * result
				+ (int) (weeklyScheduleId ^ (weeklyScheduleId >>> 32));
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
		WeeklySchedule other = (WeeklySchedule) obj;
		if (weeklyScheduleId != other.weeklyScheduleId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WeeklySchedule [weeklyScheduleId="
				+ weeklyScheduleId
				+ ", "
				+ (business != null ? "business=" + business + ", " : "")
				+ (date != null ? "date=" + date + ", " : "")
				+ (businessShifts != null ? "businessShifts=" + businessShifts
						+ ", " : "")
				+ (requestStatus != null ? "requestStatus=" + requestStatus
						: "") + "]";
	}

}
