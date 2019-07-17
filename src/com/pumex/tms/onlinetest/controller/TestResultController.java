/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.onlinetest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumex.tms.models.OnlineTest;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.onlinetest.bean.TestReportBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.onlinetest.dao.TestResultDao;
import com.pumex.tms.onlinetest.service.TestManagementService;
import com.pumex.tms.onlinetest.service.TestResultService;

@Controller
public class TestResultController {
	@Autowired
	TestManagementService testManagementServiceInterface;

	@Autowired
	TestResultDao testDao;
    
	@Autowired
	TestResultService service;
	/*
	 * Method for getAll test names
	 * 
	 * @return list of testnames
	 */
	@RequestMapping(value = "/getallTestNames/", method = RequestMethod.GET)
	public @ResponseBody List listAllUsers(HttpSession session)
			throws Exception {

		long id = (long) session.getAttribute("userId");
		long roleid = (long) session.getAttribute("roleId");
		OnlineTest ot = new OnlineTest();
		List<TestBean> test1;
		List<TestBean> beantest;

		if (roleid == 2) {

			return beantest = testDao.getAllTestTopicsTrainer(id);

		} else {

			return test1 = testDao.getAllTestTopicsAdmin();
		}

	}

	
	/*
	 * Method for fetch test result
	 * 
	 * @return  test result as  TestReportBean
	 */
	@RequestMapping(value = { "/fetchalltestresult/{id}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TestReportBean> getAllResult(
			@PathVariable("id") long testid, HttpSession session) {
		try {

			List<TestResultBean> result = testDao.getAllResult(testid);

			TestReportBean reportBean = new TestReportBean();

			long countpass = 0;
			long countfail = 0;
			for (TestResultBean test : result) {
				if(test.getMark()!=0){
					if (test.getMark() >= test.getPassMark()) {
						test.setStatus("PASS");
						countpass++;

					} else {
						test.setStatus("FAILED");
						countfail++;
					}
				}else{
					test.setStatus("ABSENT");
				}
			

			}

			reportBean.setTestresult(result);
			reportBean.setNopass(countpass);
			reportBean.setNofail(countfail);

			return new ResponseEntity<TestReportBean>(reportBean, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<TestReportBean>(HttpStatus.CONFLICT);
		}

	}
	@RequestMapping(value = { "/fetchanswersheet/{testid}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List fetchAnswerSheetForAttendee(
			@PathVariable("testid") long testid, HttpSession session) throws Exception {
		long userid = (long) session.getAttribute("userId");
		
		List<TestResultBean> ansersheet=service.fetchQuestionAndAnswerForAttendee(testid,userid);
		
		if(ansersheet!=null){
			return ansersheet ;
		}
		return ansersheet;
	
	}
	
	@RequestMapping(value = { "/fetchanswersheetbyattendeeid/{attendeeid}/{testid}" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List fetchAnswerSheetForAttendeeByAttendeeId(
			@PathVariable("testid") long testid, @PathVariable("attendeeid") long attendeeid) throws Exception {
		
		List<TestResultBean> ansersheet=service.fetchQuestionAndAnswerForAttendee(testid,attendeeid);
		
		if(ansersheet!=null){
			return ansersheet ;
		}
		return ansersheet;
	
	}

}
