package com.pumex.tms.report.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumex.tms.notifications.service.NotificationServiceInterface;
import com.pumex.tms.onlinetest.bean.AnsweringBean;
import com.pumex.tms.onlinetest.dao.AttendTestDao;
import com.pumex.tms.report.service.PerformanceAnalysisServiceInterface;
import com.pumex.tms.usermanagement.service.UserManagementServiceInterface;
import com.pumex.tms.util.AppConstants;

/**
 * 
 * This class controls all home page related requests.
 * 
 * @Author JINSHAD P.T.
 * @Date 31/10/2016
 */

@Controller
public class PerformanceAnalysisController {

	@Autowired
	UserManagementServiceInterface userService;

	@Autowired
	PerformanceAnalysisServiceInterface paServiceInterface;

	@Autowired
	AttendTestDao testDao;

	/*
	 * Method to get attendee user
	 * 
	 * @return List of attendees
	 */
	@RequestMapping(value = { "/getallattendees/" }, method = RequestMethod.GET)
	public @ResponseBody List getAllAttendees() {

		try {

			return userService
					.getAllUserNamesByRoleId(AppConstants.USER_ROLES.ATTENDEE_ROLE);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * Method to load performance analysis
	 * 
	 * @return HashMap of different details
	 */
	@RequestMapping(value = "/loadperformanceanalysis/{attendeeId}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> loadHomePageData(
			@PathVariable("attendeeId") long attendeeId) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		data.put("userDetails", userService.editUser(attendeeId));

		data.put("attendeePrograms",
				paServiceInterface.getAllTrainingPrograms(attendeeId));

		data.put("attendeeTests", paServiceInterface.getAllTests(attendeeId));

		data.put("programDetails",
				paServiceInterface.getProgramDetails(attendeeId));

		return data;
	}

	/*
	 * Method to load performance analysis
	 * 
	 * @return HashMap of different details
	 */
	@RequestMapping(value = "/showattendeetestresult/{testId}/{attendeeId}", method = RequestMethod.GET)
	public @ResponseBody AnsweringBean loadHomePageData(
			@PathVariable("testId") long testId,
			@PathVariable("attendeeId") long attendeeId) throws Exception {

		return testDao.showResult(testId, attendeeId);

	}

	/*
	 * Method to load feedbacks
	 * 
	 * @return List of feedbacks
	 */
	@RequestMapping(value = "/getattendeefeedbackfromtopic/{userId}/{topicId}", method = RequestMethod.GET)
	public @ResponseBody List viewFeedback(
			@PathVariable("topicId") long topicId,
			@PathVariable("userId") long userId) throws Exception {

		return paServiceInterface.getFeedBacks(userId, topicId);

	}
}