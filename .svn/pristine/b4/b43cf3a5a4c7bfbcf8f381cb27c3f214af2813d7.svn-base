package com.pumex.tms.trainingmanagement.service;

import java.util.List;

/**
 * @Author Reshma Manoj.
 * @Date 01/07/2016
 */






import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean;

public interface TrainingCategoryServiceInterface {

	List<TrainingCategory> getCategory() throws Exception;

	void addCategory(TrainingCategoryBean trainingCategory) throws Exception;

	long getCategory(String category) throws Exception;

	void addSubCategory(TrainingCategoryBean trainingCategory, long pid) throws Exception;

	List<TrainingCategory> FetchMainCategory() throws Exception;

	List<TrainingCategory> FetchSubCategory(long parentid) throws Exception;

	List<TrainingCategoryBean> getAllCategories() throws Exception;

	void deleteCategory(long id) throws Exception;

	TrainingCategory editCategory(long id) throws Exception;

	void updateCategory(TrainingCategory training) throws Exception;

	long getCategoryCount(long id) throws Exception;

	void deleteCategoryandsubcategory(long id) throws Exception;

	String getMainCategory(long pid) throws Exception;

}
