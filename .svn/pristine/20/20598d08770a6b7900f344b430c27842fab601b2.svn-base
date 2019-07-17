/*
 * @Author Jinshad P.T.
 */

package com.pumex.tms.home.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.home.bean.DashboardBean;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.util.AppConstants;

/**
 * DAO for dashboard actions
 * 
 * @Author JINSHAD P.T.
 * @Date 13/08/2016
 */

@Transactional
@Repository
public class DashboardDaoImpl extends AbstractDao<Long, TrainingTopic>
		implements DashboardDaoInterface {

	/*
	 * Method to get upcoming training programs
	 * 
	 * @return List of upcoming programs
	 */
	@Override
	public List<TrainerTopicBean> getUpcomingTrainingPrograms()
			throws Exception {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainerTopicBean(id,topic,description,startDateTime) from TrainingTopic where status=1 and startDateTime>:ctime order by startDateTime asc")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.setMaxResults(5).list();

	}

	/*
	 * Method to get completed training programs
	 * 
	 * @return List of completed programs
	 */
	@Override
	public List<TrainerTopicBean> getCompletedTrainingPrograms()
			throws Exception {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainerTopicBean(id,topic,description,startDateTime) from TrainingTopic where status=2 order by startDateTime desc")
				.setMaxResults(5).list();

	}

	/*
	 * Method to get dashboard statistics
	 * 
	 * @return dashboard statistics
	 */
	@Override
	public DashboardBean getStatisticsDetails(long userId, long roleId)
			throws Exception {
		// TODO Auto-generated method stub

		Calendar cal = Calendar.getInstance();

		DashboardBean bean = new DashboardBean();

		long upcomingCount = (long) getSession()
				.createQuery(
						"select count(id) from TrainingTopic where status=1 and startDateTime>:ctime")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.uniqueResult();

		long completedPgmCount = (long) getSession().createQuery(
				"select count(id) from TrainingTopic where status=2")
				.uniqueResult();

		List monthlyPgmList = getSession()
				.createQuery(
						"select id from TrainingTopic where month(startDateTime)=:mn and year(startDateTime)=:yr")
				.setParameter("mn", cal.get(Calendar.MONTH) + 1)
				.setParameter("yr", cal.get(Calendar.YEAR)).list();

		long monthlyAttended = 0;

		if (monthlyPgmList.size() > 0)
			monthlyAttended = (long) getSession()
					.createQuery(
							"select count(id) from TopicAttendee where topic.id in (:tids) and attendedStatus=1")
					.setParameterList("tids", monthlyPgmList).uniqueResult();

		long totalPgmCount = (long) getSession().createQuery(
				"select count(id) from TrainingTopic").uniqueResult();

		long totalUsersCount = (long) getSession().createQuery(
				"select count(userId) from UserDetails where status != -1")
				.uniqueResult();

		if (roleId == AppConstants.USER_ROLES.ATTENDEE_ROLE) {

			long attendedTests = (long) getSession()
					.createQuery(
							"select count(a.id) from AttendeeTest a where a.status=2 and a.attendee.userId =:userId")
					.setLong("userId", userId).uniqueResult();

			long pendingTests = (long) getSession()
					.createQuery(
							"select count(a.id) from OnlineTest  a, TopicAttendee b where a.training.id = b.topic.id  and b.attendee.userId =:userId and  a.status=:sts and a.id not in (select c.test.id from AttendeeTest c where c.status=2 and c.attendee.userId=:userId)")
					.setLong("userId", userId)
					.setInteger("sts", AppConstants.test_statuses.PUBLISHED)
					.uniqueResult();

			bean.setAttendedTests(attendedTests);
			bean.setPendingTests(pendingTests);

		}

		bean.setUpcomingPgmCount(upcomingCount);
		bean.setCompletedPgmCount(completedPgmCount);
		bean.setMonthlyPgmCount(monthlyPgmList.size());
		bean.setMonthlyAttendedPgmCount(monthlyAttended);
		bean.setTotalPgmCount(totalPgmCount);
		bean.setTotalUsersCount(totalUsersCount);

		return bean;

	}

	/*
	 * Method to get attended and not attended count
	 * 
	 * @return attended and not attended count
	 */
	@Override
	public long[] getAttendedAndNotAttendedCount(long topicId) throws Exception {
		long[] attendedAndNotAttendedCount = new long[2];

		attendedAndNotAttendedCount[0] = (long) getSession()
				.createQuery(
						"select count(id) from TopicAttendee where topic.id=:tid and attendedStatus=1")
				.setLong("tid", topicId).uniqueResult();

		attendedAndNotAttendedCount[1] = (long) getSession()
				.createQuery(
						"select count(id) from TopicAttendee where topic.id=:tid and attendedStatus=0")
				.setLong("tid", topicId).uniqueResult();
		return attendedAndNotAttendedCount;
	}

	/*
	 * Method to get latest forum thread
	 * 
	 * @return list of latest forum thread
	 */
	@Override
	public List getLatestForumThreads() throws Exception {

		return getSession()
				.createQuery(
						"select new com.pumex.tms.forum.bean.ForumBean(id, category.category,subject,postedon,likes,user.userId) from ForumDetails where parentid=0 order by postedon desc")
				.setMaxResults(5).list();
	}

	/*
	 * Method to get all tests based on role & userId
	 * 
	 * @return list of tests permitted to logged in user
	 */
	@Override
	public List<TestBean> getAllTests(long userId, long roleId)
			throws Exception {

		List<TestBean> tests = null;
		if (roleId == 1) {
			tests = getSession()
					.createQuery(
							"select new com.pumex.tms.onlinetest.bean.TestBean("
									+ "a.id, a.title)"
									+ " from OnlineTest a where a.status=:sts order by a.id desc")
					.setInteger("sts", AppConstants.test_statuses.PUBLISHED)
					.list();
		} else if (roleId == 2) {
			tests = getSession()
					.createQuery(
							"select new com.pumex.tms.onlinetest.bean.TestBean("
									+ "a.id, a.title)"
									+ " from OnlineTest  a, TopicTrainer b where a.training.id = b.topic.id  and b.trainer.userId =:userId and  a.status=:sts order by a.id desc")
					.setLong("userId", userId)
					.setInteger("sts", AppConstants.test_statuses.PUBLISHED)
					.list();
		} else {
			tests = getSession()
					.createQuery(
							"select new com.pumex.tms.onlinetest.bean.TestBean("
									+ "a.id, a.title)"
									+ " from OnlineTest  a, TopicAttendee b where a.training.id = b.topic.id  and b.attendee.userId =:userId and  a.status=:sts order by a.id desc")
					.setLong("userId", userId)
					.setInteger("sts", AppConstants.test_statuses.PUBLISHED)
					.list();
		}

		return tests;
	}

	/*
	 * Method to get test result
	 * 
	 * @return TestResultBean
	 */
	@Override
	public TestResultBean getTestResult(long testId) throws Exception {

		TestResultBean bean = new TestResultBean();

		long totalPassed = (long) getSession()
				.createQuery(
						"select count(id) from AttendeeTest a where a.status=2 and a.test.id = :id and a.mark >= a.test.passMark")
				.setLong("id", testId).uniqueResult();

		long totalFailed = (long) getSession()
				.createQuery(
						"select count(id) from AttendeeTest a where a.status=2 and a.test.id = :id and a.mark < a.test.passMark")
				.setLong("id", testId).uniqueResult();

		long totalUsers = (long) getSession()
				.createQuery(
						"select count(a.id) from TopicAttendee a, OnlineTest b where a.topic.id = b.training.id  and b.id =:tid")
				.setLong("tid", testId).uniqueResult();

		bean.setPassnumber(totalPassed);
		bean.setFailednumber(totalFailed);
		bean.setNotAttendedNumber(totalUsers - totalPassed - totalFailed);

		return bean;
	}

	/*
	 * Method to get pending tests of attendee
	 * 
	 * @return list of pending test
	 */
	public List<TestBean> getPendingTests(long userId) throws Exception {

		List<TestBean> tests = null;

		tests = getSession()
				.createQuery(
						"select new com.pumex.tms.onlinetest.bean.TestBean("
								+ "a.id, a.title, a.training.topic,a.description)"
								+ " from OnlineTest  a, TopicAttendee b where a.training.id = b.topic.id  and b.attendee.userId =:userId and  a.status=:sts and a.id not in (select c.test.id from AttendeeTest c where c.status=2 and c.attendee.userId=:userId)")
				.setLong("userId", userId)
				.setInteger("sts", AppConstants.test_statuses.PUBLISHED)
				.setMaxResults(5).list();

		return tests;

	}

	/*
	 * Method to get top 5 trainers
	 * 
	 * @return List of top 5 trainers
	 */
	public List getTopTrainers() throws Exception {

		return getSession()
				.createQuery(
						"select concat(a.trainer.firstName,' ',a.trainer.lastName) as name, ((sum(a.feedback)*1.0)/count(a.id)) as rating, count(a.id)"
								+ " from AttendeeFeedback  a group by a.trainer.id order by rating desc")
				.setMaxResults(5).list();

	}

}
