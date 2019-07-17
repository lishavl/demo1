package com.pumex.tms.onlinetest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.onlinetest.dao.TestResultDao;


@Service("testresult")
public class TestResultServiceImp implements TestResultService{
	
	@Autowired
	TestResultDao dao;

	@Override
	public List<TestResultBean> fetchQuestionAndAnswerForAttendee(long testid,
			long userid) throws Exception {
		// TODO Auto-generated method stub
		return dao.fetchQuestionAndAnswerForAttendee(testid,userid);
	}

}
