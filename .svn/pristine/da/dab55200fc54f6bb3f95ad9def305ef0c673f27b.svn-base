package com.pumex.tms.trainingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean;
import com.pumex.tms.trainingmanagement.dao.TrainingCategoryDaoInterface;

/**
 * @Author Reshma Manoj.
 * @Date 01/07/2016
 */

@Service("trainingservice")
public class TrainingCategoryServiceImpl implements
		TrainingCategoryServiceInterface {

	@Autowired
	TrainingCategoryDaoInterface trainingCategoryDaoInterface;

	@Override
	public List<TrainingCategory> getCategory() throws Exception {
		// TODO Auto-generated method stub
		return trainingCategoryDaoInterface.getCategory();
	}

	@Override
	public void addCategory(TrainingCategoryBean training) throws Exception {
		// TODO Auto-generated method stub

		TrainingCategory trainingCategory = new TrainingCategory();
		trainingCategory.setCategory(training.getCategory());
		trainingCategory.setDescription(training.getDescription());
		trainingCategory.setIstrainingcategory(training.getIstrainingcategory());
		trainingCategory.setIsforumcategory(training.getIsforumcategory());
		trainingCategory.setStatus(1);
		trainingCategoryDaoInterface.saveCategory(trainingCategory);

	}

	@Override
	public long getCategory(String category) throws Exception {
		// TODO Auto-generated method stub
		return trainingCategoryDaoInterface.getCategory(category);
	}

	@Override
	public void addSubCategory(TrainingCategoryBean training, long pid) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Parentid in Service----->"+pid);
		TrainingCategory trainingCategory = new TrainingCategory();
		trainingCategory.setCategory(training.getCategory());
		trainingCategory.setDescription(training.getDescription());
		trainingCategory.setIstrainingcategory(training.getIstrainingcategory());
		trainingCategory.setIsforumcategory(training.getIsforumcategory());
		trainingCategory.setStatus(1);
		trainingCategory.setParentId(pid);
		trainingCategoryDaoInterface.saveCategory(trainingCategory);
		
	}


	@Override
	public List<TrainingCategory> FetchMainCategory() throws Exception {
		// TODO Auto-generated method stub
		return trainingCategoryDaoInterface.FetchMainCategory();
	}

	@Override
	public List<TrainingCategory> FetchSubCategory(long parentid)
			throws Exception {
		// TODO Auto-generated method stub
		return trainingCategoryDaoInterface.FetchSubCategory(parentid);
	}

	@Override
	public List<TrainingCategoryBean> getAllCategories() throws Exception {
		// TODO Auto-generated method stub
		return trainingCategoryDaoInterface.FetchAllCategories();
	}

	@Override
	public void deleteCategory(long id) throws Exception {
		// TODO Auto-generated method stub
		
		trainingCategoryDaoInterface.deleteCategory(id);
		
	}

	@Override
	public TrainingCategory editCategory(long id) throws Exception {
			TrainingCategory cat= trainingCategoryDaoInterface.editCategory(id);
			return cat;
		}

	@Override
	public void updateCategory(TrainingCategory training) throws Exception {
		// TODO Auto-generated method stub
		
		trainingCategoryDaoInterface.updateCategory(training);
		
	}

	@Override
	public long getCategoryCount(long id) throws Exception {
		// TODO Auto-generated method stub
		return trainingCategoryDaoInterface.getCategoryCount(id);
	}

	@Override
	public void deleteCategoryandsubcategory(long id) throws Exception {
		// TODO Auto-generated method stub
		trainingCategoryDaoInterface.deleteCategoryandsubcategory(id);
	}

	@Override
	public String getMainCategory(long pid) throws Exception {
		// TODO Auto-generated method stub
		return trainingCategoryDaoInterface.getMainCategory(pid);
	}

}
