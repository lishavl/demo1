package com.pumex.tms.onlinetest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.models.OnlineTest;
import com.pumex.tms.models.Question;
import com.pumex.tms.onlinetest.bean.QuestionBean;
import com.pumex.tms.onlinetest.dao.QuestionManagementDao;

@Service("questionservice")
public class QuestionManagementServiceImpl implements QuestionManagementService {

	@Autowired
	QuestionManagementDao questionManagementDao;

	/*
	 * Method to get all questions under test id
	 * 
	 * @return list of questions
	 */
	public List<Question> getAllQuestions(long questionId) throws Exception {
		// TODO Auto-generated method stub
		return questionManagementDao.getAllQuestions(questionId);
	}

	/*
	 * Method to get question details from question id
	 * 
	 * @return QuestionBean
	 */
	public Question getQuestionById(long id) throws Exception {
		Question user = questionManagementDao.getQuestion(id);
		return user;
	}

	/*
	 * Method to get question details for edit from question id
	 * 
	 * @return QuestionBean
	 */
	public QuestionBean editQuestion(long id) throws Exception {
		Question question = questionManagementDao.getQuestion(id);
		QuestionBean bean = new QuestionBean();
		bean.setId(question.getId());
		bean.setQuestion(question.getQuestion());
		bean.setChoice1(question.getChoice1());
		bean.setChoice2(question.getChoice2());
		bean.setChoice3(question.getChoice3());
		bean.setChoice4(question.getChoice4());
		bean.setAnswer(question.getAnswer());
		bean.setNoOfChoices(question.getNoOfChoices());
		bean.setTestId(question.getTest().getId());

		return bean;
	}

	/*
	 * Method to save/update question
	 * 
	 * @return response with save/update status
	 */
	public void saveOrUpdateQuestion(QuestionBean question) throws Exception {

		Question questionMdl;
		if (question.getId() != 0) {
			questionMdl = questionManagementDao.getQuestion(question.getId());
		} else {
			questionMdl = new Question();
			questionMdl.setStatus(1);
		}

		questionMdl.setQuestion(question.getQuestion());
		questionMdl.setChoice1(question.getChoice1());
		questionMdl.setChoice2(question.getChoice2());
		questionMdl.setChoice3(question.getChoice3());
		questionMdl.setChoice4(question.getChoice4());
		questionMdl.setAnswer(question.getAnswer());
		questionMdl.setNoOfChoices(question.getNoOfChoices());
		questionMdl.setTest(new OnlineTest(question.getTestId()));

		questionManagementDao.saveOrUpdateQuestion(questionMdl);
		// TODO Auto-generated method stub

	}

	/*
	 * Method to delete question
	 * 
	 * @return nothing
	 */
	public void deleteQuestion(long id) throws Exception {
		// TODO Auto-generated method stub
		questionManagementDao.deleteQuestion(id);
	}

	/*
	 * Method to check questions are completed or more questions are allowed
	 * 
	 * @return true/false
	 */
	public boolean isQuestionAllowed(long questionId, long testId)
			throws Exception {
		// TODO Auto-generated method stub
		return questionManagementDao.isQuestionAllowed(questionId, testId);
	}

	/*
	 * Method to check test is ready for submit
	 * 
	 * @return true/false
	 */
	public boolean isTestSubmitable(long testId) throws Exception {
		// TODO Auto-generated method stub
		return questionManagementDao.isTestSubmitable(testId);
	}

	/*
	 * Method to get test status
	 * 
	 * @return status
	 */
	public int getTestStatus(long testId) throws Exception {
		// TODO Auto-generated method stub
		return questionManagementDao.getTestStatus(testId);
	}

	/*
	 * Method to submit a test
	 * 
	 * @return nothing
	 */
	public void submitTest(long testId) throws Exception {
		// TODO Auto-generated method stub
		questionManagementDao.submitTest(testId);
	}

}
