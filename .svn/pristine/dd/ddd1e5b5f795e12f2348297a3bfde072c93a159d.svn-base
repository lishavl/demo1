/*
 * @Author *** Reshma Manoj ***
 */

package com.pumex.tms.notifications.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.Notifications;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.notifications.bean.NotificationBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean;
import com.pumex.tms.usermanagement.bean.FeedBackBean;

@Transactional
@Repository("NotificationDao")
public class NotificationDaoImpl extends AbstractDao<Long, Notifications>
		implements NotificationDaoInterface {

	@Override
	public List<NotificationBean> getTodaysTrainingList() throws Exception {
		// TODO Auto-generated method stub
		Date today = new Date();
		// System.out.println("Today----->" + today);
		Date tomorrow = new Date();
		tomorrow.setDate(today.getDate() + 1);
		// System.out.println("tomorrow----->" + tomorrow);

		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(b.id,c.userId,b.topic,b.startDateTime,c.firstName,c.lastName,c.email)"
								+ " from TopicAttendee a join a.topic b join a.attendee c where b.status=1 and "
								+ "b.startDateTime >=:ctime and b.startDateTime <:stime  order by b.startDateTime asc")
				.setParameter("ctime", today).setParameter("stime", tomorrow)
				.list();

		return list;
	}

	@Override
	public List<NotificationBean> getTodaysEndTrainingList() throws Exception {
		// TODO Auto-generated method stub

		Date today = new Date();
		Date tomorrow = new Date();
		tomorrow.setDate(today.getDate() + 1);

		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(b.id,c.userId,b.topic,b.endDateTime,c.firstName,c.lastName,c.email)"
								+ " from TopicTrainer a join a.topic b join a.trainer c where b.status != 0 and "
								+ "b.endDateTime >=:ctime and b.endDateTime <:stime  order by b.endDateTime asc")
				.setParameter("ctime", today).setParameter("stime", tomorrow)
				.list();

		for (NotificationBean endtraininglist : list) {

			String edatetime = endtraininglist.getSdate().toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = sdf.parse(edatetime);
			
			Calendar c = Calendar.getInstance();
			c.setTime(date1);
			c.add(Calendar.DATE, 1);
			Date edate = c.getTime();
			endtraininglist.setSdate(edate);

		}

		return list;
	}

	@Override
	public List<NotificationBean> getPendingTestList(long id) throws Exception {
		// TODO Auto-generated method stub
		List<NotificationBean> list1 = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.id,a.training.id,a.title,b.attendee.userId,b.attendee.firstName,b.attendee.lastName,b.attendee.email)"
								+ "from OnlineTest a,TopicAttendee b"
								+ " where b.topic.id = a.training.id and"
								+ " b.attendedStatus=1 and " + "a.id = :id ")
				.setLong("id", id).list();

		return list1;
	}

	@Override
	public List<NotificationBean> getPendingFeedbackList(long topicid)
			throws Exception {
		// TODO Auto-generated method stub
		List<NotificationBean> list2 = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.topic.id,a.attendee.userId,a.topic.topic,a.attendee.firstName,a.attendee.lastName,a.attendee.email)"
								+ "from TopicAttendee a"
								+ " where a.topic.id = :topicid and"
								+ " a.attendedStatus=1 and a.attendee.userId not in(select c.attendee.userId from AttendeeFeedback c) "
								+ "and a.topic.id not in(select c.topic.id from AttendeeFeedback c)")
				.setLong("topicid", topicid).list();

		return list2;
	}

	@Override
	public void saveNotification(Notifications testnotifications)
			throws Exception {
		// TODO Auto-generated method stub

		persist(testnotifications);

	}

	@Override
	public List<NotificationBean> getgetTestList() throws Exception {
		// TODO Auto-generated method stub
		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.id,a.userId,a.training.id,a.status,b.title)"
								+ "from Notifications a , OnlineTest b where a.type = 3 and"
								+ " a.status=1 and b.id = a.training.id")
				.list();

		return list;
	}

	@Override
	public List<NotificationBean> getgetTestList(long userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getTrainers(long topicid) throws Exception {
		// TODO Auto-generated method stub

		List<Long> list = getSession()
				.createQuery(
						"select a.trainer.id from TopicTrainer a where a.topic.id = :topicid ")
				.setLong("topicid", topicid).list();

		return list;
	}

	@Override
	public List<NotificationBean> getFeedbacks(long userId) throws Exception {
		// TODO Auto-generated method stub

		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.training.id,b.attendee.userId,a.training.topic,b.attendee.firstName,"
								+ "b.attendee.lastName,a.status)"
								+ "from Notifications a ,AttendeeFeedback b where a.type = 2 and"
								+ " a.status=0 and a.training.id = b.topic.id and b.trainer.userId = :userId and a.userId = b.attendee.userId")
				.setLong("userId", userId).list();

		return list;
	}

	@Override
	public List<NotificationBean> getTrainings(long userId) throws Exception {
		// TODO Auto-generated method stub
		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.training.id,a.training.topic,a.training.startDateTime,a.status)"
								+ "from Notifications a  where a.type = 1 and"
								+ " a.status=0 and a.userId = :userId")
				.setLong("userId", userId).list();

		return list;
	}

	@Override
	public void saveNotifications(Notifications notifications) throws Exception {
		// TODO Auto-generated method stub
		persist(notifications);

	}

	@Override
	public void deleteNotification(long trainingId, long userId)
			throws Exception {
		// TODO Auto-generated method stub

		getSession()
				.createQuery(
						"Delete from Notifications where training.id = :trainingId and userId = :userId")
				.setLong("trainingId", trainingId).setLong("userId", userId)
				.executeUpdate();

	}

	@Override
	public long getTrainingId(long testid) throws Exception {
		// TODO Auto-generated method stub
		long pid = (long) getSession()
				.createQuery(
						"select training.id from OnlineTest where id = :testid")
				.setLong("testid", testid).uniqueResult();
		return pid;
	}

	@Override
	public List<NotificationBean> getTrainerTrainingList(long userId)
			throws Exception {
		// TODO Auto-generated method stub
		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.id,a.training.id,a.training.topic,a.training.startDateTime,a.status,a.type)"
								+ "from Notifications a  where a.type = 1 and"
								+ " a.status=0 and a.userId = :userId")
				.setLong("userId", userId).list();

		return list;
	}

	@Override
	public List<NotificationBean> getTrainerFeedbackList(long userId)
			throws Exception {
		// TODO Auto-generated method stub
		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.id,a.training.id,b.attendee.userId,a.training.topic,b.attendee.firstName,"
								+ "b.attendee.lastName,a.status,a.type)"
								+ "from Notifications a ,AttendeeFeedback b where a.type = 2 and"
								+ " a.status=0 and a.training.id = b.topic.id and b.trainer.userId = :userId and a.userId = b.attendee.userId")
				.setLong("userId", userId).list();

		return list;
	}

	@Override
	public List<NotificationBean> getTrainerTestList(long userId)
			throws Exception {
		// System.out.println(userId);
		// TODO Auto-generated method stub
		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.id,a.training.id,b.attendee.userId,b.test.title,b.attendee.firstName,"
								+ "b.attendee.lastName,a.status,a.type)"
								+ "from Notifications a ,AttendeeTest b ,TopicTrainer c where a.type = 3 and"
								+ " a.status=0 and a.training.id = b.test.training.id and "
								+ " c.trainer.userId = :userId and a.userId = b.attendee.userId and"
								+ " c.topic.id =  a.training.id")
				.setLong("userId", userId).list();

		return list;
	}

	@Override
	public List<NotificationBean> getTrainerPendingFeedbackList(long userId)
			throws Exception {
		// TODO Auto-generated method stub

		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.id,a.training.id,b.attendee.userId,a.training.topic,b.attendee.firstName,"
								+ "b.attendee.lastName,a.status,a.type)"
								+ "from Notifications a ,AttendeeFeedback b where a.type = 4 and"
								+ " a.status=0 and a.training.id = b.topic.id and b.trainer.userId = :userId and a.userId = b.attendee.userId")
				.setLong("userId", userId).list();

		return list;
	}

	@Override
	public List<NotificationBean> getAdminTrainingList() throws Exception {
		// TODO Auto-generated method stub
		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.id,a.training.id,b.attendee.userId,b.test.title,b.attendee.firstName,"
								+ "b.attendee.lastName,a.adminstatus,a.type)"
								+ "from Notifications a,AttendeeTest b where a.type = 3 and"
								+ " a.adminstatus=0 and a.training.id = b.test.training.id  "
								+ "  and a.userId = b.attendee.userId").list();

		return list;
	}

	@Override
	public List<NotificationBean> getAdminFeedbackList() throws Exception {
		// TODO Auto-generated method stub
		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(a.id,a.training.id,b.attendee.userId,a.training.topic,b.attendee.firstName,"
								+ "b.attendee.lastName,a.adminstatus,a.type)"
								+ "from Notifications a ,AttendeeFeedback b where a.type = 4 and"
								+ " a.adminstatus=0 and a.training.id = b.topic.id and a.userId = b.attendee.userId")
				.list();

		return list;
	}

	@Override
	public void updateAdminStatus(long notificationid) throws Exception {
		// TODO Auto-generated method stub
		getSession()
				.createQuery(
						" update Notifications set adminstatus = 1 where id = :id")
				.setLong("id", notificationid).executeUpdate();
	}

	@Override
	public void updateuserStatus(long notificationid) throws Exception {
		// TODO Auto-generated method stub

		getSession()
				.createQuery(
						" update Notifications set status = 1 where id = :id")
				.setLong("id", notificationid).executeUpdate();

	}

}
