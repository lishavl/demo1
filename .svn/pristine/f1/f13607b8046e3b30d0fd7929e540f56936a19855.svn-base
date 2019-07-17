/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.trainingmanagement.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.Notifications;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.notifications.bean.NotificationBean;
import com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;

@Transactional
@Repository("markAttendanceDao")
public class MarkAttendanceDaoImp extends AbstractDao<Long, TopicTrainer>
		implements MarkAttendanceDao {

	/*
	 * Method for fetch All Training names
	 * 
	 * @return  list as TrainerTopicBean
	 */
	@Override
	public List<TrainerTopicBean> getAllTopicNames(long id) throws Exception {

		List<TrainerTopicBean> lst = getSession()
				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTopicBean("
								+ "a.topic.id,  a.topic.topic)"
								+ " from TopicTrainer a  where a.trainer.userId = :id and a.topic.status in (1,2) and a.topic.startDateTime <=:ctime")
				.setLong("id", id)
				.setParameter("ctime", new Timestamp(new Date().getTime())).list();

		return lst;

	}
    
	/*
	 * Method for fetch All Attendee names
	 * 
	 * @return  list as TopicAttendeeBean
	 */
	@Override
	public List<TopicAttendeeBean> getallAttendees(long id) throws Exception {
		return getSession()
				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean("
								+ "a.attendee.userId,  a.attendee.firstName,a.attendedStatus)"
								+ " from TopicAttendee a where a.topic.id = :id and a.attendedStatus in(0,1) order by a.attendedStatus asc,a.attendee.firstName asc ")
				.setLong("id", id).list();

	}
    
	/*
	 * Method for update attendance
	 * 
	 * @return  status as response
	 */
	@Override
	public void addAttendance(List<Long> ids, long testid) throws Exception {
		getSession()
				.createQuery(
						"update TopicAttendee set attendedStatus=0 where topic.id=:testid")
				.setLong("testid", testid).executeUpdate();

		if (ids != null) {
			getSession()

					.createQuery(
							"update TopicAttendee set attendedStatus=1 where attendee.userId in (:uids) and topic.id=:testid")
					.setParameterList("uids", ids).setLong("testid", testid)
					.executeUpdate();

			getSession()

					.createQuery(
							"delete Notifications where type = 4  and training.id=:testid")
					.setLong("testid", testid).executeUpdate();

			for (Long test : ids) {
				Notifications notifications = new Notifications();
				notifications.setAdminstatus(0);
				notifications.setStatus(0);
				TrainingTopic topic = new TrainingTopic();
				topic.setId(testid);
				notifications.setTraining(topic);
				notifications.setType(4);
				notifications.setUserId(test);
				getSession().save(notifications);
			}

		}
		// TODO Auto-generated method stub

		getSession()

		.createQuery("update TrainingTopic set status=2 where id=:testid")
				.setLong("testid", testid).executeUpdate();
	}

	@Override
	public void cancelAttendance(long id, long testid) throws Exception {
		getSession()
				.createQuery(
						"update TopicAttendee set attendedStatus=0 where attendee.userId=:uid and topic.id=:testid")
				.setLong("uid", id).setLong("testid", testid).executeUpdate();

	}
    
	/*
	 * Method for fetch All Training names
	 * 
	 * @return  list as TrainerTopicBean
	 */
	@Override
	public List<TrainerTopicBean> getAllTopicNamesForAdmin() throws Exception {
		List<TrainerTopicBean> lst = getSession()
				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTopicBean("
								+ "a.topic.id,  a.topic.topic)"
								+ " from TopicTrainer a  where a.topic.status in (1,2) and a.topic.startDateTime <=:ctime")
				.setParameter("ctime", new Timestamp(new Date().getTime())).list();

		return lst;
	}

	@Override
	public void cancelTopicCompletionStatus(long testid, long id)
			throws Exception {

		getSession()

				.createQuery(
						"update TopicAttendee set attendedStatus=0 where attendee.userId=:uids and topic.id=:testid")
				.setLong("uids", id).setLong("testid", testid).executeUpdate();

		// TODO Auto-generated method stub
		getSession()
				.createQuery(
						"update TrainingTopic set status=1 where id=:testid")
				.setLong("testid", testid).executeUpdate();
	}
    
	/*
	 * Method for cancel attendance
	 * 
	 * @return  status as response
	 */
	@Override
	public void cancelAttendaceForAllAttendees(List<Long> list, long testid)
			throws Exception {
		getSession()
				.createQuery(
						"update TopicAttendee set attendedStatus=0 where  attendee.userId in (:uids) and topic.id=:testid")
				.setParameterList("uids", list).setLong("testid", testid)
				.executeUpdate();
		
		getSession()
		.createQuery(
				"update TrainingTopic set status=1 where id=:testid")
		.setLong("testid", testid).executeUpdate();

	}
    
	/*
	 * Method for check date for attendance
	 * 
	 * @return  status as response
	 */
	@Override
	public boolean checkDateForMarkAttendance(long testid) throws Exception {
		// TODO Auto-generated method stub
		
		final SimpleDateFormat monthDayYearformatter = new SimpleDateFormat(
			      "MMMMM dd, yyyy");
        

		Timestamp endDateTime = (Timestamp) getSession()
				.createQuery(
						"select endDateTime from TrainingTopic where id=:testid")
				.setLong("testid", testid).uniqueResult();
		
		Timestamp startDateTime = (Timestamp) getSession()
				.createQuery(
						"select startDateTime from TrainingTopic where id=:testid")
				.setLong("testid", testid).uniqueResult();
		
		
		Date date = new Date(endDateTime.getTime());
		Date tomorrow = new Date(endDateTime.getTime() + (1000 * 60 * 60 * 24));
		Timestamp time = new Timestamp(new Date().getTime());
		
		Date startdte =  new Date(startDateTime.getTime());
		Date currentdate = new Date(time.getTime());
		
		String trainingdate= monthDayYearformatter.format((java.util.Date) endDateTime);
		String nextdaytraining = monthDayYearformatter.format((java.util.Date) tomorrow);
		String currentday = monthDayYearformatter.format((java.util.Date) time);
		
		
		if((currentdate.compareTo(startdte)>0 && currentdate.compareTo(date)<0) || currentday.equals(trainingdate) || currentday.equalsIgnoreCase(nextdaytraining))
			return true;
		else
			return false;
		
//		if (date.compareTo(date1) == 0 || tomorrow.compareTo(date1) == 0) {
//
//			return true;
//		}
//
//		else {
//			return false;

//		}
	}

}
