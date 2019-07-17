/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.usermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.models.AttendeeFeedback;
import com.pumex.tms.models.TopicAttendee;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.onlinetest.bean.TestReportBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.usermanagement.bean.FeedBackBean;
import com.pumex.tms.usermanagement.dao.FeedBackDao;

@Service("feedbackservice")
@Transactional
public class FeedBackServiceImp implements FeedbackService {

	@Autowired
	FeedBackDao dao;
   
	  
	/*
		 * Method for fetch all feedback details
	* 
	* @return  list as Feedback bean
    */
	@Override
	public List<FeedBackBean> getAllFeedBackList(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllFeedBackList(id);
	}
	
	/*
	 * Method for fetch trainer name
	 * 
	 * @return  as Feedback bean
	 */
	@Override
	public FeedBackBean getTrainerName(long id) throws Exception {
		// TODO Auto-generated method stub
		return dao.getTrainerName(id);
	}
    
	/*
	 * Method for update feedback
	 * 
	 * @return  status
	 */
	@Override
	public void updateFeedback(FeedBackBean feedbackbean, long id)
			throws Exception {

		AttendeeFeedback feedback = new AttendeeFeedback();
		UserDetails details = new UserDetails();
		TrainingTopic topic = new TrainingTopic();
		UserDetails trainername = new UserDetails();
		details.setUserId(id);
		trainername.setUserId(feedbackbean.getTrainerid());
		topic.setId(feedbackbean.getTopicid());

		feedback.setTrainer(trainername);
		feedback.setTopic(topic);
		feedback.setAttendee(details);
		feedback.setFeedback(feedbackbean.getReport());
		feedback.setWhyAppropriate(feedbackbean.getData());
		feedback.setComment(feedbackbean.getComment());
		feedback.setFollowupSessionNeeded(feedbackbean.getFollowup());
		feedback.setDemoNeeded(feedbackbean.getDemo());
		feedback.setStatus(1);
		
		dao.updateAttendeeFeedBack(feedback,id);

		// TODO Auto-generated method stub

	}
	
	/*
	 * Method for get feedback details
	 * 
	 * @return  feedback details as FeedBackBean
	 */
	@Override
	public FeedBackBean viewFeedback(long id, long topicid, long trainerid)
			throws Exception {

		return dao.viewFeedback(id, topicid, trainerid);
	}

	@Override
	public long checkFeedbackExist(long id, long topicid, long trainerid)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.feedBackExist(id, topicid, trainerid);
	}
    
	/*
	 * Method for fetch All UpComing Training Details
	 * 
	 * @return  list as FeedBackBean
	 */
	@Override
	public List<FeedBackBean> getAllUpcomingTrainings(long id) {
		// TODO Auto-generated method stub
		return dao.getAllUpcomingTrainingList(id);
	}

	/*
	 * Method for fetch interested attendee details for trainer 
	 * 
	 * @return  response  as UserDetails
	 */
	@Override
	public UserDetails getAttendeeDetails(long id) {

		// TODO Auto-generated method stub
		return dao.getAllAttendeeDetails(id);
	}

	/*
	 * Method for fetch topic details for trainer 
	 * 
	 * @return  response  as TrainingTopic
	 */
	@Override
	public TrainingTopic getAllTopicDetails(long topicid) {
		// TODO Auto-generated method stub
		return dao.getAllTopicNames(topicid);
	}
    
	/*
	 * Method for set attendeestatus as 0
	 * 
	 * @return  status 
	 */
	@Override
	public void addAttendeeDetails(long topicid, long userid) throws Exception {
		// TODO Auto-generated method stub
		dao.saveInterestedAttendee(topicid,userid);

	}
	
	@Override
	public void addAttendeeDetailsAfterMail(long topicid, long userid) throws Exception {
		// TODO Auto-generated method stub

		TopicAttendee topicAttendee = new TopicAttendee();
		TrainingTopic trainingTopic = new TrainingTopic();
		UserDetails userDetails = new UserDetails();

		userDetails.setUserId(userid);
		trainingTopic.setId(topicid);

		topicAttendee.setAttendee(userDetails);
		topicAttendee.setTopic(trainingTopic);
		dao.saveInterestedAttendeeAfterMail(topicAttendee);
		
		

	}
    
	/*
	 * Method for fetch all topic names for admin feedback
	 * 
	 * @return  topic names as TrainingTopic list
	 */
	@Override
	public List<TrainingTopic> getAllTopicNames() throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllTopicNamesForAdmin();
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
		return dao.getAllTrainerNamesForAdmin(id);
	}
   
	/*
	 * Method for fetch all attendee details  for admin feedback
	 * 
	 * @return  attendee  names as TopicAttendeeBean list
	 */
	@Override
	public List<TopicAttendeeBean> getAllAttendeeForAdminFeedback(long topicid,
			long trainerid) throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllAttendeeListAdmin(topicid, trainerid);
	}
    
	/*
	 * Method for set status after mail
	 * 
	 * @return  status as response
	 */
	@Override
	public void setStatusAftrMail(long id,long topicid) throws Exception {
		
		
		TopicAttendee topicAttendee = new TopicAttendee();
		TrainingTopic trainingTopic = new TrainingTopic();
		UserDetails userDetails = new UserDetails();

		userDetails.setUserId(id);
		trainingTopic.setId(topicid);

		topicAttendee.setAttendee(userDetails);
		topicAttendee.setTopic(trainingTopic);
		dao.saveInterestedAttendee(topicAttendee);
		dao.setStatusAfterMail(id,topicid);
		
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
		return dao.getAllTestResult(id);
	}
  
	/*
	 * Method for check whether the attendee is already enrolled by other trainer
	 * 
	 * @return  count 
	 */
	@Override
	public long isAttendeeExist(long topicid, long userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.isAttendeeExist(topicid,userid);
	}
   
	/*
	 * Method for trainer names for mail sending
	 * 
	 * @return  List as  TrainerTopicBean
	 */
	@Override
	public List<TrainerTopicBean> getAllTrainerNamesForMail(long topicid)
			throws Exception {
		
		// TODO Auto-generated method stub
		return dao.getAllTrainerDetailsForMail(topicid);
	}
    
	/*
	 * Method for fetch All Completed Training Details
	 * 
	 * @return  list as FeedBackBean
	 */
	@Override
	public List<FeedBackBean> getAllCompletedTrainings(long id)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.getAllCompletedTrainingList(id);
	}

}
