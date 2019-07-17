package com.pumex.tms.trainingmanagement.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean;

/**
 * @Author Reshma Manoj.
 * @Date 01/07/2016
 */

@Transactional
@Repository("TrainingDao")
public class TrainingCategoryDaoImpl extends
		AbstractDao<Long, TrainingCategory> implements
		TrainingCategoryDaoInterface {

	@Override
	public List<TrainingCategory> getCategory() throws Exception {
		// TODO Auto-generated method stub
		List list = getSession().createQuery("from TrainingCategory where status = 1 and parentId =0 order by id desc").list();
		return list;
	}

	@Override
	public void saveCategory(TrainingCategory trainingCategory)
			throws Exception {
		// TODO Auto-generated method stub
		persist(trainingCategory);

	}

	@Override
	public long getCategory(String category) throws Exception {
		long count = (Long) getSession()
				.createQuery(
						"select count(id) from TrainingCategory where category=:category and  status = 1")
				.setString("category", category).uniqueResult();
		return count;
	}

	@Override
	public List<TrainingCategory> FetchMainCategory() throws Exception {
		// TODO Auto-generated method stub
		List list = getSession().createQuery("from TrainingCategory where parentId=0 and status = 1 and istrainingcategory =1 ").list();
		return list;
	}

	@Override
	public List<TrainingCategory> FetchSubCategory(long parentid) throws Exception {
		// TODO Auto-generated method stub
		List list = getSession().createQuery("from TrainingCategory where parentId=:parentid and status = 1 and istrainingcategory =1").setLong("parentid", parentid).list();
		return list;
	}

	@Override
	public List<TrainingCategoryBean> FetchAllCategories() throws Exception {
		// TODO Auto-generated method stub
		List list = getSession().createQuery("select new com.pumex.tms.trainingmanagement.bean.TrainingCategoryBean(a.id, a.category, a.description,"+
			" (select category from TrainingCategory where id=a.parentId)) from TrainingCategory a where a.status = 1 order by a.id desc").list();
		return list;
	}

	@Override
	public void deleteCategory(long id) throws Exception {
		// TODO Auto-generated method stub
		
		getSession().createQuery(" update TrainingCategory  set status = 0 where id = :id")
		.setLong("id", id).executeUpdate();
		
	}
	
	public TrainingCategory editCategory(long id) throws Exception {

		return (TrainingCategory) getSession()
				.createQuery(" from TrainingCategory where id = :id")
				.setLong("id", id).uniqueResult();
		// TODO Auto-generated method stub
	}

	@Override
	public void updateCategory(TrainingCategory training) throws Exception {
		// TODO Auto-generated method stub
		update(training);
		
	}

	@Override
	public long getCategoryCount(long id) throws Exception {
		// TODO Auto-generated method stub
		long count = (Long) getSession()
				.createQuery(
						"select count(id) from TrainingCategory where parentId=:id")
				.setLong("id", id).uniqueResult();

		return count;
	}

	@Override
	public void deleteCategoryandsubcategory(long id) throws Exception {
		// TODO Auto-generated method stub
		
		getSession().createQuery(" update TrainingCategory  set status = 0 where parentId = :id")
		.setLong("id", id).executeUpdate();
		
		getSession().createQuery(" update TrainingCategory  set status = 0 where id = :id")
		.setLong("id", id).executeUpdate();
	}

	@Override
	public String getMainCategory(long pid) throws Exception {
		// TODO Auto-generated method stub
		String category = (String) getSession()
				.createQuery(
						"select category from TrainingCategory where id=:id")
				.setLong("id", pid).uniqueResult();

		return category;
	}
}
