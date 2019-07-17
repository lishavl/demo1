package com.pumex.tms.onlinetest.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pumex.tms.models.Question;
import com.pumex.tms.onlinetest.bean.QuestionBean;
import com.pumex.tms.onlinetest.service.QuestionManagementService;
import com.pumex.tms.onlinetest.service.TestManagementService;
import com.pumex.tms.util.AppConstants;
import com.pumex.tms.util.Response;

/**
 * Class to manage questions
 * 
 * @Author JINSHAD P.T.
 * @Date 17/06/2016
 */
@Controller
public class QuestionManagementController {

	@Autowired
	QuestionManagementService questionManagementServiceInterface;

	@Autowired
	TestManagementService testManagementServiceInterface;

	/*
	 * Method to get all questions under test id
	 * 
	 * @return response with test status and list of questions
	 */
	@RequestMapping(value = "/getallquestions/{testId}", method = RequestMethod.GET)
	public @ResponseBody HashMap listAllQuestions(
			@PathVariable("testId") long testId) throws Exception {

		HashMap resp=new HashMap<String, Object>();
		List<Question> questions = questionManagementServiceInterface
				.getAllQuestions(testId);
		resp.put("questions",questions);
		String submitStatus="";
		
		OnlineTest test=testManagementServiceInterface.getTestById(testId);
		
		if (questionManagementServiceInterface.isTestSubmitable(testId)) {
			submitStatus="submitable";
		} else {
			int status = test.getStatus();
			if (status == AppConstants.test_statuses.CREATED)
				submitStatus="created";
			else if (status == AppConstants.test_statuses.SUBMITTED)
				submitStatus="submitted";
			else if (status == AppConstants.test_statuses.PUBLISHED)
				submitStatus="published";
			else
				submitStatus="not_submitable";
		}
		resp.put("submitStatus",submitStatus);
		resp.put("totalQuestions",test.getNumberOfQuestions());

		return resp;
	}

	/*
	 * Method to save/update question
	 * 
	 * @return response with save/update status
	 */
	@RequestMapping(value = "/savequestion/", method = RequestMethod.PUT)
	public @ResponseBody Response saveOrUpdateQuestion(
			@Valid @RequestBody QuestionBean question) throws Exception {

		if (questionManagementServiceInterface.isQuestionAllowed(
				question.getId(), question.getTestId())) {
			questionManagementServiceInterface.saveOrUpdateQuestion(question);
			return new Response(1, "success");
		} else {
			return new Response(0, "Already finished");
		}

	}

	/*
	 * Method to check all questions are created or not
	 * 
	 * @return true/false
	 */
	@RequestMapping(value = "/checkquestionsover/{testId}", method = RequestMethod.GET)
	public @ResponseBody boolean checkQuestionsOver(
			@PathVariable("testId") long testId) throws Exception {

		if (questionManagementServiceInterface.isQuestionAllowed(0, testId)) {
			return false;
		}

		return true;

	}

	/*
	 * Method to delete a question
	 * 
	 * @return response with delete status
	 */
	@RequestMapping(value = "/deletequestion/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Response deleteQuestion(@PathVariable("id") long id)
			throws Exception {
		questionManagementServiceInterface.deleteQuestion(id);
		return new Response(1, "success");
	}

	/*
	 * Method to get question details from question id
	 * 
	 * @return QuestionBean
	 */
	@RequestMapping(value = "/fetchquestion/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<QuestionBean> getQuestion(@PathVariable("id") long id)
			throws Exception {
		QuestionBean question = questionManagementServiceInterface
				.editQuestion(id);
		if (question == null) {
			return new ResponseEntity<QuestionBean>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<QuestionBean>(question, HttpStatus.OK);

	}

	/*
	 * Method to get available training program to logged in user
	 * 
	 * @return list of training programs
	 */
	@RequestMapping(value = "/fetchalltestnames/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List getAllTrainings(HttpSession session)
			throws Exception {
		if (session.getAttribute("userId") != null) {
			long userId = (long) session.getAttribute("userId");
			long roleId = (long) session.getAttribute("roleId");
			if (roleId == 1)
				return testManagementServiceInterface.getAllTestNames(true,
						userId);
			else
				return testManagementServiceInterface.getAllTestNames(false,
						userId);
		}
		return null;

	}

	/*
	 * Method to submit a test 
	 * 
	 * @return response with submit status
	 */
	@RequestMapping(value = "/submittest/{testId}", method = RequestMethod.GET)
	public @ResponseBody Response submitTest(@PathVariable("testId") long testId)
			throws Exception {
		questionManagementServiceInterface.submitTest(testId);
		return new Response(1, "success");
	}
}
