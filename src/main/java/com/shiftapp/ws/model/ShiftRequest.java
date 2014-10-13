package com.shiftapp.ws.model;

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
public class ShiftRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column (name="employee_id")
	private long employeeId;
	
	@ElementCollection
	@CollectionTable(name="employeeShiftIdRequests", joinColumns=@JoinColumn(name="id"))
	@Column (name="business_shift_ids")
	private Set<Long> businessShiftIds;
	
	public ShiftRequest() {}

	public ShiftRequest(long employeeId, Set<Long> businessShiftIds) {
		super();
		this.employeeId = employeeId;
		this.businessShiftIds = businessShiftIds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public Set<Long> getBusinessShiftIds() {
		return businessShiftIds;
	}

	public void setBusinessShiftIds(Set<Long> businessShiftIds) {
		this.businessShiftIds = businessShiftIds;
	}
}
