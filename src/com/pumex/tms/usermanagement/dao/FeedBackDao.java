/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.usermanagement.dao;

import java.util.List;

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

public interface FeedBackDao {

	public List<FeedBackBean> getAllFeedBackList(long id) throws Exception;

	public FeedBackBean getTrainerName(long id) throws Exception;

	public void updateAttendeeFeedBack(AttendeeFeedback feedback, long trainerId);

	public FeedBackBean viewFeedback(long id, long topicid, long trainerid)
			throws Exception;

	public long feedBackExist(long id, long topicid, long trainerid)
			throws Exception;

	public List<FeedBackBean> getAllUpcomingTrainingList(long id);

	public UserDetails getAllAttendeeDetails(long id);

	public TrainingTopic getAllTopicNames(long topicid);

	public void saveInterestedAttendee(TopicAttendee topicAttendee)
			throws Exception;

	public List<TrainingTopic> getAllTopicNamesForAdmin() throws Exception;

	public List<TopicTrainer> getAllTrainerNamesForAdmin(long id)
			throws Exception;

	public List<TopicAttendeeBean> getAllAttendeeListAdmin(long topicid,
			long trainerid) throws Exception;

	public void setStatusAfterMail(long id,long topicid);

	public List<TestResultBean> getAllTestResult(long id) throws Exception;

	public long isAttendeeExist(long topicid, long userid) throws Exception;

	public void saveInterestedAttendeeAfterMail(TopicAttendee topicAttendee) throws Exception;

	public List<TrainerTopicBean> getAllTrainerDetailsForMail(long topicid) throws Exception;

	public void saveInterestedAttendee(long topicid, long userid) throws Exception;

	public List<FeedBackBean> getAllCompletedTrainingList(long id)throws Exception;

}
