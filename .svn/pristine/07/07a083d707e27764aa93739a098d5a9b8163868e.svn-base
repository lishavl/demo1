/*
 * @Author Reshma Manoj
 */

package com.pumex.tms.trainingmanagement.service;

import java.util.List;

import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;
import com.pumex.tms.usermanagement.bean.Register;

public interface TrainingScheduleServiceInterface {

	void scheduleTraining(TrainingScheduleBean training, long Catid,
			long[] trainerlist, long[] attendeelist) throws Exception;

	long getParentID(String maincategory) throws Exception;

	List<Register> FetchTrainers(String text) throws Exception;

	long saveCategory(String maincategory) throws Exception;

	long getCategoryId(String maincategory) throws Exception;

	long saveSubCategory(String subCategoryName, long categoryid)
			throws Exception;

	List<TrainingTopic> getAllTrainingSchedules() throws Exception;

	long getTraining(String topic, String sdate, String edate) throws Exception;

	TrainingScheduleBean getScheduleById(long id) throws Exception;

	void deleteSchedule(long id) throws Exception;

	void updateSchedule(TrainingScheduleBean trainingScheduleBean,
			long categoryid, long[] trainerlist, long[] attendeelist)
			throws Exception;

	List<Register> FetchAttendees(String text) throws Exception;

	List<Register> FetchAttendee(String text) throws Exception;

	List<Register> FetchTrainers(String text, long userId) throws Exception;

	List<Register> FetchAttendee(String text, long deptid)throws Exception;
}
