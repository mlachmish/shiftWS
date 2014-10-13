package com.shiftapp.ws.model;

import java.sql.Time;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class BusinessShift {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column (name="business_id")
	private long businessId;

	@ElementCollection
	@CollectionTable(name="shiftDays", joinColumns=@JoinColumn(name="id"))
	@Column (name="days")
	private Set<Integer> days;

	@Column (name="start_time")
	private Time startTime;

	@Column (name="end_time")
	private Time endTime;

	/**
	 * crew is a map between category id and amount of employees from that category.
	 */
	@ElementCollection
	@CollectionTable(name="shiftCrew", joinColumns=@JoinColumn(name="id"))
	@Column (name="crew")
	private Map<Long, Integer> crew;

	public BusinessShift() {}

	public BusinessShift(long businessId, Set<Integer> days, Time startTime,
			Time endTime, Map<Long, Integer> crew) {
		super();
		this.businessId = businessId;
		this.days = days;
		this.startTime = startTime;
		this.endTime = endTime;
		this.crew = crew;
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

	public Set<Integer> getDays() {
		return days;
	}

	public void setDays(Set<Integer> days) {
		this.days = days;
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

	public Map<Long, Integer> getCrew() {
		return crew;
	}

	public void setCrew(Map<Long, Integer> crew) {
		this.crew = crew;
	}
}
