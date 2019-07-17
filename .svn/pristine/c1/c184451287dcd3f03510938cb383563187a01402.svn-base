/*
 * @Author Jinshad P.T.
 */

package com.pumex.tms.home.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.home.bean.DashboardBean;
import com.pumex.tms.home.dao.DashboardDaoInterface;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;

/**
 * Service for dashboard actions
 * 
 * @Author JINSHAD P.T.
 * @Date 13/08/2016
 */

@Service
public class DashboardServiceImpl implements DashboardServiceInterface {

	@Autowired
	DashboardDaoInterface dashboardDaoInterface;

	/*
	 * Method to get upcoming training programs
	 * 
	 * @return List of upcoming programs
	 */
	@Override
	public List<TrainerTopicBean> getUpcomingTrainingPrograms()
			throws Exception {

		return dashboardDaoInterface.getUpcomingTrainingPrograms();
	}

	/*
	 * Method to get completed training programs with attended status
	 * 
	 * @return List of completed programs
	 */
	@Override
	public List<TrainerTopicBean> getCompletedTrainingPrograms()
			throws Exception {

		String[] monthNames = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

		List<TrainerTopicBean> lst = dashboardDaoInterface
				.getCompletedTrainingPrograms();

		for (TrainerTopicBean train : lst) {
			long[] attendedAndNotAttendedCount = dashboardDaoInterface
					.getAttendedAndNotAttendedCount(train.getId());

			train.setAttendedCount(attendedAndNotAttendedCount[0]);
			train.setNotAttendedCount(attendedAndNotAttendedCount[1]);

			Calendar cal = Calendar.getInstance();
			cal.setTime(train.getDate());
			train.setDay(cal.get(Calendar.DAY_OF_MONTH));
			train.setMonth(monthNames[cal.get(Calendar.MONTH)]);
		}

		return lst;
	}

	/*
	 * Method to get dashboard statistics
	 * 
	 * @return dashboard statistics
	 */
	@Override
	public DashboardBean getStatisticsDetails(long userId, long roleId)
			throws Exception {
		return dashboardDaoInterface.getStatisticsDetails(userId, roleId);
	}

	/*
	 * Method to get latest forum thread
	 * 
	 * @return list of latest forum thread
	 */
	@Override
	public List getLatestForumThreads() throws Exception {
		return dashboardDaoInterface.getLatestForumThreads();
	}

	/*
	 * Method to get all tests based on role & userId
	 * 
	 * @return list of tests permitted to logged in user
	 */
	@Override
	public List<TestBean> getAllTests(long userId, long roleId)
			throws Exception {
		return dashboardDaoInterface.getAllTests(userId, roleId);
	}

	/*
	 * Method to get test result
	 * 
	 * @return TestResultBean
	 */
	@Override
	public TestResultBean getTestResult(long testId) throws Exception {
		return dashboardDaoInterface.getTestResult(testId);
	}

	/*
	 * Method to get pending tests of attendee
	 * 
	 * @return list of pending test
	 */
	@Override
	public List<TestBean> getPendingTests(long userId) throws Exception {
		return dashboardDaoInterface.getPendingTests(userId);
	}

	/*
	 * Method to get top 5 trainers
	 * 
	 * @return List of top 5 trainers
	 */
	@Override
	public List getTopTrainers() throws Exception {
		return dashboardDaoInterface.getTopTrainers();
	}

}
