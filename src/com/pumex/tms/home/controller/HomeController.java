package com.pumex.tms.home.controller;

import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumex.tms.home.service.DashboardServiceInterface;
import com.pumex.tms.notifications.service.NotificationServiceInterface;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.usermanagement.dao.UserManagementDao;
import com.pumex.tms.usermanagement.service.UserAccessService;
import com.pumex.tms.util.AppConstants;

/**
 * 
 * This class controls all home page related requests.
 * 
 * @Author JINSHAD P.T.
 * @Date 17/06/2016
 */

@Controller
public class HomeController {

	@Autowired
	UserManagementDao userDao;

	@Autowired
	UserAccessService userAccessService;

	@Autowired
	DashboardServiceInterface dashboardServiceInterface;

	@Autowired
	NotificationServiceInterface notificationServiceInterface;

	/*
	 * Starting point of the application.
	 * 
	 * @return view of homepage.jsp
	 */
	@RequestMapping(value = { "/homepage", "/" }, method = RequestMethod.GET)
	public String homePage(Model model, HttpSession session) {

		try {

			TimeZone.setDefault(TimeZone.getTimeZone("GMT+05:30"));

			if (AppConstants.BASE_PATH == null)
				AppConstants.BASE_PATH = session.getServletContext()
						.getRealPath("/") + "/";

			if (session.getAttribute("userId") != null) {
				model.addAttribute("userDetails",
						session.getAttribute("userDetails"));
				model.addAttribute("userId", session.getAttribute("userId"));
				model.addAttribute("roleId", session.getAttribute("roleId"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "homepage";
	}

	/*
	 * Method to check session is exist or not
	 * 
	 * @return true or false
	 */
	@RequestMapping(value = { "/issessionexist/{roleId}" }, method = RequestMethod.GET)
	public @ResponseBody boolean isSessionExist(
			@PathVariable("roleId") long roleId, HttpSession session) {

		try {

			if (session.getAttribute("roleId") != null) {
				if ((long) session.getAttribute("roleId") == roleId) {
					return true;
				} else {
					return false;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * Method to load data to dash-board
	 * 
	 * @return HashMap of all details
	 */
	@RequestMapping(value = "/loadhomepagedata/", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> loadHomePageData(
			HttpSession session) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();

		List menus = null;
		if (session.getAttribute("userId") != null) {

			String IDs = "(";

			if ((boolean) session.getAttribute("isAdmin")) {
				IDs += AppConstants.USER_ROLES.ADMIN_ROLE + ",";
			}
			if ((boolean) session.getAttribute("isTrainer")) {
				IDs += AppConstants.USER_ROLES.TRAINER_ROLE + ",";
			}
			if ((boolean) session.getAttribute("isAttendee")) {
				IDs += AppConstants.USER_ROLES.ATTENDEE_ROLE + ",";
			}
			IDs += "0)";

			menus = userAccessService.getAvailableOptions(IDs);
		}

		data.put("menus", menus);

		if (session.getAttribute("userId") != null) {
			data.put("userId", session.getAttribute("userId"));
			data.put("roleId", session.getAttribute("roleId"));
			data.put("userDetails", session.getAttribute("userDetails"));

			data.put("testsForCriteria", dashboardServiceInterface.getAllTests(
					(long) session.getAttribute("userId"),
					(long) session.getAttribute("roleId")));

			if ((long) session.getAttribute("roleId") == AppConstants.USER_ROLES.ATTENDEE_ROLE)
				data.put("pendingTests", dashboardServiceInterface
						.getPendingTests((long) session.getAttribute("userId")));

			if ((long) session.getAttribute("roleId") != AppConstants.USER_ROLES.ATTENDEE_ROLE)
				data.put("topTrainers",
						dashboardServiceInterface.getTopTrainers());

			data.put("allnotifications", notificationServiceInterface
					.getAllNotifications((long) session.getAttribute("userId"),
							(long) session.getAttribute("roleId")));

			data.put("statistics", dashboardServiceInterface
					.getStatisticsDetails((long) session.getAttribute("userId"),
							(long) session.getAttribute("roleId")));

		}

		data.put("upcomingPgms",
				dashboardServiceInterface.getUpcomingTrainingPrograms());

		data.put("completedPgms",
				dashboardServiceInterface.getCompletedTrainingPrograms());

		data.put("forumThreads",
				dashboardServiceInterface.getLatestForumThreads());

		return data;
	}

	/*
	 * Method to get result of given test id (for graph)
	 * 
	 * @return test result (TestResultBean)
	 */
	@RequestMapping(value = { "/gettestpassedresult/{testid}" }, method = RequestMethod.GET)
	public @ResponseBody TestResultBean getTestResult(
			@PathVariable("testid") long testId) throws Exception {
		try {
			return dashboardServiceInterface.getTestResult(testId);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * Method to update dashboard notification status
	 * 
	 * @return List of pending notification
	 */
	@RequestMapping(value = { "/updatestatus/{notificationid}" }, method = RequestMethod.POST)
	public @ResponseBody List UpdateNotification(
			@PathVariable("notificationid") long notificationid,
			HttpSession session) throws Exception {
		try {
			notificationServiceInterface.updateStatus(notificationid,
					(long) session.getAttribute("roleId"));
			return notificationServiceInterface.getAllNotifications(
					(long) session.getAttribute("userId"),
					(long) session.getAttribute("roleId"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/*
	 * Method to logout
	 * 
	 * @return redirect to homepage with logout
	 */
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:";
	}
}