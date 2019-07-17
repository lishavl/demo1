/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.trainingmanagement.dao;

import java.util.List;

import com.pumex.tms.trainingmanagement.bean.TopicAttendeeBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;

public interface MarkAttendanceDao {
	List<TrainerTopicBean> getAllTopicNames(long id) throws Exception;

	List<TopicAttendeeBean> getallAttendees(long id) throws Exception;

	void addAttendance(List<Long> id, long testid) throws Exception;

	void cancelAttendance(long id, long testid) throws Exception;

	List<TrainerTopicBean> getAllTopicNamesForAdmin() throws Exception;

	void cancelTopicCompletionStatus(long testid, long id) throws Exception;

	void cancelAttendaceForAllAttendees(List<Long> list, long testid) throws Exception;

	boolean checkDateForMarkAttendance(long testid) throws Exception;

}
