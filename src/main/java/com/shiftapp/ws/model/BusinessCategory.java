package com.shiftapp.ws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BusinessCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column (name="business_id")
	private long businessId;
	
	@Column (name="category_name")
	private String categoryName;
	
	@Column (name="uses")
	private int uses;
	
	public BusinessCategory() {}

	public BusinessCategory(long businessId, String categoryName, int uses) {
		super();
		this.businessId = businessId;
		this.categoryName = categoryName;
		this.uses = uses;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getUses() {
		return uses;
	}

	public void setUses(int uses) {
		this.uses = uses;
	}
}
