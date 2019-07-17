package com.pumex.tms.trainingmanagement.bean;

import com.pumex.tms.models.TrainingCategory;

public class TrainingCategoryBean {

	private long id;
	private long parentId;
	private String category;
	private String description;
	private int status;
	private String mainCategory;
	private int istrainingcategory;
	private int isforumcategory;
	

	public TrainingCategoryBean(long id, String category, String description,
			String mainCategory) {
		super();
		this.id = id;
		this.category = category;
		this.description = description;
		this.mainCategory = mainCategory;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public TrainingCategoryBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public int getIstrainingcategory() {
		return istrainingcategory;
	}

	public void setIstrainingcategory(int istrainingcategory) {
		this.istrainingcategory = istrainingcategory;
	}

	public int getIsforumcategory() {
		return isforumcategory;
	}

	public void setIsforumcategory(int isforumcategory) {
		this.isforumcategory = isforumcategory;
	}

	public TrainingCategoryBean(long id, long parentId, String category,
			String description, int status) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.category = category;
		this.description = description;
		this.status = status;
	}
    
	
	@Override
	public String toString() {
		return "TrainingCategory [id=" + id + ", parentId=" + parentId
				+ ", category=" + category + ", description=" + description
				+ ", status=" + status + "]";
	}

	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

}
