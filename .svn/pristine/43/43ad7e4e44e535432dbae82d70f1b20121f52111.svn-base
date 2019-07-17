package com.pumex.tms.onlinetest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.OnlineTest;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;
import com.pumex.tms.util.AppConstants;

/**
 * @Author JINSHAD P.T.
 * @Date 12/01/2016
 */
@Transactional
@Repository("testDao")
public class TestManagementDaoImpl extends AbstractDao<Long, OnlineTest>
		implements TestManagementDao {

	/*
	 * Method to get all tests
	 * 
	 * @return list of all tests
	 */
	public List<OnlineTest> getAllTests() throws Exception {
		// TODO Auto-generated method stub
		List lst = getSession().createQuery("from OnlineTest where status>0").list();

		return lst;
	}

	/*
	 * Method to get all tests based on user
	 * 
	 * @return list of available tests
	 */
	public List<OnlineTest> getAllTestsOfUser(long roleId, long userId)
			throws Exception {

		List tests = null;

		if (roleId == AppConstants.USER_ROLES.ADMIN_ROLE) {
			tests = getSession()
					.createQuery("from OnlineTest order by id desc").list();
		} else if (roleId == AppConstants.USER_ROLES.TRAINER_ROLE) {
			tests = getSession()
					.createQuery(
							"select distinct a from OnlineTest  a, TopicTrainer b where a.training.id = b.topic.id  and b.trainer.userId =:userId order by a.id desc")
					.setLong("userId", userId).list();
		} else {
			tests = getSession()
					.createQuery(
							"select distinct a from OnlineTest  a, TopicAttendee b where a.training.id = b.topic.id  and b.attendee.userId =:userId order by a.id desc")
					.setLong("userId", userId).list();
		}

		return tests;
	}

	/*
	 * Method to edit test by id
	 * 
	 * @return test details
	 */
	public OnlineTest editTest(long id) throws Exception {

		return getByKey(id);
		// TODO Auto-generated method stub
	}

	/*
	 * Method to save/update test
	 * 
	 * @return id of created/updated test
	 */
	public long saveOrUpdateTest(OnlineTest test) throws Exception {
		// TODO Auto-generated method stub
		saveOrUpdate(test);
		return test.getId();
	}

	/*
	 * Method to delete test
	 * 
	 * @return nothing
	 */
	public void deleteTest(long id) throws Exception {
		// TODO Auto-generated method stub
		delete(getByKey(id));
	}

	/*
	 * Method to get test by id
	 * 
	 * @return test details
	 */
	@Override
	public OnlineTest getTest(long id) throws Exception {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	/*
	 * Method to get all training programs based on user role and user id
	 * 
	 * @return list of available training programs
	 */
	public List<TrainingScheduleBean> getAllTrainings(long roleId, long userId)
			throws Exception {

		List pgms = null;

		if (roleId == AppConstants.USER_ROLES.ADMIN_ROLE) {
			pgms = getSession()
					.createQuery(
							"select new com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean(id, topic) from TrainingTopic where status!=0")
					.list();
		} else if (roleId == AppConstants.USER_ROLES.TRAINER_ROLE) {
			pgms = getSession()
					.createQuery(
							"select distinct new com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean(a.topic.id, a.topic.topic) from TopicTrainer a where a.trainer.userId=:uid and a.status!=0")
					.setLong("uid", userId).list();
		} else if (roleId == AppConstants.USER_ROLES.ATTENDEE_ROLE) {
			pgms = getSession()
					.createQuery(
							"select distinct new com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean(a.topic.id, a.topic.topic) from TopicAttendee a where a.attendee.userId=:uid and a.status!=0")
					.setLong("uid", userId).list();
		}

		return pgms;

	}

	/*
	 * Method to get all test names and IDs
	 * 
	 * @return list of all tests
	 */
	public List<TestBean> getAllTestNames() throws Exception {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"select new com.pumex.tms.onlinetest.bean.TestBean(id, title) from OnlineTest where status>0")
				.list();
	}

	/*
	 * Method to get all test names and IDs based on user role
	 * 
	 * @return list of available tests
	 */
	public List<TestBean> getAllTestNames(boolean isAdmin, long userId)
			throws Exception {
		// TODO Auto-generated method stub
		if (isAdmin) {
			return getSession()
					.createQuery(
							"select new com.pumex.tms.onlinetest.bean.TestBean(id, title) from OnlineTest where status>0")
					.list();
		} else {

			List ids = getSession()
					.createQuery(
							"select distinct topic.id from TopicTrainer where topic.status>0 and trainer.userId=:uid")
					.setLong("uid", userId).list();

			if (ids != null && ids.size() > 0) {
				return getSession()
						.createQuery(
								"select new com.pumex.tms.onlinetest.bean.TestBean(id, title) from OnlineTest where status>0 and training.id in (:ids)")
						.setParameterList("ids", ids).list();
			} else
				return null;

		}
	}

	/*
	 * Method to change test status
	 * 
	 * @return nothing
	 */
	public void changeTestStatus(long id, int status) throws Exception {

		getSession()
				.createQuery("update OnlineTest set status=:sts where id=:tid")
				.setParameter("sts", status).setParameter("tid", id)
				.executeUpdate();
	}

}
