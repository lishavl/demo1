package com.pumex.tms.onlinetest.dao;

import java.util.List;

import com.pumex.tms.models.Question;

/**
 * @Author JINSHAD P.T.
 * @Date 12/01/2016
 */

public interface QuestionManagementDao {

	public List<Question> getAllQuestions(long testId) throws Exception;

	public Question getQuestion(long id) throws Exception;

	public void saveOrUpdateQuestion(Question user) throws Exception;

	public void deleteQuestion(long id) throws Exception;

	public boolean isQuestionAllowed(long questionId, long testId)
			throws Exception;

	public boolean isTestSubmitable(long testId) throws Exception;

	public void submitTest(long testId) throws Exception;
	
	public int getTestStatus(long testId) throws Exception;

}
