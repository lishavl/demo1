/*
 * @Author *** Reshma Manoj ***
*/

package com.pumex.tms.notifications.service;

import java.util.List;

import com.pumex.tms.models.Notifications;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.notifications.bean.NotificationBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;

public interface NotificationServiceInterface {

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

	void getTodaysTrainingList() throws Exception;

	void getUpcomingTestList(long id) throws Exception;

	void getFeedbackList(long topicid) throws Exception;

	List<NotificationBean> getTestList() throws Exception;

	List<NotificationBean> getTestList(long userId) throws Exception;

	List<NotificationBean> getFeedback(long userId) throws Exception;

	List<NotificationBean> getTrainings(long userId) throws Exception;

	void deleteNotification(long testid, long userId) throws Exception;

	List  getAllNotifications(long userId, long roleId) throws Exception;

	void updateStatus(long notificationid, long userId) throws Exception;

	void getTodayEndingTrainingList() throws Exception;

}
