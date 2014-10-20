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

}
