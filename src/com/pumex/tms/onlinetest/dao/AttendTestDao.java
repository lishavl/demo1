package com.pumex.tms.onlinetest.dao;

import java.util.List;

import com.pumex.tms.onlinetest.bean.AnsweringBean;

/**
 * @Author JINSHAD P.T.
 * @Date 17/06/2016
 */

public interface AttendTestDao {

	AnsweringBean startTest(long id, long userId) throws Exception;

	boolean isTestAttended(long id, long userId) throws Exception;

	List getAllTests(long userId) throws Exception;

	AnsweringBean answerQuestionAndGetNext(long testId, long userId,
			long questionid, int answer, int remainingSeconds) throws Exception;

	AnsweringBean finishTest(long testId, long userId, long questionid,
			int answer, int remainingSeconds) throws Exception;

	AnsweringBean showResult(long testId, long userId) throws Exception;
	

	AnsweringBean closeTest(long testId, long userId, long questionid,
			int answer, int remainingSeconds) throws Exception;

}
