package com.pumex.tms.trainingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.AttendeeFeedback;
import com.pumex.tms.models.TopicAttendee;
import com.pumex.tms.models.TopicTrainer;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.notifications.bean.NotificationBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTrainingListBean;
import com.pumex.tms.trainingmanagement.dao.TrainerTrainingListDaoInterface;
import com.pumex.tms.usermanagement.bean.FeedBackBean;
import com.pumex.tms.usermanagement.bean.Register;

@Service("traininglistservice")
public class TrainerTrainingListServiceImpl implements
		TrainerTrainingListServiceInterface {

	@Autowired
	TrainerTrainingListDaoInterface trainerTrainingListDaoInterface;

	@Override
	public List<TrainerTrainingListBean> getMyTrainingList(long trainerid)
			throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getMyTrainingList(trainerid);
	}

	@Override
	public List<Register> fetchAllAttendees(long topicid) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.fetchAllAttendees(topicid);
	}

	@Override
	public void addAttendee(long[] attendees, long topicid) throws Exception {
		// TODO Auto-generated method stub

		TrainingTopic topic = new TrainingTopic();
		topic.setId(topicid);
		trainerTrainingListDaoInterface.addAttendee(attendees, topic);

	}

	@Override
	public List<TrainerTrainingListBean> getTrainingDetails(long topicid,
			long trainerId) throws Exception {
		// TODO Auto-generated method stub
		List<TrainerTrainingListBean> trainingbean = trainerTrainingListDaoInterface
				.getTrainingDetails(topicid, trainerId);
		return trainingbean;
	}

	@Override
	public FeedBackBean getFeedBack(long attendeeid, long trainerId,
			long topicid) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getFeedBackList(attendeeid,
				trainerId, topicid);
	}

	@Override
	public List getAttendees(long topicid) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAttendees(topicid);
	}

	@Override
	public List<TrainerTrainingListBean> getCompletedTrainingList(long trainerid)
			throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getCompletedTrainingList(trainerid);
	}

	@Override
	public List<TrainerTrainingListBean> getAllTrainings() throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAllTrainingList();
	}

	@Override
	public List<TrainerTrainingListBean> getAllCompletedTrainings()
			throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAllCompletedTrainingList();
	}

	@Override
	public List<UserDetails> getUserRoles(long userId) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getUserRoles(userId);
	}

	@Override
	public List<TrainerTrainingListBean> getAttendeeTrainingList(long trainerid)
			throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAttendeeTrainingList(trainerid);
	}

	@Override
	public List<TrainerTrainingListBean> getAttendeesCompletedTrainingList(
			long trainerid) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAttendeesCompletedTrainingList(trainerid);
	}
	
	@Override
	public List<TrainerTrainingListBean> getAllTrainingList(long userId,
			long roleId) throws Exception  {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAllTrainingList(userId, roleId);
	}

	@Override
	public List getAllTrainers(long topicid,long id) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAllTrainers(topicid,id);
	}

	@Override
	public List<NotificationBean> getTrainingDetails(long topicid)
			throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getTrainingDetails(topicid);
	}

	@Override
	public void setEmailStatus(long topicid) throws Exception {
		// TODO Auto-generated method stub
		
		trainerTrainingListDaoInterface.setEmailStatus(topicid);
		
	}

	@Override
	public List<TrainerTrainingListBean> getAllTrainingsBydate(String strtdate,
			String enddte) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAllShedulesByDate(strtdate,enddte);
	}

	@Override
	public List<TrainerTrainingListBean> getMyTrainingListByDate(
			long trainerid, String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getCompletedTrainingListForTrainerByDate(trainerid,strtdate,enddte);
	}

	@Override
	public List<TrainerTrainingListBean> getAttendeeTrainingListByDate(
			long trainerid, String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAttendeeTrainingListByDate(trainerid,strtdate,enddte);
	}

	@Override
	public List<TrainerTrainingListBean> getAllUpcomingTrainingsBydate(
			String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getAllUpcomingTrainingsBydate(strtdate,enddte);
	}

	@Override
	public List<TrainerTrainingListBean> getUpcomingTrngForTrainerByDate(
			long trainerid, String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getUpcomingTrngForTrainerByDate(trainerid,strtdate,enddte);
	}

	@Override
	public List<TrainerTrainingListBean> getUpcmingAttendeeTrainingListByDate(
			long trainerid, String strtdate, String enddte) throws Exception {
		// TODO Auto-generated method stub
		return trainerTrainingListDaoInterface.getUpcmingAttendeeTrainingListByDate(trainerid,strtdate,enddte);
	}
}
