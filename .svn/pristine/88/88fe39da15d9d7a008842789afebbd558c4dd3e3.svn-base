package com.pumex.tms.onlinetest.dao;

import java.util.List;

import com.pumex.tms.models.OnlineTest;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;

/**
 * @Author JINSHAD P.T.
 * @Date 12/01/2016
 */

public interface TestManagementDao {

	public List<OnlineTest> getAllTests() throws Exception;

	List<OnlineTest> getAllTestsOfUser(long roleId, long userId)
			throws Exception;

	public OnlineTest getTest(long id) throws Exception;

	public long saveOrUpdateTest(OnlineTest user) throws Exception;

	public void deleteTest(long id) throws Exception;

	public List<TrainingScheduleBean> getAllTrainings(long roleId, long userId)
			throws Exception;

	public List<TestBean> getAllTestNames() throws Exception;

	public void changeTestStatus(long id, int status) throws Exception;

	public List<TestBean> getAllTestNames(boolean isAdmin, long userId)
			throws Exception;

}
