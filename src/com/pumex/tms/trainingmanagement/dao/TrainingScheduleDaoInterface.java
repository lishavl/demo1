/*
 * @Author Reshma Manoj
 */

package com.pumex.tms.trainingmanagement.dao;

import java.sql.Timestamp;
import java.util.List;

import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.usermanagement.bean.Register;

public interface TrainingScheduleDaoInterface {

	List<TrainingTopic> getCategory() throws Exception;

	void scheduleTraining(TrainingTopic trainingtopic, long[] trainerlist,
			long[] attendeelist) throws Exception;

	long getParentID(String maincategory) throws Exception;

	List<Register> FetchTrainers(String text) throws Exception;

	long saveCategory(String maincategory) throws Exception;

	long getCategoryId(String maincategory) throws Exception;

	long saveSubCategory(String subCategoryName, long categoryid)
			throws Exception;

	long getTraining(String topic, Timestamp startdate, Timestamp enddate)
			throws Exception;

	List<TrainingTopic> getAllTrainingSchedules() throws Exception;

	TrainingTopic getScheduleDetails(long id) throws Exception;

	List<Register> getTrainers(long id) throws Exception;

	List<Register> getAttendee(long id) throws Exception;

	void deleteSchedule(long id) throws Exception;

	void UpdateSchedule(TrainingTopic trainingTopic, long[] trainerlist,
			long[] attendeelist) throws Exception;

	String getMainCategory(long parentId) throws Exception;

	List<Register> FetchAttendees(String text) throws Exception;

	List<Register> FetchAttendee(String text) throws Exception;

	List<Register> FetchTrainers(String text, long userId) throws Exception;

	List<Register> FetchAttendee(String text, long deptid) throws Exception;

}
