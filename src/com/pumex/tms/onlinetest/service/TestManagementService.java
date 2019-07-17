package com.pumex.tms.onlinetest.service;

import java.util.List;

import com.pumex.tms.models.OnlineTest;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;

public interface TestManagementService {

	List<OnlineTest> getAllTests() throws Exception;

	List<OnlineTest> getAllTestsOfUser(long roleId, long userId)
			throws Exception;

	List<TestBean> getAllTestNames() throws Exception;

	OnlineTest getTestById(long id) throws Exception;

	TestBean editTest(long id) throws Exception;

	long saveOrUpdateTest(TestBean test) throws Exception;

	void deleteTest(long id) throws Exception;

	void changeTestStatus(long id, int status) throws Exception;

	List<TrainingScheduleBean> getAllTrainings(long roleId, long userId) throws Exception;

	List<TestBean> getAllTestNames(boolean isAdmin, long userId)
			throws Exception;

}
