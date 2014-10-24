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
	private List<ScheduleCrew> crews;
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public WeeklySchedule() {}

	public WeeklySchedule(Business business, Date date,
			List<ScheduleCrew> crews, RequestStatusEnum requestStatus) {
		super();
		this.business = business;
		this.date = date;
		this.crews = crews;
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

	public List<ScheduleCrew> getCrews() {
		if (crews == null) {
			crews = new ArrayList<ScheduleCrew>();
		}
		return crews;
	}

	public void setCrews(List<ScheduleCrew> crews) {
		this.crews = crews;
	}
	
	public void addCrew (ScheduleCrew Crew) {
		this.getCrews().add(Crew);
		Crew.setWeeklySchedule(this);
	}
	
	public void removeCrew (ScheduleCrew Crew) {
		this.getCrews().remove(Crew);
		Crew.setWeeklySchedule(null);
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
		return "WeeklySchedule [weeklyScheduleId=" + weeklyScheduleId
				+ ", business=" + business + ", date=" + date + ", schedule="
				+ crews + ", requestStatus=" + requestStatus + "]";
	}

}
