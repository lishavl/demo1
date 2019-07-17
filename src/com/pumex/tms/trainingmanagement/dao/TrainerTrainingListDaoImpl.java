package com.pumex.tms.trainingmanagement.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.AttendeeFeedback;
import com.pumex.tms.models.TopicAttendee;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.notifications.bean.NotificationBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean;
import com.pumex.tms.usermanagement.bean.FeedBackBean;
import com.pumex.tms.usermanagement.bean.Register;
import com.pumex.tms.util.AppConstants;

@Transactional
@Repository("TrainingListDao")
public class TrainerTrainingListDaoImpl extends AbstractDao<Long, TopicTrainer>
		implements TrainerTrainingListDaoInterface {

	@Override
	public List<TrainerTrainingListBean> getMyTrainingList(long trainerid)
			throws Exception {
		List<TrainerTrainingListBean> list = getSession()
				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.trainer.userId,a.topic.id,"
								+ "a.topic.startDateTime,a.topic.endDateTime,a.topic.topic,"
								+ "a.topic.status,a.topic.emailstatus,(select count(b.attendee.userId) "
								+ "from TopicAttendee b where a.topic.id = b.topic.id))"
								+ " from TopicTrainer a  where  a.trainer.userId = :userId and a.topic.status=1 and a.topic.endDateTime >:ctime order by a.topic.startDateTime desc")
				.setLong("userId", trainerid)
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : list) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String sdate = attendeetrainings.getStartdatetime().toString();
			String currentdate = sdf.format(new Date().getTime());
			
			Date date1 = sdf.parse(currentdate);
			Date date2 = sdf.parse(sdate);
			
			if ((date1.compareTo(date2) > 0) || (date1.compareTo(date2) == 0)) {
				attendeetrainings.setAttendancestatus(1);
			} else {
				attendeetrainings.setAttendancestatus(0);
			}
		}

		return list;

	}

	@Override
	public List<Register> fetchAllAttendees(long topicid) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"select new com.pumex.tms.usermanagement.bean.Register(a.userId,a.firstName,a.lastName) "
								+ "from UserDetails a join a.roles b where b.role.id = 3 and a.status = 1 "
								+ "and a.userId not in(select c.attendee.userId from TopicAttendee c where c.topic.id = :topicid )"
								+ "  group by a.userId order by a.firstName,a.lastName asc")
				.setLong("topicid", topicid).list();
		return list;
	}

	@Override
	public void addAttendee(long[] attendees, TrainingTopic topic)
			throws Exception {
		// TODO Auto-generated method stub

		long topic_id = topic.getId();

		getSession()
				.createQuery(
						"delete from  TopicAttendee a where a.topic.id = :topic_id")
				.setLong("topic_id", topic_id).executeUpdate();

		for (int i = 0; i < attendees.length; i++) {

			TopicAttendee topicattendee = new TopicAttendee();
			UserDetails userDetails = new UserDetails();
			userDetails.setUserId(attendees[i]);
			topicattendee.setAttendee(userDetails);
			topicattendee.setTopic(topic);
			getSession().save(topicattendee);
		}

	}

	@Override
	public List<TrainerTrainingListBean> getTrainingDetails(long topicid,
			long trainerId) throws Exception {
		// TODO Auto-generated method stub

		List<TrainerTrainingListBean> list = getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.attendee.userId,a.topic.id,a.topic.topic,a.topic.startDateTime,a.attendee.firstName,a.attendee.lastName)"
								+ " from TopicAttendee a  where  a.topic.id = :id")
				.setLong("id", topicid).list();

		for (TrainerTrainingListBean l : list) {
			long count = (Long) getSession()
					.createQuery(
							"select  count(a.id) from AttendeeFeedback a where a.attendee.userId =:attendeeid and a.topic.id = :topicid and a.trainer.userId = :trainerId")
					.setLong("topicid", topicid)
					.setLong("trainerId", trainerId)
					.setLong("attendeeid", l.getAttendeeid()).uniqueResult();
			l.setCount(count);
		}
		return list;

	}

	@Override
	public FeedBackBean getFeedBackList(long attendeeid, long trainerId,
			long topicid) throws Exception {
		// TODO Auto-generated method stub
		return (FeedBackBean) getSession()
				.createQuery(
						"select  new com.pumex.tms.usermanagement.bean.FeedBackBean("
								+ " a.attendee.userId,a.attendee.firstName,a.attendee.lastName,a.feedback,a.whyAppropriate,a.comment,"
								+ "a.followupSessionNeeded,a.demoNeeded)"
								+ "from AttendeeFeedback a where a.attendee.userId =:attendeeid and a.topic.id = :topicid and a.trainer.userId = :trainerId")
				.setLong("topicid", topicid).setLong("trainerId", trainerId)
				.setLong("attendeeid", attendeeid).uniqueResult();
	}

	@Override
	public List getAttendees(long topicid) throws Exception {
		// TODO Auto-generated method stub
		return (List) getSession()
				.createQuery(
						"select  new com.pumex.tms.usermanagement.bean.FeedBackBean("
								+ "a.attendee.userId,a.attendee.firstName,a.attendee.lastName)"
								+ "from TopicAttendee a where a.topic.id = :topicid")
				.setLong("topicid", topicid).list();
	}

	@Override
	public List<TrainerTrainingListBean> getCompletedTrainingList(long trainerid)
			throws Exception {
		// TODO Auto-generated method stub
		List<TrainerTrainingListBean> list = getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.trainer.userId,a.topic.id,"
								+ "a.topic.startDateTime,a.topic.endDateTime,a.topic.topic,"
								+ "a.topic.status,(select count(b.attendee.userId) "
								+ "from TopicAttendee b where a.topic.id = b.topic.id))"
								+ " from TopicTrainer a  where  a.trainer.userId = :userId and a.topic.status=2  or a.topic.status=1 and a.topic.endDateTime <:ctime order by a.topic.startDateTime desc")
				.setLong("userId", trainerid)
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : list) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);
		}

		return list;

	}

	@Override
	public List<TrainerTrainingListBean> getAllTrainingList() throws Exception {
		// TODO Auto-generated method stub
		List<TrainerTrainingListBean> list = getSession()
				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.id,"
								+ "a.startDateTime,a.endDateTime,a.topic,"
								+ "a.status,a.emailstatus,(select count(b.attendee.userId)"
								+ "from TopicAttendee b where a.id = b.topic.id))"
								+ " from TrainingTopic a  where  a.status=1 and a.endDateTime >:ctime order by a.startDateTime desc")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : list) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);

			Timestamp time = new Timestamp(new Date().getTime());
			Date currentdate = new Date(time.getTime());

			if (currentdate.compareTo(attendeetrainings.getStartdatetime()) > 0) {
				attendeetrainings.setAttendancestatus(1);
			} else {
				attendeetrainings.setAttendancestatus(0);
			}
		}

		return list;
	}

	@Override
	public List<TrainerTrainingListBean> getAllCompletedTrainingList()
			throws Exception {
		// TODO Auto-generated method stub
		List<TrainerTrainingListBean> list = getSession()
				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.id,"
								+ "a.startDateTime,a.endDateTime,a.topic,"
								+ "a.status,(select count(b.attendee.userId) "
								+ "from TopicAttendee b where a.id = b.topic.id))"
								+ " from TrainingTopic a  where  a.status=2  or a.status=1 and a.endDateTime <:ctime order by a.startDateTime desc")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : list) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);

			attendeetrainings.setAttendancestatus(1);
		}

		return list;
	}

	@Override
	public List<UserDetails> getUserRoles(long userId) throws Exception {
		// TODO Auto-generated method stub
		List list = getSession()
				.createQuery(
						"from UserDetails where  status = 1 and userId = :userId")
				.setLong("userId", userId).list();
		return list;
	}

	@Override
	public List<TrainerTrainingListBean> getAttendeeTrainingList(long trainerid)
			throws Exception {
		// TODO Auto-generated method stub
		List<TrainerTrainingListBean> lst = getSession()

				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.topic.id,a.topic.startDateTime, a.topic.endDateTime, a.topic.topic)"
								+ " from TopicTrainer a where a.topic.status=1 and a.topic.endDateTime >:ctime  order by a.topic.startDateTime")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : lst) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);

			long count = 0;
			count = (Long) getSession()
					.createQuery(
							"select  count(a.id) from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid  and a.attendedStatus in (0,1,-1) ")
					.setLong("userId", trainerid)
					.setLong("topicid", attendeetrainings.getTopicid())
					.uniqueResult();

			attendeetrainings.setCount(count);
			if (count != 0) {
				int status = (int) getSession()
						.createQuery(
								"select a.attendedStatus from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid")
						.setLong("userId", trainerid)
						.setLong("topicid", attendeetrainings.getTopicid())
						.uniqueResult();
				attendeetrainings.setStatus(status);
			}

		}
		return lst;
	}

	@Override
	public List<TrainerTrainingListBean> getAttendeesCompletedTrainingList(
			long trainerid) throws Exception {
		// TODO Auto-generated method stub
		List<TrainerTrainingListBean> lst = getSession()

				.createQuery(
						"select distinct  new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.topic.id,a.topic.startDateTime, a.topic.endDateTime, a.topic.topic)"
								+ " from TopicTrainer a where a.topic.status=2  or a.topic.status=1 and a.topic.endDateTime <:ctime order by a.topic.startDateTime desc")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : lst) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);

			long count = 0;
			count = (Long) getSession()
					.createQuery(
							"select  count(a.id) from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid  and a.attendedStatus in (0,1,-1) ")
					.setLong("userId", trainerid)
					.setLong("topicid", attendeetrainings.getTopicid())
					.uniqueResult();

			attendeetrainings.setCount(count);
			if (count != 0) {
				int status = (int) getSession()
						.createQuery(
								"select a.attendedStatus from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid")
						.setLong("userId", trainerid)
						.setLong("topicid", attendeetrainings.getTopicid())
						.uniqueResult();
				attendeetrainings.setStatus(status);
			}

		}
		return lst;
	}

	// Added by Jinshad
	@Override
	public List<TrainerTrainingListBean> getAllTrainingList(long userId,
			long roleId) throws Exception {

		if (roleId == AppConstants.USER_ROLES.ADMIN_ROLE) {
			return getSession()
					.createQuery(
							"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
									+ "a.id,"
									+ "concat(a.startDateTime,''),concat(a.endDateTime,''),a.topic,"
									+ "a.status)"
									+ " from TrainingTopic a where a.status!=0")
					.list();

		} else if (roleId == AppConstants.USER_ROLES.TRAINER_ROLE) {
			return getSession()
					.createQuery(
							"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
									+ "a.topic.id,"
									+ "concat(a.topic.startDateTime,''),concat(a.topic.endDateTime,''),a.topic.topic,"
									+ "a.topic.status)"
									+ " from TopicTrainer a  where  a.trainer.userId = :userId and  a.topic.status!=0")
					.setLong("userId", userId).list();
		} else {
			return getSession()
					.createQuery(
							"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
									+ "a.topic.id,"
									+ "concat(a.topic.startDateTime,''),concat(a.topic.endDateTime,''),a.topic.topic,"
									+ "a.topic.status)"
									+ " from TopicAttendee a  where  a.attendee.userId = :userId and  a.topic.status!=0")
					.setLong("userId", userId).list();
		}

	}

	@Override
	public List getAllTrainers(long topicid, long id) throws Exception {
		// TODO Auto-generated method stub
		List<FeedBackBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.usermanagement.bean.FeedBackBean("
								+ "a.trainer.userId,a.trainer.firstName,a.trainer.lastName)"
								+ "from TopicTrainer a where a.topic.id = :topicid")
				.setLong("topicid", topicid).list();

		for (FeedBackBean l : list) {
			long count = (Long) getSession()
					.createQuery(
							"select  count(a.id) from AttendeeFeedback a where a.attendee.userId =:userId and a.topic.id = :topicid and a.trainer.userId = :trainerid")
					.setLong("userId", id).setLong("topicid", topicid)
					.setLong("trainerid", l.getUser_id()).uniqueResult();
			l.setCount(count);
		}

		return list;
	}

	@Override
	public List<NotificationBean> getTrainingDetails(long topicid)
			throws Exception {
		// TODO Auto-generated method stub
		List<NotificationBean> list = getSession()
				.createQuery(
						"select  new com.pumex.tms.notifications.bean.NotificationBean"
								+ "(b.id,c.userId,b.topic,b.startDateTime,c.firstName,c.lastName,c.email)"
								+ " from TopicAttendee a join a.topic b join a.attendee c where b.status=1 "
								+ "and b.id = :topicid")
				.setLong("topicid", topicid).list();

		return list;
	}

	@Override
	public void setEmailStatus(long topicid) throws Exception {
		// TODO Auto-generated method stub

		getSession()
				.createQuery(
						" update TrainingTopic  set emailstatus = 1 where id = :topicid")
				.setLong("topicid", topicid).executeUpdate();

	}

	@Override
	public List<TrainerTrainingListBean> getAllShedulesByDate(String strtdate,
			String enddte) throws Exception {
		// TODO Auto-generated method stub

		String criteria = "";

		if (strtdate.length() > 0 && enddte.length() > 0) {
			criteria += " a.startDateTime>='" + strtdate
					+ " 00:00:00' and a.startDateTime<='" + enddte
					+ " 23:59:59'";
		} else if (strtdate.length() > 0) {
			criteria += "a.startDateTime>='" + strtdate + " 00:00:00'";
		} else if (enddte.length() > 0) {
			criteria += "a.startDateTime<='" + enddte + " 23:59:59'";
		}

		List<TrainerTrainingListBean> list = getSession()
				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.id,"
								+ "a.startDateTime,a.endDateTime,a.topic,"
								+ "a.status,(select count(b.attendee.userId) "
								+ "from TopicAttendee b where a.id = b.topic.id))"
								+ " from TrainingTopic a  where "
								+ criteria
								+ " and  a.status in (1,2) and a.endDateTime <:ctime  order by a.startDateTime desc")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : list) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);

			attendeetrainings.setAttendancestatus(1);
		}

		return list;

	}

	@Override
	public List<TrainerTrainingListBean> getCompletedTrainingListForTrainerByDate(
			long trainerid, String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub

		String criteria = "";

		if (strtdate.length() > 0 && enddte.length() > 0) {
			criteria += " a.topic.startDateTime>='" + strtdate
					+ " 00:00:00' and a.topic.startDateTime<='" + enddte
					+ " 23:59:59'";
		} else if (strtdate.length() > 0) {
			criteria += "a.topic.startDateTime>='" + strtdate + " 00:00:00'";
		} else if (enddte.length() > 0) {
			criteria += "a.topic.startDateTime<='" + enddte + " 23:59:59'";
		}

		List<TrainerTrainingListBean> list = getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.trainer.userId,a.topic.id,"
								+ "a.topic.startDateTime,a.topic.endDateTime,a.topic.topic,"
								+ "a.topic.status,(select count(b.attendee.userId) "
								+ "from TopicAttendee b where a.topic.id = b.topic.id))"
								+ " from TopicTrainer a  where   a.trainer.userId = :userId and  "
								+ criteria
								+ " and a.topic.status in (1,2) and a.endDateTime <:ctime  order by a.topic.startDateTime desc")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.setLong("userId", trainerid).list();

		for (TrainerTrainingListBean attendeetrainings : list) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);
		}

		return list;
	}

	@Override
	public List<TrainerTrainingListBean> getAttendeeTrainingListByDate(
			long trainerid, String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub

		String criteria = "";

		if (strtdate.length() > 0 && enddte.length() > 0) {
			criteria += " a.topic.startDateTime>='" + strtdate
					+ " 00:00:00' and a.topic.startDateTime<='" + enddte
					+ " 23:59:59'";
		} else if (strtdate.length() > 0) {
			criteria += "a.topic.startDateTime>='" + strtdate + " 00:00:00'";
		} else if (enddte.length() > 0) {
			criteria += "a.topic.startDateTime<='" + enddte + " 23:59:59'";
		}

		List<TrainerTrainingListBean> lst = getSession()

				.createQuery(
						"select distinct  new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.topic.id,a.topic.startDateTime, a.topic.endDateTime, a.topic.topic)"
								+ " from TopicTrainer a where "
								+ criteria
								+ " and  a.topic.status in (1,2) and a.endDateTime <:ctime   order by a.topic.startDateTime desc")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : lst) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);

			long count = 0;
			count = (Long) getSession()
					.createQuery(
							"select  count(a.id) from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid  and a.attendedStatus in (0,1,-1) ")
					.setLong("userId", trainerid)
					.setLong("topicid", attendeetrainings.getTopicid())
					.uniqueResult();

			attendeetrainings.setCount(count);
			if (count != 0) {
				int status = (int) getSession()
						.createQuery(
								"select a.attendedStatus from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid")
						.setLong("userId", trainerid)
						.setLong("topicid", attendeetrainings.getTopicid())
						.uniqueResult();
				attendeetrainings.setStatus(status);
			}

		}
		return lst;
	}

	@Override
	public List<TrainerTrainingListBean> getAllUpcomingTrainingsBydate(
			String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub

		String criteria = "";

		if (strtdate.length() > 0 && enddte.length() > 0) {
			criteria += " a.startDateTime>='" + strtdate
					+ " 00:00:00' and a.startDateTime<='" + enddte
					+ " 23:59:59'";
		} else if (strtdate.length() > 0) {
			criteria += "a.startDateTime>='" + strtdate + " 00:00:00'";
		} else if (enddte.length() > 0) {
			criteria += "a.startDateTime<='" + enddte + " 23:59:59'";
		}

		List<TrainerTrainingListBean> list = getSession()
				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.id,"
								+ "a.startDateTime,a.endDateTime,a.topic,"
								+ "a.status,a.emailstatus,(select count(b.attendee.userId)"
								+ "from TopicAttendee b where a.id = b.topic.id))"
								+ " from TrainingTopic a  where "
								+ criteria
								+ " and  a.status=1 and a.endDateTime >:ctime order by a.startDateTime desc")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : list) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);

			Timestamp time = new Timestamp(new Date().getTime());
			Date currentdate = new Date(time.getTime());

			if (currentdate.compareTo(attendeetrainings.getStartdatetime()) > 0) {
				attendeetrainings.setAttendancestatus(1);
			} else {
				attendeetrainings.setAttendancestatus(0);
			}
		}

		return list;
	}

	@Override
	public List<TrainerTrainingListBean> getUpcomingTrngForTrainerByDate(
			long trainerid, String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub

		String criteria = "";

		if (strtdate.length() > 0 && enddte.length() > 0) {
			criteria += " a.topic.startDateTime>='" + strtdate
					+ " 00:00:00' and a.topic.startDateTime<='" + enddte
					+ " 23:59:59'";
		} else if (strtdate.length() > 0) {
			criteria += "a.topic.startDateTime>='" + strtdate + " 00:00:00'";
		} else if (enddte.length() > 0) {
			criteria += "a.topic.startDateTime<='" + enddte + " 23:59:59'";
		}
		List<TrainerTrainingListBean> list = getSession()
				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.trainer.userId,a.topic.id,"
								+ "a.topic.startDateTime,a.topic.endDateTime,a.topic.topic,"
								+ "a.topic.status,a.topic.emailstatus,(select count(b.attendee.userId) "
								+ "from TopicAttendee b where a.topic.id = b.topic.id))"
								+ " from TopicTrainer a  where  a.trainer.userId = :userId and "
								+ criteria
								+ " and a.topic.status=1 and a.topic.endDateTime >:ctime  order by a.topic.startDateTime desc")
				.setLong("userId", trainerid)
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : list) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);

			Timestamp time = new Timestamp(new Date().getTime());
			Date currentdate = new Date(time.getTime());

			if (currentdate.compareTo(attendeetrainings.getStartdatetime()) > 0) {
				attendeetrainings.setAttendancestatus(1);
			} else {
				attendeetrainings.setAttendancestatus(0);
			}
		}

		return list;
	}

	@Override
	public List<TrainerTrainingListBean> getUpcmingAttendeeTrainingListByDate(
			long trainerid, String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub

		String criteria = "";

		if (strtdate.length() > 0 && enddte.length() > 0) {
			criteria += " a.topic.startDateTime>='" + strtdate
					+ " 00:00:00' and a.topic.startDateTime<='" + enddte
					+ " 23:59:59'";
		} else if (strtdate.length() > 0) {
			criteria += "a.topic.startDateTime>='" + strtdate + " 00:00:00'";
		} else if (enddte.length() > 0) {
			criteria += "a.topic.startDateTime<='" + enddte + " 23:59:59'";
		}

		List<TrainerTrainingListBean> lst = getSession()

				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean("
								+ "a.topic.id,a.topic.startDateTime, a.topic.endDateTime, a.topic.topic)"
								+ " from TopicTrainer a where "
								+ criteria
								+ "  and a.topic.status=1 and a.topic.endDateTime >:ctime   order by a.topic.startDateTime")
				.setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (TrainerTrainingListBean attendeetrainings : lst) {
			List<String> trianers = getSession()
					.createQuery(
							"select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid")
					.setLong("topicid", attendeetrainings.getTopicid()).list();
			attendeetrainings.setTrainernames(trianers);

			long count = 0;
			count = (Long) getSession()
					.createQuery(
							"select  count(a.id) from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid  and a.attendedStatus in (0,1,-1) ")
					.setLong("userId", trainerid)
					.setLong("topicid", attendeetrainings.getTopicid())
					.uniqueResult();

			attendeetrainings.setCount(count);
			if (count != 0) {
				int status = (int) getSession()
						.createQuery(
								"select a.attendedStatus from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid")
						.setLong("userId", trainerid)
						.setLong("topicid", attendeetrainings.getTopicid())
						.uniqueResult();
				attendeetrainings.setStatus(status);
			}

		}
		return lst;
	}
}
