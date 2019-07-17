package com.pumex.tms.onlinetest.controller;

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

/**
 * Class to manage attend test
 * 
 * @Author JINSHAD P.T.
 * @Date 17/06/2016
 */

@Controller
public class AttendTestController {

	@Autowired
	AttendTestDao testDao;

	@Autowired
	NotificationServiceInterface notificationServiceInterface;

	/*
	 * Method to get available tests to attendee
	 * 
	 * @return List of available tests
	 */
	@RequestMapping(value = { "/getallmytests" }, method = RequestMethod.GET)
	public @ResponseBody List getAllTests(HttpSession session) {
		List tests = null;
		try {
			tests = testDao.getAllTests((long) session.getAttribute("userId"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tests;
	}

	/*
	 * Method to know is the test is attended or not
	 * 
	 * @return true/false
	 */
	@RequestMapping(value = { "/istestattended/{testid}" }, method = RequestMethod.GET)
	public @ResponseBody boolean isTestAttended(
			@PathVariable("testid") long testid, HttpSession session) {
		try {
			boolean isAttended = testDao.isTestAttended(testid,
					(long) session.getAttribute("userId"));
			return isAttended;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Method to start a test
	 * 
	 * @return AnsweringBean (includes Question)
	 */
	@RequestMapping(value = { "/starttest/{testid}" }, method = RequestMethod.GET)
	public ResponseEntity<AnsweringBean> startTests(
			@PathVariable("testid") long testid, HttpSession session) {
		try {

			AnsweringBean bean = testDao.startTest(testid,
					(long) session.getAttribute("userId"));

			notificationServiceInterface.deleteNotification(testid,
					(long) session.getAttribute("userId"));

			return new ResponseEntity<AnsweringBean>(bean, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AnsweringBean>(HttpStatus.CONFLICT);
		}

	}

	/*
	 * Method to answer a question 
	 * 
	 * @return next question
	 */
	@RequestMapping(value = { "/answerquestionandgetnext/{testid}/{questionid}/{answer}/{remainingseconds}" }, method = RequestMethod.GET)
	public ResponseEntity<AnsweringBean> answerQuestionAndGetNext(
			@PathVariable("testid") long testid,
			@PathVariable("questionid") long questionid,
			@PathVariable("answer") int answer,
			@PathVariable("remainingseconds") int remainingSeconds,
			HttpSession session) {
		try {

			AnsweringBean bean = testDao.answerQuestionAndGetNext(testid,
					(long) session.getAttribute("userId"), questionid, answer,
					remainingSeconds);

			return new ResponseEntity<AnsweringBean>(bean, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AnsweringBean>(HttpStatus.CONFLICT);
		}

	}

	/*
	 * Method to finish the test 
	 * 
	 * @return Result
	 */
	@RequestMapping(value = { "/finishtest/{testid}/{questionid}/{answer}/{remainingseconds}" }, method = RequestMethod.GET)
	public ResponseEntity<AnsweringBean> finishTest(
			@PathVariable("testid") long testid,
			@PathVariable("questionid") long questionid,
			@PathVariable("answer") int answer,
			@PathVariable("remainingseconds") int remainingSeconds,
			HttpSession session) {
		try {

			AnsweringBean bean = testDao.finishTest(testid,
					(long) session.getAttribute("userId"), questionid, answer,
					remainingSeconds);

			return new ResponseEntity<AnsweringBean>(bean, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AnsweringBean>(HttpStatus.CONFLICT);
		}
	}

	/*
	 * Method to show result of test 
	 * 
	 * @return Result
	 */
	@RequestMapping(value = { "/showresult/{testid}" }, method = RequestMethod.GET)
	public ResponseEntity<AnsweringBean> showResult(
			@PathVariable("testid") long testid, HttpSession session) {
		try {

			AnsweringBean bean = testDao.showResult(testid,
					(long) session.getAttribute("userId"));

			return new ResponseEntity<AnsweringBean>(bean, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AnsweringBean>(HttpStatus.CONFLICT);
		}

	}

	/*
	 * Method to automatically close the test when time out 
	 * 
	 * @return Result
	 */
	@RequestMapping(value = { "/closetest/{testid}/{questionid}/{answer}/{remainingseconds}" }, method = RequestMethod.GET)
	public ResponseEntity<AnsweringBean> closeTest(
			@PathVariable("testid") long testid,
			@PathVariable("questionid") long questionid,
			@PathVariable("answer") int answer,
			@PathVariable("remainingseconds") int remainingSeconds,
			HttpSession session) {
		try {

			AnsweringBean bean = testDao.closeTest(testid,
					(long) session.getAttribute("userId"), questionid, answer,
					remainingSeconds);

			return new ResponseEntity<AnsweringBean>(bean, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AnsweringBean>(HttpStatus.CONFLICT);
		}
	}

}