package com.pumex.tms.onlinetest.service;

import java.util.List;

import com.pumex.tms.onlinetest.bean.TestResultBean;

public interface TestResultService {

	List<TestResultBean> fetchQuestionAndAnswerForAttendee(long testid,
			long userid) throws Exception;

}
