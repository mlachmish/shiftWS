package com.shiftapp.ws.model.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Represent employee category in a {@link Business} (i.e. Waiter, Chef, etc...)
 * @author Matan Lachmish
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "business_id", "category_name" })})
public class BusinessCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="business_category_id")
	private long businessCategoryId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "business_id")
	private Business business;
	
	@Column (name="category_name")
	private String categoryName;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "businessCategories")
	private List<BusinessEmployee> businessEmployees;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "businessCategory")
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	private List<ScheduleCrew> scheduleCrews;
	
	public BusinessCategory() {}

	public BusinessCategory(Business business, String categoryName,
			List<BusinessEmployee> businessEmployees,
			List<ScheduleCrew> scheduleCrews) {
		super();
		this.business = business;
		this.categoryName = categoryName;
		this.businessEmployees = businessEmployees;
		this.scheduleCrews = scheduleCrews;
	}

	public long getBusinessCategoryId() {
		return businessCategoryId;
	}

	public void setBusinessCategoryId(long businessCategoryId) {
		this.businessCategoryId = businessCategoryId;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<BusinessEmployee> getBusinessEmployees() {
		if (businessEmployees == null) {
			businessEmployees = new ArrayList<BusinessEmployee>();
		}
		return businessEmployees;
	}

	public void setBusinessEmployees(List<BusinessEmployee> businessEmployees) {
		this.businessEmployees = businessEmployees;
	}
	
	public void addBusinessEmployee (BusinessEmployee businessEmployee) {
		this.getBusinessEmployees().add(businessEmployee);
		if (!businessEmployee.getBusinessCategories().contains(this)) {
			businessEmployee.addBusinessCategory(this);
		}
	}
	
	public void removeBusinessEmployee (BusinessEmployee scheduleCrew) {
		this.getBusinessEmployees().remove(scheduleCrew);
		scheduleCrew.removeBusinessCategory(this);
	}

	public List<ScheduleCrew> getScheduleCrews() {
		if (scheduleCrews == null) {
			scheduleCrews = new ArrayList<ScheduleCrew>();
		}
		return scheduleCrews;
	}

	public void setScheduleCrews(List<ScheduleCrew> scheduleCrews) {
		this.scheduleCrews = scheduleCrews;
	}
	
	public void addScheduleCrew (ScheduleCrew scheduleCrew) {
		this.getScheduleCrews().add(scheduleCrew);
		scheduleCrew.setBusinessCategory(this);
	}
	
	public void removeScheduleCrew (ScheduleCrew scheduleCrew) {
		this.getScheduleCrews().remove(scheduleCrew);
		scheduleCrew.setBusinessCategory(null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (businessCategoryId ^ (businessCategoryId >>> 32));
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
		BusinessCategory other = (BusinessCategory) obj;
		if (businessCategoryId != other.businessCategoryId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BusinessCategory [businessCategoryId="
				+ businessCategoryId
				+ ", "
				+ (business != null ? "business=" + business + ", " : "")
				+ (categoryName != null ? "categoryName=" + categoryName + ", "
						: "")
				+ (businessEmployees != null ? "businessEmployees="
						+ businessEmployees + ", " : "")
				+ (scheduleCrews != null ? "scheduleCrews=" + scheduleCrews
						: "") + "]";
	}

}
