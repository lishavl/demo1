package com.pumex.tms.notifications.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pumex.tms.notifications.service.NotificationServiceInterface;

/*
 * @Author *** Reshma Manoj ***
 * Created On *** 21-09-2016 ***
 */

@Component
@EnableScheduling
public class Scheduler {

	@Autowired
	NotificationServiceInterface notificationServiceInterface;

	@Scheduled(cron = "0 1 0 * * ?")
	public synchronized void demoServiceMethod() {
		try {
			notificationServiceInterface.getTodaysTrainingList();
			notificationServiceInterface.getTodayEndingTrainingList();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
