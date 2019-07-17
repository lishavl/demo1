package com.pumex.tms.onlinetest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumex.tms.models.OnlineTest;
import com.pumex.tms.notifications.service.NotificationServiceInterface;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.onlinetest.service.TestManagementService;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.Response;

/**
 * Class to manage test
 * 
 * @Author JINSHAD P.T.
 * @Date 17/06/2016
 */
@Controller
public class TestManagementController {

	@Autowired
	TestManagementService testManagementServiceInterface;

	@Autowired
	NotificationServiceInterface notificationServiceInterface;

	/*
	 * Method to get all tests based on user role
	 * 
	 * @return list of available tests
	 */
	@RequestMapping(value = "/getalltests/", method = RequestMethod.GET)
	public ResponseEntity<List<OnlineTest>> listAllTests(HttpSession session)
			throws Exception {

		List<OnlineTest> tests = tests = testManagementServiceInterface
				.getAllTestsOfUser((long) session.getAttribute("roleId"),
						(long) session.getAttribute("userId"));

		if (tests.isEmpty()) {
			return new ResponseEntity<List<OnlineTest>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<OnlineTest>>(tests, HttpStatus.OK);
	}

	/*
	 * Method to save/update test
	 * 
	 * @return response with save/update status
	 */
	@RequestMapping(value = "/saveonlinetest/", method = RequestMethod.PUT)
	public @ResponseBody Response saveOrUpdateTest(@Valid @RequestBody TestBean test)
			throws Exception {

		long testId = testManagementServiceInterface.saveOrUpdateTest(test);
		return new Response(1, "success", testId);
	}

	/*
	 * Method to delete test
	 * 
	 * @return response with delete status
	 */
	@RequestMapping(value = "/deletetest/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteTest(@PathVariable("id") long id)
			throws Exception {
		try {
			testManagementServiceInterface.deleteTest(id);
			return new Response(1, "success");
		} catch (DataIntegrityViolationException e) {
			return new Response(0, "failed");
			// TODO: handle exception
		}

	}

	/*
	 * Method to get test by test id
	 * 
	 * @return test details
	 */
	@RequestMapping(value = "/fetchonlinetest/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestBean> getTest(@PathVariable("id") long id)
			throws Exception {
		TestBean test = testManagementServiceInterface.editTest(id);
		if (test == null) {
			return new ResponseEntity<TestBean>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TestBean>(test, HttpStatus.OK);
	}

	/*
	 * Method to get all training programs based on user role and user id
	 * 
	 * @return list of available training programs
	 */
	@RequestMapping(value = "/fetchalltrainings/", method = RequestMethod.GET)
	public @ResponseBody List getAllTrainings(HttpSession session)
			throws Exception {
		return testManagementServiceInterface.getAllTrainings(
				(long) session.getAttribute("roleId"),
				(long) session.getAttribute("userId"));

	}

	/*
	 * Method to publish test
	 * 
	 * @return response with publish status
	 */
	@RequestMapping(value = "/publishtest/{id}", method = RequestMethod.GET)
	public @ResponseBody Response publishTest(@PathVariable("id") long id)
			throws Exception {
		testManagementServiceInterface.changeTestStatus(id,
				AppConstants.test_statuses.PUBLISHED);

		notificationServiceInterface.getUpcomingTestList(id);
		return new Response(1, "success");
	}

	/*
	 * Method to close test
	 * 
	 * @return response with close status
	 */
	@RequestMapping(value = "/closetest/{id}", method = RequestMethod.GET)
	public @ResponseBody Response closeTest(@PathVariable("id") long id)
			throws Exception {
		testManagementServiceInterface.changeTestStatus(id,
				AppConstants.test_statuses.CLOSED);
		return new Response(1, "success");
	}

}
