package com.pumex.tms.onlinetest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.OnlineTest;
import com.pumex.tms.models.Question;
import com.pumex.tms.util.AppConstants;

/**
 * @Author JINSHAD P.T.
 * @Date 15/07/2016
 */
@Transactional
@Repository("questionDao")
public class QuestionManagementDaoImpl extends AbstractDao<Long, Question>
		implements QuestionManagementDao {

	/*
	 * Method to get all questions under test id
	 * 
	 * @return list of questions
	 */
	public List<Question> getAllQuestions(long testId) throws Exception {
		// TODO Auto-generated method stub

		String criteria = "";
		if (testId > 0)
			criteria = " where test.id=" + testId;

		List lst = getSession().createQuery("from Question " + criteria).list();

		// getSession().evict(lst);
		return lst;
	}

	/*
	 * Method to save/update question
	 * 
	 * @return response with save/update status
	 */
	public void saveOrUpdateQuestion(Question question) throws Exception {
		// TODO Auto-generated method stub
		saveOrUpdate(question);
	}

	/*
	 * Method to delete question
	 * 
	 * @return nothing
	 */
	public void deleteQuestion(long id) throws Exception {
		// TODO Auto-generated method stub

		Object testId = getSession()
				.createQuery(
						"select a.test.id from Question a where a.id=:id and a.test.status=:sts")
				.setLong("id", id)
				.setInteger("sts", AppConstants.test_statuses.SUBMITTED)
				.uniqueResult();

		if (testId != null) {
			getSession()
					.createQuery(
							"update OnlineTest set status=:sts where id=:id").setInteger("sts", AppConstants.test_statuses.CREATED)
					.setLong("id", (long) testId).executeUpdate();
		}
		
		
		delete(getByKey(id));
		

	}

	/*
	 * Method to get question details from question id
	 * 
	 * @return QuestionBean
	 */
	@Override
	public Question getQuestion(long id) throws Exception {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	/*
	 * Method to check questions are completed or more questions are allowed
	 * 
	 * @return true/false
	 */
	public boolean isQuestionAllowed(long questionId, long testId)
			throws Exception {
		// TODO Auto-generated method stub

		OnlineTest test = (OnlineTest) getSession().load(OnlineTest.class,
				testId);

		String criteria = " where test.id=" + testId;
		if (questionId > 0)
			criteria += " and id !=" + questionId;

		long count = (long) getSession().createQuery(
				"select count(id) from Question " + criteria).uniqueResult();

		int num = test.getNumberOfQuestions();

		if (count >= num)
			return false;
		else
			return true;

	}

	/*
	 * Method to check test is ready for submit
	 * 
	 * @return true/false
	 */
	public boolean isTestSubmitable(long testId) throws Exception {

		OnlineTest test = (OnlineTest) getSession().load(OnlineTest.class,
				testId);

		if (test.getStatus() == AppConstants.test_statuses.CREATED) {
			String criteria = " where test.id=" + testId;

			long count = (long) getSession().createQuery(
					"select count(id) from Question " + criteria)
					.uniqueResult();

			int num = test.getNumberOfQuestions();

			if (count >= num)
				return true;
			else
				return false;
		} else
			return false;

	}

	/*
	 * Method to get test status
	 * 
	 * @return status
	 */
	public int getTestStatus(long testId) throws Exception {

		OnlineTest test = (OnlineTest) getSession().load(OnlineTest.class,
				testId);
		return test.getStatus();

	}

	/*
	 * Method to submit a test
	 * 
	 * @return nothing
	 */
	public void submitTest(long testId) throws Exception {

		getSession()
				.createQuery("update OnlineTest set status=:sts where id=:tid")
				.setParameter("sts", AppConstants.test_statuses.SUBMITTED)
				.setParameter("tid", testId).executeUpdate();

	}

}
