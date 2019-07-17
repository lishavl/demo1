/*
 * @Author *** Reshma Manoj ***
*/

package com.pumex.tms.notifications.dao;

import java.sql.Timestamp;
import java.util.List;

import com.pumex.tms.models.Notifications;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.notifications.bean.NotificationBean;

public interface NotificationDaoInterface {

//	List<NotificationBean> getUpcomingTrainingList() throws Exception;
//
//	void saveNotification(List<Notifications> notifications) throws Exception;
//
//	List<NotificationBean> getDelayedTrainingList() throws Exception;
//
//	List<NotificationBean> getUpcomingTrainingList(long userId) throws Exception;
//
//	List<NotificationBean> getDelayedTrainingList(long userId) throws Exception;
//
//	List<NotificationBean> getDelayedTrainingListforAdmin() throws Exception;

	List<NotificationBean> getTodaysTrainingList() throws Exception;

	List<NotificationBean> getPendingTestList(long id) throws Exception;

	List<NotificationBean> getPendingFeedbackList(long topicid) throws Exception;

	void saveNotification(Notifications testnotifications) throws Exception;

	List<NotificationBean> getgetTestList() throws Exception;

	List<NotificationBean> getgetTestList(long userId) throws Exception;

	List<Long> getTrainers(long topicid) throws Exception;

	List<NotificationBean> getFeedbacks(long userId) throws Exception;

	List<NotificationBean> getTrainings(long userId) throws Exception;

	void saveNotifications(Notifications notifications) throws Exception;

	void deleteNotification(long trainingId, long userId) throws Exception;

	long getTrainingId(long testid) throws Exception;

	 List<NotificationBean> getTrainerTrainingList(long userId) throws Exception;

	 List<NotificationBean> getTrainerFeedbackList(long userId) throws Exception;

	List<NotificationBean> getTrainerTestList(long userId) throws Exception;

	List<NotificationBean> getTrainerPendingFeedbackList(long userId) throws Exception;

	List<NotificationBean> getAdminTrainingList() throws Exception;

	List<NotificationBean> getAdminFeedbackList() throws Exception;

	void updateAdminStatus(long notificationid) throws Exception;

	void updateuserStatus(long notificationid) throws Exception;

	List<NotificationBean> getTodaysEndTrainingList() throws Exception;
		
}
