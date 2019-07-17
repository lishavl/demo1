/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.onlinetest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.AttendeeTest;
import com.pumex.tms.onlinetest.bean.TestBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;

@Transactional
@Repository
public class TestResultDaoImp extends AbstractDao<Long, AttendeeTest> implements
		TestResultDao {

	
	/*
	 * Method for fetch test result
	 * 
	 * @return  test result as  TestReportBean
	 */
	@Override
	public List<TestResultBean> getAllResult(long testId) throws Exception {
		
		
		List<TestResultBean> test=getSession().createQuery("select  new com.pumex.tms.onlinetest.bean.TestResultBean(a.attendee.userId,concat(a.attendee.firstName ,' ',a.attendee.lastName) as attendee,b.id,b.title)"
				+ " from TopicAttendee a ,OnlineTest b where b.id=:id and a.topic.id =b.training.id ")
				.setLong("id", testId).list();
		
		
		
		List<TestResultBean> testresult= getSession()
				.createQuery(
						"select new com.pumex.tms.onlinetest.bean.TestResultBean("
								+ " a.attendee.userId,a.test.id,a.test.title,concat(a.attendee.firstName ,' ',a.attendee.lastName) as attendee, a.mark,a.test.numberOfQuestions, a.test.passMark,a.totalAnswered,a.test.correctAnswerMark)"
								+ " from AttendeeTest a where a.test.id = :id")
				.setLong("id", testId).list();
		
		for(TestResultBean te:test){
			for(TestResultBean re:testresult){
				if(te.getAttendeeid()==re.getAttendeeid()){
					te.setMark(re.getMark());
					te.setNumberOfQuestions(re.getNumberOfQuestions());
					te.setPassMark(re.getPassMark());
					te.setTotalAnswered(re.getTotalAnswered());
					te.setCorrectAnswerMark(re.getCorrectAnswerMark());			
				}
				else{
					te.setMark(0);
					te.setNumberOfQuestions(0);
					te.setPassMark(0);
					te.setTotalAnswered(0);
					te.setCorrectAnswerMark(0);					
				}
			}
		}

		return test;
		// TODO Auto-generated method stub
	}
  
	/*
	 * Method for getAll test names
	 * 
	 * @return list of testnames
	 */
	@Override
	public List<TestBean> getAllTestTopicsTrainer(long id) throws Exception {
		List<TestBean> test = getSession()
				.createQuery(
						"select new com.pumex.tms.onlinetest.bean.TestBean("
								+ "a.id, a.title)"
								+ " from OnlineTest  a, TopicTrainer b where a.training.id = b.topic.id  and b.trainer.userId =:userId ")
				.setLong("userId", id).list();
		return test;
	}
	
	/*
	 * Method for getAll test names
	 * 
	 * @return list of testnames
	 */
	@Override
	public List<TestBean> getAllTestTopicsAdmin() throws Exception {
		List<TestBean> test = getSession().createQuery(
				"select new com.pumex.tms.onlinetest.bean.TestBean("
						+ "a.id, a.title)" + " from OnlineTest  a").list();
		return test;
	}

	@Override
	public List<TestResultBean> fetchQuestionAndAnswerForAttendee(long testid,
			long userid) throws Exception {
		// TODO Auto-generated method stub
		List<TestResultBean> resultset= getSession().createQuery("select new com.pumex.tms.onlinetest.bean.TestResultBean("
								+ "b.question,a.answer,b.answer,b.choice1,b.choice2,b.choice3,b.choice4)"
								+ " from AttendeeAnswer a ,Question b where b.test.id = :testid and a.attendee.userId = :userid and a.question.id = b.id").setLong("testid", testid).setLong("userid", userid).list();
		return resultset;
	}

}
