package com.pumex.tms.trainingmanagement.service;

import java.util.List;

import com.pumex.tms.models.AttendeeFeedback;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.notifications.bean.NotificationBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean;
import com.pumex.tms.usermanagement.bean.FeedBackBean;
import com.pumex.tms.usermanagement.bean.Register;

public interface TrainerTrainingListServiceInterface {

	List<TrainerTrainingListBean> getMyTrainingList(long trainerid)
			throws Exception;

	List<Register> fetchAllAttendees(long topicid) throws Exception;

	void addAttendee(long[] attendees, long topicid) throws Exception;

	List<TrainerTrainingListBean> getTrainingDetails(long id, long trainerId)
			throws Exception;

	FeedBackBean getFeedBack(long attendeeid, long trainerId, long topicid)
			throws Exception;

	List getAttendees(long topicid) throws Exception;

	List<TrainerTrainingListBean> getCompletedTrainingList(long trainerid)
			throws Exception;

	List<TrainerTrainingListBean> getAllTrainings() throws Exception;

	List<TrainerTrainingListBean> getAllCompletedTrainings() throws Exception;

	List<UserDetails> getUserRoles(long userId) throws Exception;

	List<TrainerTrainingListBean> getAttendeeTrainingList(long trainerid)
			throws Exception;

	List<TrainerTrainingListBean> getAttendeesCompletedTrainingList(
			long trainerid) throws Exception;
	
	public List<TrainerTrainingListBean> getAllTrainingList(long userId,
			long roleId) throws Exception;

	List getAllTrainers(long topicid,long id) throws Exception;

	List<NotificationBean> getTrainingDetails(long topicid) throws Exception;

	void setEmailStatus(long topicid) throws Exception;

	List<TrainerTrainingListBean> getAllTrainingsBydate(String strtdate,
			String enddte) throws Exception;

	List<TrainerTrainingListBean> getMyTrainingListByDate(long trainerid,
			String strtdate, String enddte) throws Exception;

	List<TrainerTrainingListBean> getAttendeeTrainingListByDate(long trainerid,
			String strtdate, String enddte) throws Exception;

	List<TrainerTrainingListBean> getAllUpcomingTrainingsBydate(
			String strtdate, String enddte) throws Exception;

	List<TrainerTrainingListBean> getUpcomingTrngForTrainerByDate(
			long trainerid, String strtdate, String enddte) throws Exception;

	List<TrainerTrainingListBean> getUpcmingAttendeeTrainingListByDate(
			long trainerid, String strtdate, String enddte) throws Exception;

}
