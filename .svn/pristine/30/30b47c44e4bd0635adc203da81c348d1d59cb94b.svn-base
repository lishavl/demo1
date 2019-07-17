/*
 * @Author Jinshad P.T.
 */

package com.pumex.tms.home.dao;

import java.util.List;

import com.pumex.tms.home.bean.DashboardBean;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;

public interface DashboardDaoInterface {

	List<TrainerTopicBean> getUpcomingTrainingPrograms() throws Exception;

	List<TrainerTopicBean> getCompletedTrainingPrograms() throws Exception;

	DashboardBean getStatisticsDetails(long userId, long roleId)
			throws Exception;

	long[] getAttendedAndNotAttendedCount(long topicId) throws Exception;

	List getLatestForumThreads() throws Exception;

	List<TestBean> getAllTests(long userId, long roleId) throws Exception;

	TestResultBean getTestResult(long testId) throws Exception;

	List<TestBean> getPendingTests(long userId) throws Exception;

	List getTopTrainers() throws Exception;

}
