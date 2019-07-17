package com.pumex.tms.trainingmanagement.bean;

import java.util.List;

public class CategoryBean {
	private List<TrainingCategoryBean> trainingCategory;
	private String maincategory;
	
	
	public CategoryBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CategoryBean(List<TrainingCategoryBean> trainingCategory,
			String maincategory) {
		super();
		this.trainingCategory = trainingCategory;
		this.maincategory = maincategory;
	}
	


	public CategoryBean(List<TrainingCategoryBean> trainingCategory) {
		super();
		this.trainingCategory = trainingCategory;
	}


	public List<TrainingCategoryBean> getTrainingCategory() {
		return trainingCategory;
	}


	public void setTrainingCategory(List<TrainingCategoryBean> trainingCategory) {
		this.trainingCategory = trainingCategory;
	}


	public String getMaincategory() {
		return maincategory;
	}


	public void setMaincategory(String maincategory) {
		this.maincategory = maincategory;
	}


	@Override
	public String toString() {
		return "CategoryBean [trainingCategory=" + trainingCategory
				+ ", maincategory=" + maincategory + "]";
	}
	
	
	
	
	 
	
	

}
