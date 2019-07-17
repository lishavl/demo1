package com.pumex.tms.onlinetest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.OnlineTest;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.onlinetest.dao.TestManagementDao;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;

@Service("testservice")
public class TestManagementServiceImpl implements TestManagementService {

	@Autowired
	TestManagementDao testManagementDao;

	/*
	 * Method to get all tests
	 * 
	 * @return list of all tests
	 */
	public List<OnlineTest> getAllTests() throws Exception {
		// TODO Auto-generated method stub
		return testManagementDao.getAllTests();
	}

	/*
	 * Method to get all tests based on user
	 * 
	 * @return list of available tests
	 */
	public List<OnlineTest> getAllTestsOfUser(long roleId, long userId)
			throws Exception {
		// TODO Auto-generated method stub
		return testManagementDao.getAllTestsOfUser(roleId, userId);
	}

	/*
	 * Method to get test by id
	 * 
	 * @return test details
	 */
	public OnlineTest getTestById(long id) throws Exception {
		OnlineTest user = testManagementDao.getTest(id);
		return user;
	}

	/*
	 * Method to edit test by id
	 * 
	 * @return test details
	 */
	public TestBean editTest(long id) throws Exception {
		OnlineTest test = testManagementDao.getTest(id);
		TestBean bean = new TestBean();
		bean.setTestId(test.getId());
		bean.setTitle(test.getTitle());
		bean.setDescription(test.getDescription());
		bean.setNumberOfQuestions(test.getNumberOfQuestions());
		bean.setPassMark(test.getPassMark());
		bean.setCorrectAnswerMark(test.getCorrectAnswerMark());
		bean.setWrongAnswerMark(test.getWrongAnswerMark());
		bean.setNotAttendedMark(test.getNotAttendedMark());
		bean.setTimeMinutes(test.getTimeMinutes());
		bean.setStatus(1);
		bean.setTrainingId(test.getTraining().getId());

		return bean;
	}

	/*
	 * Method to save/update test
	 * 
	 * @return id of created/updated test
	 */
	public long saveOrUpdateTest(TestBean test) throws Exception {

		OnlineTest testMdl;
		if (test.getTestId() != 0) {
			testMdl = testManagementDao.getTest(test.getTestId());
		} else {
			testMdl = new OnlineTest();
			testMdl.setStatus(1);
		}

		testMdl.setTitle(test.getTitle());
		testMdl.setDescription(test.getDescription());
		testMdl.setNumberOfQuestions(test.getNumberOfQuestions());
		testMdl.setPassMark(test.getPassMark());
		testMdl.setCorrectAnswerMark(test.getCorrectAnswerMark());
		testMdl.setWrongAnswerMark(test.getWrongAnswerMark());
		testMdl.setNotAttendedMark(test.getNotAttendedMark());
		testMdl.setTimeMinutes(test.getTimeMinutes());
		testMdl.setTraining(new TrainingTopic(test.getTrainingId()));

		return testManagementDao.saveOrUpdateTest(testMdl);
		// TODO Auto-generated method stub

	}

	/*
	 * Method to delete test
	 * 
	 * @return nothing
	 */
	public void deleteTest(long id) throws Exception {
		// TODO Auto-generated method stub
		testManagementDao.deleteTest(id);
	}

	/*
	 * Method to get all training programs based on user role and user id
	 * 
	 * @return list of available training programs
	 */
	public List<TrainingScheduleBean> getAllTrainings(long roleId, long userId)
			throws Exception {
		// TODO Auto-generated method stub
		return testManagementDao.getAllTrainings(roleId, userId);
	}

	/*
	 * Method to get all tests
	 * 
	 * @return list of all tests
	 */
	@Override
	public List<TestBean> getAllTestNames() throws Exception {
		// TODO Auto-generated method stub
		return testManagementDao.getAllTestNames();
	}

	/*
	 * Method to get all test names and IDs based on user role
	 * 
	 * @return list of available tests
	 */
	@Override
	public List<TestBean> getAllTestNames(boolean isAdmin, long userId)
			throws Exception {
		// TODO Auto-generated method stub
		return testManagementDao.getAllTestNames(isAdmin, userId);
	}

	/*
	 * Method to change test status
	 * 
	 * @return nothing
	 */
	public void changeTestStatus(long id, int status) throws Exception {
		// TODO Auto-generated method stub
		testManagementDao.changeTestStatus(id, status);
	}

}
