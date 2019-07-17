/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.trainingmanagement.service;

import java.util.List;

import com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.trainingmanagement.bean.TrainingScheduleBean;

public interface MarkAttendaceService {

	List<TrainerTopicBean> getAllTopicNames(long id) throws Exception;

	List<TopicAttendeeBean> getAllAttendees(long id) throws Exception;

	void addAttendance(List<Long> id, long testid) throws Exception;

	void cancelAttendance(long id, long testid) throws Exception;

	List<TrainerTopicBean> getAllTopicNamesForAdmin() throws Exception;

	void cancelTopicCompletionStatus(long testid, long id) throws Exception;

	void CancelAttendanceForAllAttndees(List<Long> list, long testid) throws Exception;

	boolean getDateForAttendance(long testid) throws Exception;
}
