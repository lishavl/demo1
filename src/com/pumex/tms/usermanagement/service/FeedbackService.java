/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.usermanagement.service;

import java.util.List;

import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.onlinetest.bean.TestReportBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.usermanagement.bean.FeedBackBean;

public interface FeedbackService {

	public List<FeedBackBean> getAllFeedBackList(long id) throws Exception;

	public FeedBackBean getTrainerName(long id) throws Exception;

	public void updateFeedback(FeedBackBean feedbackbean, long id)
			throws Exception;

	public FeedBackBean viewFeedback(long id, long topicid, long trainerid)
			throws Exception;

	public long checkFeedbackExist(long id, long topicid, long trainerid)
			throws Exception;

	public List<FeedBackBean> getAllUpcomingTrainings(long id);

	public UserDetails getAttendeeDetails(long id);

	public TrainingTopic getAllTopicDetails(long topicid);

	public void addAttendeeDetails(long topicid, long userid) throws Exception;
	
	public void addAttendeeDetailsAfterMail(long topicid, long userid) throws Exception;

	public List<TrainingTopic> getAllTopicNames() throws Exception;

	public List<TopicTrainer> getAllTrainerNamesForAdmin(long id)
			throws Exception;

	public List<TopicAttendeeBean> getAllAttendeeForAdminFeedback(long topicid,
			long trainerid) throws Exception;

	public void setStatusAftrMail(long id,long topicid) throws Exception;

	public List<TestResultBean> getAllTestResult(long id) throws Exception;

	public long isAttendeeExist(long topicid, long userid) throws Exception;

	public List<TrainerTopicBean> getAllTrainerNamesForMail(long topicid)throws Exception;

	public List<FeedBackBean> getAllCompletedTrainings(long id)throws Exception;

}
