package com.shiftapp.ws.model.classes;

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
	private List<ScheduleCrew> schedule;
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public WeeklySchedule() {}

	public WeeklySchedule(Business business, Date date,
			List<ScheduleCrew> schedule, RequestStatusEnum requestStatus) {
		super();
		this.business = business;
		this.date = date;
		this.schedule = schedule;
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

	public List<ScheduleCrew> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<ScheduleCrew> schedule) {
		this.schedule = schedule;
	}

	public RequestStatusEnum getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatusEnum requestStatus) {
		this.requestStatus = requestStatus;
	}

}
