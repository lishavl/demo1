/*
 * @Author Reshma Manoj
 */

package com.pumex.tms.trainingmanagement.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.TrainingCategory;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;
import com.pumex.tms.trainingmanagement.dao.TrainingScheduleDaoInterface;
import com.pumex.tms.usermanagement.bean.Register;

@Service("trainingscheduleservice")
public class TrainingScheduleServiceImpl implements
		TrainingScheduleServiceInterface {

	@Autowired
	TrainingScheduleDaoInterface trainingScheduleDaoInterface;

	@Override
	public void scheduleTraining(TrainingScheduleBean training, long Catid,
			long[] trainerlist, long[] attendeelist) throws Exception {
		// TODO Auto-generated method stub

		TrainingTopic trainingtopic = new TrainingTopic();

		TrainingCategory trainingCategory = new TrainingCategory();
		trainingCategory.setId(Catid);

		String startdatetime = training.getStartDateTime();
		Timestamp startdate = Timestamp.valueOf(startdatetime);

		String enddatetime = training.getEndDateTime();
		Timestamp enddate = Timestamp.valueOf(enddatetime);

		trainingtopic.setTopic(training.getTopic());
		trainingtopic.setDescription(training.getDescription());
		trainingtopic.setCategory(trainingCategory);
		trainingtopic.setStartDateTime(startdate);
		trainingtopic.setEndDateTime(enddate);
		trainingtopic.setStatus(1);

		trainingScheduleDaoInterface.scheduleTraining(trainingtopic,
				trainerlist, attendeelist);

	}

	@Override
	public long getParentID(String maincategory) throws Exception {
		// TODO Auto-generated method stub
		return trainingScheduleDaoInterface.getParentID(maincategory);
	}

	@Override
	public List<Register> FetchTrainers(String text) throws Exception {
		// TODO Auto-generated method stub
		return trainingScheduleDaoInterface.FetchTrainers(text);
	}

	@Override
	public List<Register> FetchAttendee(String text) throws Exception {
		// TODO Auto-generated method stub
		return trainingScheduleDaoInterface.FetchAttendee(text);
	}

	@Override
	public long saveCategory(String maincategory) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println("Main Category in Service is" + maincategory);

		return trainingScheduleDaoInterface.saveCategory(maincategory);
	}

	@Override
	public long getCategoryId(String maincategory) throws Exception {
		// TODO Auto-generated method stub
		return trainingScheduleDaoInterface.getCategoryId(maincategory);
	}

	@Override
	public long saveSubCategory(String subCategoryName, long categoryid)
			throws Exception {
		// TODO Auto-generated method stub
		return trainingScheduleDaoInterface.saveSubCategory(subCategoryName,
				categoryid);
	}

	@Override
	public long getTraining(String topic, String sdate, String edate)
			throws Exception {
		// TODO Auto-generated method stub

		Timestamp startdate = Timestamp.valueOf(sdate);
		Timestamp enddate = Timestamp.valueOf(edate);
		return trainingScheduleDaoInterface.getTraining(topic, startdate,
				enddate);
	}

	@Override
	public List<TrainingTopic> getAllTrainingSchedules() throws Exception {
		// TODO Auto-generated method stub
		return trainingScheduleDaoInterface.getAllTrainingSchedules();
	}

	@Override
	public TrainingScheduleBean getScheduleById(long id) throws Exception {
		// TODO Auto-generated method stub

		TrainingTopic trainingTopic = trainingScheduleDaoInterface
				.getScheduleDetails(id);
		
		TrainingScheduleBean trainingScheduleBean = new TrainingScheduleBean();
		trainingScheduleBean.setId(trainingTopic.getId());
		trainingScheduleBean.setTopic(trainingTopic.getTopic());
		trainingScheduleBean.setDescription(trainingTopic.getDescription());
		String sdate = trainingTopic.getStartDateTime().toString();
		String edate = trainingTopic.getEndDateTime().toString();
		trainingScheduleBean.setStartDateTime(sdate);
		trainingScheduleBean.setEndDateTime(edate);
		List<Register> trainers = trainingScheduleDaoInterface.getTrainers(id);
		trainingScheduleBean.setTrainerdetails(trainers);
		List<Register> attendee = trainingScheduleDaoInterface.getAttendee(id);
		trainingScheduleBean.setAttendeedetails(attendee);
		trainingScheduleBean.setCategory(trainingTopic.getCategory());
		trainingScheduleBean.setMaincategory(trainingScheduleDaoInterface
				.getMainCategory(trainingScheduleBean.category.parentId));
		return trainingScheduleBean;
	}

	@Override
	public void deleteSchedule(long id) throws Exception {
		// TODO Auto-generated method stub
		trainingScheduleDaoInterface.deleteSchedule(id);
	}

	@Override
	public void updateSchedule(TrainingScheduleBean training, long categoryid,
			long[] trainerlist, long[] attendeelist) throws Exception {
		// TODO Auto-generated method stub

		long id = training.getId();

		TrainingTopic trainingtopic = trainingScheduleDaoInterface
				.getScheduleDetails(id);

		TrainingCategory trainingCategory = new TrainingCategory();
		trainingCategory.setId(categoryid);

		String startdatetime = training.getStartDateTime();
		Timestamp startdate = Timestamp.valueOf(startdatetime);

		String enddatetime = training.getEndDateTime();
		Timestamp enddate = Timestamp.valueOf(enddatetime);

		trainingtopic.setTopic(training.getTopic());
		trainingtopic.setDescription(training.getDescription());
		trainingtopic.setCategory(trainingCategory);
		trainingtopic.setStartDateTime(startdate);
		trainingtopic.setEndDateTime(enddate);

		trainingScheduleDaoInterface.UpdateSchedule(trainingtopic, trainerlist,
				attendeelist);

	}

	@Override
	public List<Register> FetchAttendees(String text) throws Exception {
		// TODO Auto-generated method stub
		return trainingScheduleDaoInterface.FetchAttendees(text);
	}

	@Override
	public List<Register> FetchTrainers(String text, long userId)
			throws Exception {
		// TODO Auto-generated method stub
		return trainingScheduleDaoInterface.FetchTrainers(text,userId);
	}

	@Override
	public List<Register> FetchAttendee(String text, long deptid)
			throws Exception {
		// TODO Auto-generated method stub
		return trainingScheduleDaoInterface.FetchAttendee(text,deptid);
	}

}
