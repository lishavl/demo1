/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.onlinetest.dao;

import java.util.List;

import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;

public interface TestResultDao {

	List<TestResultBean> getAllResult(long testId) throws Exception;

	List<TestBean> getAllTestTopicsTrainer(long id) throws Exception;

	List<TestBean> getAllTestTopicsAdmin() throws Exception;

	List<TestResultBean> fetchQuestionAndAnswerForAttendee(long testid,
			long userid) throws Exception;

}
