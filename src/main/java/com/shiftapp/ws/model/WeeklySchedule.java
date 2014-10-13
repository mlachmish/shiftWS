package com.shiftapp.ws.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeeklySchedule {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column (name="business_id")
	private long businessId;
	
	@Column (name="date")
	private Date date;
	
	//TODO: add a multimap for schedule
	
	@Enumerated (EnumType.STRING)
	private RequestStatusEnum requestStatus;
	
	public WeeklySchedule() {}

	public WeeklySchedule(long businessId, Date date,
			RequestStatusEnum requestStatus) {
		super();
		this.businessId = businessId;
		this.date = date;
		this.requestStatus = requestStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public RequestStatusEnum getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatusEnum requestStatus) {
		this.requestStatus = requestStatus;
	}
}
