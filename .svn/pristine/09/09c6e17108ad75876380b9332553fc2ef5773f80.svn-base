package com.pumex.tms.onlinetest.service;

import java.util.List;

import com.pumex.tms.models.Question;
import com.pumex.tms.onlinetest.bean.QuestionBean;

public interface QuestionManagementService {

	public List<Question> getAllQuestions(long testId) throws Exception;

	public Question getQuestionById(long id) throws Exception;

	QuestionBean editQuestion(long id) throws Exception;

	public void saveOrUpdateQuestion(QuestionBean test) throws Exception;

	public void deleteQuestion(long id) throws Exception;

	public boolean isQuestionAllowed(long questionId, long testId)
			throws Exception;

	public boolean isTestSubmitable(long testId) throws Exception;

	public void submitTest(long testId) throws Exception;

	public int getTestStatus(long testId) throws Exception;
}
