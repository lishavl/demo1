package com.pumex.tms.report.dao;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.report.bean.PerformanceAnalysisBean;

/**
 * DAO for Performance analysis
 * 
 * @Author JINSHAD P.T.
 * @Date 31/10/2016
 */

@Transactional
@Repository
public class PerformanceAnalysisDaoImpl extends
		AbstractDao<Long, TrainingTopic> implements
		PerformanceAnalysisDaoInterface {

	/*
	 * Method to get all tests based on role & userId
	 * 
	 * @return list of tests permitted to logged in user
	 */
	@Override
	public List getAllTests(long attendeeId) throws Exception {

		List tests = null;
		tests = getSession()
				.createQuery(
						"select distinct a.test from AttendeeTest a where a.status=2 and a.attendee.userId =:userId order by a.id desc")
				.setLong("userId", attendeeId).list();

		return tests;
	}

	/*
	 * Method to get upcoming training programs
	 * 
	 * @return List of upcoming programs
	 */
	@Override
	public List getAllTrainingPrograms(long attendeeId) throws Exception {
		// TODO Auto-generated method stub
		List<PerformanceAnalysisBean> lst = getSession()
				.createQuery(
						"select distinct new com.pumex.tms.report.bean.PerformanceAnalysisBean(a.topic,a.attendedStatus) from TopicAttendee a where a.topic.status!=0 and a.attendee.userId=:uid and a.attendedStatus>=0 order by a.topic.startDateTime desc")
				.setLong("uid", attendeeId).list();

		for (PerformanceAnalysisBean topicObj : lst) {
			if(topicObj.getAttendedStatus()==1) {
				long count = (long) getSession()
						.createQuery(
								"select count(id) from AttendeeFeedback a where a.topic.id=:tid and a.attendee.userId=:uid")
						.setLong("tid", topicObj.getTopic().getId())
						.setLong("uid", attendeeId).uniqueResult();
				if (count > 0) {
					topicObj.setFeedbacked(true);
				}
			}
		}

		return lst;

	}

	/*
	 * Method to get upcoming training programs
	 * 
	 * @return List of upcoming programs
	 */
	@Override
	public PerformanceAnalysisBean getProgramDetails(long attendeeId)
			throws Exception {
		// TODO Auto-generated method stub

		Calendar cal = Calendar.getInstance();

		PerformanceAnalysisBean bean = new PerformanceAnalysisBean();

		long upcomingCount = (long) getSession()
				.createQuery(
						"select count(a.id) from TopicAttendee a where a.topic.status=1 and a.attendee.userId=:uid and a.topic.startDateTime>:ctime and a.attendedStatus=0")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.setLong("uid", attendeeId).uniqueResult();

		long completedCount = (long) getSession()
				.createQuery(
						"select count(a.id) from TopicAttendee a where a.topic.status=2 and a.attendee.userId=:uid")
				.setLong("uid", attendeeId).uniqueResult();

		long attendedCount = (long) getSession()
				.createQuery(
						"select count(a.id) from TopicAttendee a where a.topic.status=2 and a.attendee.userId=:uid and a.attendedStatus=1")
				.setLong("uid", attendeeId).uniqueResult();

		long totalPgmCount = (long) getSession()
				.createQuery(
						"select count(a.id) from TopicAttendee a where a.topic.status!=0 and a.attendee.userId=:uid")
				.setLong("uid", attendeeId).uniqueResult();

		bean.setTotalPrograms(totalPgmCount);
		bean.setUpcomingPrograms(upcomingCount);
		bean.setAttendedPrograms(attendedCount);
		bean.setCompletedPrograms(completedCount);

		return bean;
	}

	/*
	 * Method to get upcoming training programs
	 * 
	 * @return List of upcoming programs
	 */
	@Override
	public List getFeedBacks(long userId, long topicId) throws Exception {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"select distinct new com.pumex.tms.usermanagement.bean.FeedBackBean("
								+ " a.topic.id, a.topic.topic,a.trainer.userId,a.trainer.firstName,a.trainer.lastName,a.feedback,a.whyAppropriate,a.comment,"
								+ "a.followupSessionNeeded,a.demoNeeded	,a.status,a.attendee.userId,a.attendee.firstName,a.attendee.lastName)"
								+ " from AttendeeFeedback a  where a.topic.id = :topicid and a.attendee.userId = :userId")
				.setLong("topicid", topicId).setLong("userId", userId).list();

	}

}
