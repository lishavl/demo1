/*
 * @Author *** Reshma Manoj ***
 */

package com.pumex.tms.notifications.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.configurations.Encryption;
import com.pumex.tms.configurations.Mailer;
import com.pumex.tms.models.Notifications;
import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.notifications.bean.NotificationBean;
import com.pumex.tms.notifications.dao.NotificationDaoInterface;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.trainingmanagement.bean.TrainerTopicBean;
import com.pumex.tms.util.CommonUtil;

@Service("notificationservice")
public class NotificationServiceImpl implements NotificationServiceInterface {

	@Autowired
	NotificationDaoInterface notificationDaoInterface;

	// @Override
	// public List<NotificationBean> getUpcomingTrainingList() throws Exception
	// {
	// // TODO Auto-generated method stub
	// return notificationDaoInterface.getUpcomingTrainingList();
	// }
	//
	// @Override
	// public void saveNotification(List<Notifications> notifications)
	// throws Exception {
	// // TODO Auto-generated method stub
	//
	// notificationDaoInterface.saveNotification(notifications);
	//
	// }
	//
	// @Override
	// public List<NotificationBean> getDelayedTrainingList() throws Exception {
	// // TODO Auto-generated method stub
	// return notificationDaoInterface.getDelayedTrainingList();
	// }
	//
	// @Override
	// public List<NotificationBean> getUpcomingTrainingList(long userId)
	// throws Exception {
	// // TODO Auto-generated method stub
	// System.out.println("userId in service" + userId);
	// return notificationDaoInterface.getUpcomingTrainingList(userId);
	// }
	//
	// @Override
	// public List<NotificationBean> getDelayedTrainingList(long userId)
	// throws Exception {
	// // TODO Auto-generated method stub
	// System.out.println("userId in service" + userId);
	// return notificationDaoInterface.getDelayedTrainingList(userId);
	// }
	//
	// @Override
	// public List<NotificationBean> getDelayedTrainingListforAdmin()
	// throws Exception {
	// // TODO Auto-generated method stub
	// return notificationDaoInterface.getDelayedTrainingListforAdmin();
	// }

	@Override
	public void getTodaysTrainingList() throws Exception {
		// TODO Auto-generated method stub

		List<NotificationBean> todaystraininglist = notificationDaoInterface
				.getTodaysTrainingList();

		if (!todaystraininglist.isEmpty()) {

			for (NotificationBean test : todaystraininglist) {
				boolean mail = false;
				String message = "Hi " + test.getFirstName() + " "
						+ test.getLastName() + ",<br>" + "<br>"
						+ test.getTopicname() + " has been scheduled on "
						+ test.getSdate() + "."
						+ " Please consider this as a reminder. <br>"
						+ "Thanks & Regards" + "<br>" + "Team TMS";
				Mailer.sendMail(test.getEmail(), "Training Reminder", message);
				System.out.println(message);
			}

		}

	}
	
	@Override
	public void getTodayEndingTrainingList() throws Exception {
		// TODO Auto-generated method stub
		
		List<NotificationBean> todaysendtraininglist = notificationDaoInterface
				.getTodaysEndTrainingList();

		if (!todaysendtraininglist.isEmpty()) {

			for (NotificationBean test : todaysendtraininglist) {
				boolean mail = false;
				String message = "Hi " + test.getFirstName() + " "
						+ test.getLastName() + ",<br>" + "<br>"
						+ test.getTopicname() + " has been ends on today."
						+ "Please don't forget to mark attendace before "+ test.getSdate()+ " ,and create test if not already created. Please consider this as a reminder. <br>"
						+ "Thanks & Regards" + "<br>" + "Team TMS";
				Mailer.sendMail(test.getEmail(), "Attendance/Test Reminder", message);
				
				System.out.println(message);
			}

		}
		
	}

	@Override
	public void getUpcomingTestList(long id) throws Exception {
		// TODO Auto-generated method stub

		List<NotificationBean> testlist = notificationDaoInterface
				.getPendingTestList(id);

		if (!testlist.isEmpty()) {

			for (NotificationBean l : testlist) {
				Notifications notifications = new Notifications();
				notifications.setAdminstatus(0);
				notifications.setStatus(0);
				TrainingTopic topic = new TrainingTopic();
				topic.setId(l.getTopicid());
				notifications.setTraining(topic);
				notifications.setType(3);
				notifications.setUserId(l.getUserid());
				notificationDaoInterface.saveNotifications(notifications);
			}

			for (NotificationBean test : testlist) {
				boolean mail = false;
				String message = "Hi "
						+ test.getFirstName()
						+ " "
						+ test.getLastName()
						+ ",<br>"
						+ "<br>"
						+ test.getTitle()
						+ " is pending for you. "
						+ " Please consider this as a reminder and attend the test as soon as possible. <br>"
						+ "Thanks & Regards" + "<br>" + "Team TMS";
				Mailer.sendMail(test.getEmail(), "Test Reminder", message);
			}

		}
	}

	@Override
	public void getFeedbackList(long topicid) throws Exception {
		// TODO Auto-generated method stub

		List<NotificationBean> feedbacklist = notificationDaoInterface
				.getPendingFeedbackList(topicid);

		if (!feedbacklist.isEmpty()) {

			for (NotificationBean test : feedbacklist) {
				boolean mail = false;
				String message = "Hi "
						+ test.getFirstName()
						+ " "
						+ test.getLastName()
						+ ",<br>"
						+ "<br>"
						+ " You have to fill the feedback form for "
						+ test.getTopicname()
						+ "."
						+ " Please consider this as a reminder and fill it as soon as possible. <br>"
						+ "Thanks & Regards" + "<br>" + "Team TMS";
				Mailer.sendMail(test.getEmail(), "Feedback Reminder", message);
			}

		}

	}

	@Override
	public List<NotificationBean> getTestList() throws Exception {
		// TODO Auto-generated method stub
		return notificationDaoInterface.getgetTestList();
	}

	@Override
	public List<NotificationBean> getTestList(long userId) throws Exception {
		// TODO Auto-generated method stub
		return notificationDaoInterface.getgetTestList(userId);
	}

	@Override
	public List<NotificationBean> getFeedback(long userId) throws Exception {
		// TODO Auto-generated method stub
		return notificationDaoInterface.getFeedbacks(userId);
	}

	@Override
	public List<NotificationBean> getTrainings(long userId) throws Exception {
		// TODO Auto-generated method stub
		return notificationDaoInterface.getTrainings(userId);
	}

	@Override
	public void deleteNotification(long testid, long userId) throws Exception {
		// TODO Auto-generated method stub

		long trainingId = notificationDaoInterface.getTrainingId(testid);
		notificationDaoInterface.deleteNotification(trainingId, userId);

		System.out.println("UserId--->" + userId);

	}

	@Override
	public List getAllNotifications(long userId, long roleId) throws Exception {
		// TODO Auto-generated method stub

		List list = new ArrayList<NotificationBean>();

		if (roleId == 1) {

			List<NotificationBean> pendingtests = notificationDaoInterface
					.getAdminTrainingList();
			List<NotificationBean> pendingfeedbacks = notificationDaoInterface
					.getAdminFeedbackList();

			if (pendingtests != null)
				list.addAll(pendingtests);

			if (pendingfeedbacks != null)
				list.addAll(pendingfeedbacks);

		}
		if (roleId == 2) {
			List<NotificationBean> trainertrainings = notificationDaoInterface
					.getTrainerTrainingList(userId);
			List<NotificationBean> trainerfeedbacks = notificationDaoInterface
					.getTrainerFeedbackList(userId);
			List<NotificationBean> pendingtests = notificationDaoInterface
					.getTrainerTestList(userId);
			List<NotificationBean> pendingfeedbacks = notificationDaoInterface
					.getTrainerPendingFeedbackList(userId);

			if (!trainertrainings.isEmpty())
				list.addAll(trainertrainings);

			if (!trainerfeedbacks.isEmpty())
				list.addAll(trainerfeedbacks);

			if (pendingtests != null)
				list.addAll(pendingtests);

			if (pendingfeedbacks != null)
				list.addAll(pendingfeedbacks);
		}
		return list;

	}

	@Override
	public void updateStatus(long notificationid, long roleId) throws Exception {
		// TODO Auto-generated method stub

		if (roleId == 1) {

			notificationDaoInterface.updateAdminStatus(notificationid);
		} else {

			notificationDaoInterface.updateuserStatus(notificationid);
		}

	}

}
