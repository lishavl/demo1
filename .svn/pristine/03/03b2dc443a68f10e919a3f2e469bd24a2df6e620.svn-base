package com.pumex.tms.trainingmanagement.dao;

import java.util.List;

import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean;

/**
 * @Author Reshma Manoj.
 * @Date 01/07/2016
 */

public interface TrainingCategoryDaoInterface {

	List<TrainingCategory> getCategory() throws Exception;

	void saveCategory(TrainingCategory trainingCategory) throws Exception;

	long getCategory(String category) throws Exception;

	List<TrainingCategory> FetchMainCategory() throws Exception;

	List<TrainingCategory> FetchSubCategory(long parentid) throws Exception;

	List<TrainingCategoryBean> FetchAllCategories() throws Exception;

	public void deleteCategory(long id) throws Exception;

	TrainingCategory editCategory(long id) throws Exception;

	void updateCategory(TrainingCategory training) throws Exception;

	long getCategoryCount(long id) throws Exception;

	void deleteCategoryandsubcategory(long id) throws Exception;

	String getMainCategory(long pid) throws Exception;

}
