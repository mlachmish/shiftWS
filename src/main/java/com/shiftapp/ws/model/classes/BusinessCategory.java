package com.shiftapp.ws.model.classes;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Represent employee category in a {@link Business} (i.e. Waiter, Chef, etc...)
 * @author Matan Lachmish
 */
@Entity
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
	private Set<BusinessEmployee> businessEmployee;
	
	public BusinessCategory() {}

	public BusinessCategory(Business business, String categoryName,
			Set<BusinessEmployee> businessEmployee) {
		super();
		this.business = business;
		this.categoryName = categoryName;
		this.businessEmployee = businessEmployee;
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

	public Set<BusinessEmployee> getBusinessEmployee() {
		return businessEmployee;
	}

	public void setBusinessEmployee(Set<BusinessEmployee> businessEmployee) {
		this.businessEmployee = businessEmployee;
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
		return "BusinessCategory [businessCategoryId=" + businessCategoryId
				+ ", business=" + business + ", categoryName=" + categoryName
				+ ", businessEmployee=" + businessEmployee + "]";
	}

}
