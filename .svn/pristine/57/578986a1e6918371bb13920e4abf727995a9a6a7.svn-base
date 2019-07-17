package com.pumex.tms.trainingmanagement.dao;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.pumex.tms.models.AttendeeFeedback;
import com.pumex.tms.models.TopicAttendee;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.notifications.bean.NotificationBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean;
import com.pumex.tms.usermanagement.bean.FeedBackBean;
import com.pumex.tms.usermanagement.bean.Register;

public interface TrainerTrainingListDaoInterface {

	List<TrainerTrainingListBean> getMyTrainingList(long trainerid)
			throws Exception;

	List<Register> fetchAllAttendees(long topicid) throws Exception;

	void addAttendee(long[] attendees, TrainingTopic topic) throws Exception;

	List<TrainerTrainingListBean> getTrainingDetails(long topicid,
			long trainerId) throws Exception;

	FeedBackBean getFeedBackList(long attendeeid, long trainerId, long topicid)
			throws Exception;

	List getAttendees(long topicid) throws Exception;

	List<TrainerTrainingListBean> getCompletedTrainingList(long trainerid)
			throws Exception;

	List<TrainerTrainingListBean> getAllTrainingList() throws Exception;

	List<TrainerTrainingListBean> getAllCompletedTrainingList()
			throws Exception;

	List<UserDetails> getUserRoles(long userId) throws Exception;

	List<TrainerTrainingListBean> getAttendeeTrainingList(long trainerid)
			throws Exception;

	List<TrainerTrainingListBean> getAttendeesCompletedTrainingList(
			long trainerid) throws Exception;

	public List<TrainerTrainingListBean> getAllTrainingList(long userId,
			long roleId) throws Exception;

	List getAllTrainers(long topicid, long id) throws Exception;

	List<NotificationBean> getTrainingDetails(long topicid) throws Exception;

	void setEmailStatus(long topicid) throws Exception;

	List<TrainerTrainingListBean> getAllShedulesByDate(String strtdate,
			String enddte) throws Exception;

	List<TrainerTrainingListBean> getCompletedTrainingListForTrainerByDate(
			long trainerid, String strtdate, String enddte) throws Exception;

	List<TrainerTrainingListBean> getAttendeeTrainingListByDate(long trainerid,
			String strtdate, String enddte) throws Exception;

	List<TrainerTrainingListBean> getAllUpcomingTrainingsBydate(
			String strtdate, String enddte) throws Exception;

	List<TrainerTrainingListBean> getUpcomingTrngForTrainerByDate(
			long trainerid, String strtdate, String enddte) throws Exception;

	List<TrainerTrainingListBean> getUpcmingAttendeeTrainingListByDate(
			long trainerid, String strtdate, String enddte) throws Exception;

}
