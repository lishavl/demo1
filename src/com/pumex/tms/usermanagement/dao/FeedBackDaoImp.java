/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.usermanagement.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.AttendeeFeedback;
import com.pumex.tms.models.Notifications;
import com.pumex.tms.models.TopicAttendee;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.onlinetest.bean.TestReportBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.usermanagement.bean.FeedBackBean;

@Transactional
@Repository
public class FeedBackDaoImp extends AbstractDao<Long, AttendeeFeedback>
		implements FeedBackDao {
	  
	/*
	 * Method for fetch all feedback details
	 * 
	 * @return  list as Feedback bean
	 */
	@Override
	public List<FeedBackBean> getAllFeedBackList(long userId) throws Exception {

		List<AttendeeFeedback> attndfdback = getSession().createQuery(
				"from AttendeeFeedback").list();

		List<FeedBackBean> lst = getSession()

				.createQuery(
						"select distinct new com.pumex.tms.usermanagement.bean.FeedBackBean("
								+ "a.id,a.topic.id,a.trainer.userId,a.topic.startDateTime,  a.topic.topic,concat(a.trainer.firstName,' ',a.trainer.lastName) as trainername,b.attendedStatus)"
								+ " from TopicTrainer a ,TopicAttendee b   where "
								+ " a.topic.id = b.topic.id and b.attendee.userId = :userId "
								+ " and a.topic.status=2 group by a.id")
				.setLong("userId", userId).list();

		for (FeedBackBean l : lst) {
			long count = (Long) getSession()
					.createQuery(
							"select  count(a.id) from AttendeeFeedback a where a.attendee.userId =:userId and a.topic.id = :topicid and a.trainer.userId = :trainerid")
					.setLong("userId", userId)
					.setLong("topicid", l.getTopicid())
					.setLong("trainerid", l.getTrainerid()).uniqueResult();
			l.setCount(count);
		}

		return lst;
	}
    
	/*
	 * Method for update feedback
	 * 
	 * @return  status
	 */
	@Override
	public void updateAttendeeFeedBack(AttendeeFeedback feedback, long trainerId) {
		persist(feedback);
		
		Notifications notifications = new Notifications();
		notifications.setAdminstatus(0);
		notifications.setStatus(0);
		notifications.setTraining(feedback.getTopic());
		notifications.setType(2);
		notifications.setUserId(trainerId);
		getSession().save(notifications);
		
		
		
		getSession().createQuery("Delete from Notifications where userId = :userid and type = 4").setLong("userid", trainerId).executeUpdate();
	}
    
	/*
	 * Method for get feedback details
	 * 
	 * @return  feedback details as FeedBackBean
	 */
	@Override
	public FeedBackBean viewFeedback(long userId, long topicid, long trainerid)
			throws Exception {
		FeedBackBean lst = (FeedBackBean) getSession()
				.createQuery(
						"select distinct new com.pumex.tms.usermanagement.bean.FeedBackBean("
								+ " a.topic.id, a.topic.topic,a.trainer.userId,a.trainer.firstName,a.trainer.lastName,a.feedback,a.whyAppropriate,a.comment,"
								+ "a.followupSessionNeeded,a.demoNeeded	,a.status,a.attendee.userId,a.attendee.firstName,a.attendee.lastName)"
								+ " from AttendeeFeedback a  where a.topic.id = :topicid and a.attendee.userId = :userId and a.trainer.userId = :trainerid")
				.setLong("topicid", topicid).setLong("userId", userId)
				.setLong("trainerid", trainerid).uniqueResult();
		return lst;

	}
	/*
	 * Method for fetch trainer name
	 * 
	 * @return  as Feedback bean
	 */
	public FeedBackBean getTrainerName(long id) throws Exception {
		FeedBackBean test = (FeedBackBean) getSession()
				.createQuery(
						"select  new com.pumex.tms.usermanagement.bean.FeedBackBean("
								+ " a.topic.id, a.topic.topic,a.trainer.userId,concat(a.trainer.firstName, ' ',a.trainer.lastName))"
								+ " from TopicTrainer  a where a.id =:id ")
				.setLong("id", id).uniqueResult();
		return test;
		
	}

	@Override
	public long feedBackExist(long id, long topicid, long trainerid)
			throws Exception {
		long count = (Long) getSession()
				.createQuery(
						"select distinct count(id) from AttendeeFeedback where attendee.userId =:userId and topic.id = :topicid and trainer.userId = :trainerid")
				.setLong("userId", id).setLong("topicid", topicid)
				.setLong("trainerid", trainerid).uniqueResult();

		return count;
	}
    
	/*
	 * Method for fetch All UpComing Training Details
	 * 
	 * @return  list as FeedBackBean
	 */
	@Override
	public List<FeedBackBean> getAllUpcomingTrainingList(long id) {
		List<FeedBackBean> lst = getSession()

				.createQuery(
						"select distinct new com.pumex.tms.usermanagement.bean.FeedBackBean("
								+ "a.topic.id,a.topic.startDateTime, a.topic.endDateTime, a.topic.topic)"
								+ " from TopicTrainer a where a.topic.status=1 and a.topic.endDateTime >:ctime  and a.topic.startDateTime >:ctime order by a.topic.startDateTime").setParameter("ctime", new Timestamp(new Date().getTime()))
				.list();

		for (FeedBackBean attendeefeedbacks : lst) {
			List<String> trianers=getSession().createQuery("select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid").setLong("topicid", attendeefeedbacks.getTopicid()).list();
			attendeefeedbacks.setTrainernames(trianers);
			
			
			long count=0;
		    count = (Long) getSession()
					.createQuery(
							"select  count(a.id) from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid  and a.attendedStatus in (0,1,-1) ")
					.setLong("userId", id)
					.setLong("topicid", attendeefeedbacks.getTopicid())
					.uniqueResult();
			 
			attendeefeedbacks.setCount(count);
			if(count!=0){
			int status=(int) getSession()
					.createQuery(
							"select a.attendedStatus from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid")
					.setLong("userId", id)
					.setLong("topicid", attendeefeedbacks.getTopicid())
					.uniqueResult();
			attendeefeedbacks.setStatus(status);
			}
			
		}
		return lst;

	}
    
	/*
	 * Method for fetch interested attendee details for trainer 
	 * 
	 * @return  response  as UserDetails
	 */
	@Override
	public UserDetails getAllAttendeeDetails(long id) {
		// TODO Auto-generated method stub
		return (UserDetails) getSession()
				.createQuery("from UserDetails where userId = :userId")
				.setParameter("userId", id).uniqueResult();
	}
	
	/*
	 * Method for fetch topic details for trainer 
	 * 
	 * @return  response  as TrainingTopic
	 */
	@Override
	public TrainingTopic getAllTopicNames(long topicid) {
		// TODO Auto-generated method stub
		return (TrainingTopic) getSession()
				.createQuery("from TrainingTopic where id = :topicid")
				.setLong("topicid", topicid).uniqueResult();
	}
    
	/*
	 * Method for Attendee entry to Topic Attendee
	 * 
	 * @return  status as response
	 */
	@Override
	public void saveInterestedAttendee(TopicAttendee topicAttendee)
			throws Exception {
		// TODO Auto-generated method stub

		getSession().save(topicAttendee);

	}

	@Override
	public List<TrainingTopic> getAllTopicNamesForAdmin() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from TrainingTopic where status = 2 ")
				.list();
	}
    
	/*
	 * Method for fetch all trainer names for admin feedback
	 * 
	 * @return  trainer names as TopicTrainer list
	 */
	@Override
	public List<TopicTrainer> getAllTrainerNamesForAdmin(long id)
			throws Exception {
		// TODO Auto-generated method stub
		return getSession().createQuery("from TopicTrainer where topic.id=:id")
				.setLong("id", id).list();
	}
    
	/*
	 * Method for fetch all attendee details  for admin feedback
	 * 
	 * @return  attendee  names as TopicAttendeeBean list
	 */
	@Override
	public List<TopicAttendeeBean> getAllAttendeeListAdmin(long topicid,
			long trainerid) throws Exception {

		List<TopicAttendeeBean> lst = getSession()

				.createQuery(
						"select distinct new com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean("
								+ "a.id,a.attendee.firstName,a.attendee.lastName,a.attendee.userId,a.topic.topic,a.topic.id,a.attendedStatus)"
								+ " from TopicAttendee a  where "
								+ " a.topic.id = :topicid")
				.setLong("topicid", topicid).list();

		for (TopicAttendeeBean l : lst) {
			long count = (long) getSession()
					.createQuery(
							"select  count(a.id) from AttendeeFeedback a where a.attendee.userId =:userId and a.topic.id = :topicid and a.trainer.userId = :trainerid")
					.setLong("userId", l.getUserId())
					.setLong("topicid", topicid)
					.setLong("trainerid", trainerid).uniqueResult();
			l.setCount(count);
			List<String> trainernames = getSession().createQuery("select concat(trainer.firstName,' ',trainer.lastName) from TopicTrainer where topic.id = :topicid ").setLong("topicid", l.getTopicid()).list();
            l.setTrainernames(trainernames);
		}

		return lst;
	}
    
	/*
	 * Method for set status after mail as -1
	 * 
	 * @return  status as response
	 */
	@Override
	public void setStatusAfterMail(long id,long topicid) {
		
		getSession().createQuery("update TopicAttendee set attendedStatus =-1 where attendee.userId=:userId and topic.id=:topicid").setLong("userId",id)
		.setLong("topicid",topicid).executeUpdate();
		// TODO Auto-generated method stub
		
	}
    
	/*
	 * Method for fetch Attendee Test Result Details
	 * 
	 * @return  list as TestReportBean
	 */
	@Override
	public List<TestResultBean> getAllTestResult(long id) throws Exception {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"select new com.pumex.tms.onlinetest.bean.TestResultBean("
								+ "a.test.id,a.test.title, a.mark,a.test.numberOfQuestions, a.test.passMark,a.totalAnswered,a.test.correctAnswerMark)"
								+ " from AttendeeTest a where a.attendee.userId = :id")
				.setLong("id", id).list();
	}

	
	/*
	 * Method for check whether the attendee is already enrolled by other trainer
	 * 
	 * @return  count 
	 */
	@Override
	public long isAttendeeExist(long topicid, long userid) throws Exception {
		// TODO Auto-generated method stub
		return (long) getSession().createQuery("select count(id) from TopicAttendee where attendee.userId=:userid and topic.id=:topicid and attendedStatus=0")
				.setLong("userid",userid).setLong("topicid",topicid).uniqueResult();
	}

	@Override
	public void saveInterestedAttendeeAfterMail(TopicAttendee topicAttendee)
			throws Exception {
		
		getSession().save(topicAttendee);
		
		
		// TODO Auto-generated method stub
		
	}
    
	/*
	 * Method for trainer names for mail sending
	 * 
	 * @return  List as  TrainerTopicBean
	 */
	@Override
	public List<TrainerTopicBean> getAllTrainerDetailsForMail(long topicid)
			throws Exception {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(
						"select new com.pumex.tms.trainingmanagement.bean.TrainerTopicBean("
								+ " a.topic.topic,concat(a.trainer.firstName,' ',a.trainer.lastName) as name,a.trainer.email,a.topic.id,a.topic.startDateTime)"
								+ " from TopicTrainer a where a.topic.id = :id")
				.setLong("id", topicid).list();
	}
    
	/*
	 * Method for entry of  attendee details to the Topic Attendee table and set  attendee status as 0
	 * 
	 * @return  status 
	 */
	@Override
	public void saveInterestedAttendee(long topicid, long userid)
			throws Exception {
		getSession().createQuery("update TopicAttendee set attendedStatus =0 where attendee.userId=:userId and topic.id=:topicid").setLong("userId",userid)
		.setLong("topicid",topicid).executeUpdate();
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Method for fetch All Completed Training Details
	 * 
	 * @return  list as FeedBackBean
	 */
	@Override
	public List<FeedBackBean> getAllCompletedTrainingList(long id)
			throws Exception {
		List<FeedBackBean> lst = getSession()

				.createQuery(
						"select distinct new com.pumex.tms.usermanagement.bean.FeedBackBean("
								+ "a.topic.id,a.topic.startDateTime, a.topic.endDateTime, a.topic.topic)"
								+ " from TopicTrainer a where a.topic.status=2 and a.topic.endDateTime <:ctime  order by a.topic.startDateTime desc")
				.setParameter("ctime", new Timestamp(new Date().getTime())).list();
//
		for (FeedBackBean attendeefeedbacks : lst) {
			List<String> trianers=getSession().createQuery("select concat(a.trainer.firstName,' ',a.trainer.lastName) from TopicTrainer a where a.topic.id=:topicid").setLong("topicid", attendeefeedbacks.getTopicid()).list();
			attendeefeedbacks.setTrainernames(trianers);
			
			
			long count=0;
		    count = (Long) getSession()
					.createQuery(
							"select  count(a.id) from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid  and a.attendedStatus in (0,1,-1) ")
					.setLong("userId", id)
					.setLong("topicid", attendeefeedbacks.getTopicid())
					.uniqueResult();
			 
			attendeefeedbacks.setCount(count);
			if(count!=0){
			int status=(int) getSession()
					.createQuery(
							"select a.attendedStatus from TopicAttendee a where  a.attendee.userId =:userId and a.topic.id = :topicid")
					.setLong("userId", id)
					.setLong("topicid", attendeefeedbacks.getTopicid())
					.uniqueResult();
			attendeefeedbacks.setStatus(status);
			}
			
		}
		return lst;
	}

}
