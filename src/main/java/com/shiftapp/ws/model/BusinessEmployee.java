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
public class BusinessEmployee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column (name="business_id")
	private long businessId;
	
	@Column (name="is_manager")
	private boolean isManager;
	
	@Column (name="is_suspended")
	private boolean isSuspended;

	/**
	 * User id of promoter
	 */
	@Column (name="promoted_by")
	private long promotedBy;
	
	@Column (name="default_absences")
	private long defaultAbsences;
	
	@Column (name="extra_absences")
	private long extraAbsences;
	
	@ElementCollection
	@CollectionTable(name="employeeCategories", joinColumns=@JoinColumn(name="id"))
	@Column (name="categories")
	private Set<Long> categories;
	
	public BusinessEmployee() {}

	public BusinessEmployee(long id, long businessId, boolean isManager,
			boolean isSuspended, long promotedBy, long defaultAbsences,
			long extraAbsences, Set<Long> categories) {
		super();
		this.id = id;
		this.businessId = businessId;
		this.isManager = isManager;
		this.isSuspended = isSuspended;
		this.promotedBy = promotedBy;
		this.defaultAbsences = defaultAbsences;
		this.extraAbsences = extraAbsences;
		this.categories = categories;
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

	public boolean isManager() {
		return isManager;
	}

	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public long getPromotedBy() {
		return promotedBy;
	}

	public void setPromotedBy(long promotedBy) {
		this.promotedBy = promotedBy;
	}

	public long getDefaultAbsences() {
		return defaultAbsences;
	}

	public void setDefaultAbsences(long defaultAbsences) {
		this.defaultAbsences = defaultAbsences;
	}

	public long getExtraAbsences() {
		return extraAbsences;
	}

	public void setExtraAbsences(long extraAbsences) {
		this.extraAbsences = extraAbsences;
	}

	public Set<Long> getCategories() {
		return categories;
	}

	public void setCategories(Set<Long> categories) {
		this.categories = categories;
	}
}
