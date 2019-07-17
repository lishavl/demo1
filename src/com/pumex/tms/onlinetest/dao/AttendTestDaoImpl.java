package com.pumex.tms.onlinetest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pumex.tms.configurations.AbstractDao;
import com.pumex.tms.models.AttendeeAnswer;
import com.pumex.tms.models.AttendeeTest;
import com.pumex.tms.models.OnlineTest;
import com.pumex.tms.models.Question;
import com.pumex.tms.models.UserDetails;
import com.pumex.tms.onlinetest.bean.AnsweringBean;
import com.pumex.tms.onlinetest.bean.TestResultBean;
import com.pumex.tms.util.AppConstants;

/**
 * DAO class to manage attend test
 * 
 * @Author JINSHAD P.T.
 * @Date 17/06/2016
 */

@Transactional
@Repository
public class AttendTestDaoImpl extends AbstractDao<Long, OnlineTest> implements
		AttendTestDao {

	/*
	 * Method to get all not attended tests available to logged in user
	 * 
	 * @return list of available tests
	 */
	@Override
	public List getAllTests(long userId) throws Exception {
		// TODO Auto-generated method stub
		List<OnlineTest> lst = getSession()
				.createQuery(
						"select ot from OnlineTest ot, TopicAttendee ta where ot.training.id=ta.topic.id and ta.attendee.userId=:userId and ot.status=:sts")
				.setLong("userId", userId)
				.setInteger("sts", AppConstants.test_statuses.PUBLISHED).list();

		for (OnlineTest test : lst) {

			if ((Long) getSession()
					.createQuery(
							"select count(id) from AttendeeTest where test.id=:tid and attendee.userId=:uid and status=2")
					.setLong("tid", test.getId()).setLong("uid", userId)
					.uniqueResult() > 0) {
				test.setFinished(true);
			}

		}

		return lst;
	}

	/*
	 * Method to check test is attended by user
	 * 
	 * @return true/false
	 */
	@Override
	public boolean isTestAttended(long testId, long userId) throws Exception {

		if ((Long) getSession()
				.createQuery(
						"select count(id) from AttendeeTest where test.id=:tid and attendee.userId=:uid")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult() <= 0)
			return false;
		else
			return true;
	}

	/*
	 * Method to start a test
	 * 
	 * @return AnsweringBean (includes Question)
	 */
	@Override
	public AnsweringBean startTest(long testId, long userId) throws Exception {

		AnsweringBean answerBean = new AnsweringBean(false);
		answerBean.setLastQuestion(false);
		Question question = null;

		if ((Long) getSession()
				.createQuery(
						"select count(id) from AttendeeTest where test.id=:tid and attendee.userId=:uid")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult() <= 0) {

			List<Long> questionIds = getSession()
					.createQuery(
							"select id from Question where test.id=:tid order by id")
					.setLong("tid", testId).list();
			if (questionIds.size() > 0) {

				UserDetails user = new UserDetails(userId);

				OnlineTest test = (OnlineTest) getSession().get(
						OnlineTest.class, testId);

				AttendeeTest attTst = new AttendeeTest();
				attTst.setAttendee(user);
				attTst.setTest(test);
				attTst.setStatus(0);
				attTst.setRemainingSeconds(test.getTimeMinutes() * 60);

				getSession().persist(attTst);

				for (Long qid : questionIds) {
					getSession().persist(
							new AttendeeAnswer(new Question(qid), user, 0));
				}
			}
		}

		Object nextQn = getSession()
				.createQuery(
						"select min(id) from AttendeeAnswer where question.test.id=:tid and attendee.userId=:uid and status=0")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();

		if (nextQn == null) {
			nextQn = getSession()
					.createQuery(
							"select max(id) from AttendeeAnswer where question.test.id=:tid and attendee.userId=:uid")
					.setLong("tid", testId).setLong("uid", userId)
					.uniqueResult();

			answerBean.setLastQuestion(true);

			if (nextQn != null)
				answerBean.setAnswered(true);
		} else {

			Object lastQn = getSession()
					.createQuery(
							"select max(id) from AttendeeAnswer where question.test.id=:tid and attendee.userId=:uid and status=0")
					.setLong("tid", testId).setLong("uid", userId)
					.uniqueResult();

			if ((long) nextQn == (long) lastQn)
				answerBean.setLastQuestion(true);

		}

		if (nextQn != null) {
			question = (Question) getSession()
					.createQuery(
							"select a.question from AttendeeAnswer a where a.id=:id")
					.setLong("id", (Long) nextQn).uniqueResult();

			long count = (long) getSession()
					.createQuery(
							"select count(id) from AttendeeAnswer where id<=:id and question.test.id=:tid and attendee.userId=:uid ")
					.setLong("id", (Long) nextQn).setLong("tid", testId)
					.setLong("uid", userId).uniqueResult();

			answerBean.setQuestionNumber((int) count);

			int remainingSecs = (int) getSession()
					.createQuery(
							"select remainingSeconds from AttendeeTest where test.id=:tid and attendee.userId=:uid")
					.setLong("tid", testId).setLong("uid", userId)
					.uniqueResult();

			answerBean.setRemainingSeconds(remainingSecs);
		}
		answerBean.setQuestion(question);

		return answerBean;
	}

	/*
	 * Method to answer a question
	 * 
	 * @return next question
	 */
	@Override
	public AnsweringBean answerQuestionAndGetNext(long testId, long userId,
			long questionid, int answer, int remainingSeconds) throws Exception {

		if (answer > 0) {
			getSession()
					.createQuery(
							"update AttendeeAnswer set answer=:answer, status=1  where question.id=:qid and attendee.userId=:uid")
					.setInteger("answer", answer).setLong("qid", questionid)
					.setLong("uid", userId).executeUpdate();
		} else {
			getSession()
					.createQuery(
							" update AttendeeAnswer set status=-1 where question.id=:qid and attendee.userId=:uid")
					.setLong("qid", questionid).setLong("uid", userId)
					.executeUpdate();
		}

		getSession()
				.createQuery(
						"update AttendeeTest set status=1,remainingSeconds=:rs where test.id=:tid and attendee.userId=:uid")
				.setLong("tid", testId).setInteger("rs", remainingSeconds)
				.setLong("uid", userId).executeUpdate();

		AnsweringBean answerBean = new AnsweringBean(false);
		answerBean.setLastQuestion(false);
		Question question = null;

		Object nextQn = getSession()
				.createQuery(
						"select min(id) from AttendeeAnswer where question.test.id=:tid and attendee.userId=:uid and status=0")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();

		if (nextQn != null) {

			Object lastQn = getSession()
					.createQuery(
							"select max(id) from AttendeeAnswer where question.test.id=:tid and attendee.userId=:uid and status=0")
					.setLong("tid", testId).setLong("uid", userId)
					.uniqueResult();

			if ((long) nextQn == (long) lastQn)
				answerBean.setLastQuestion(true);

		}

		if (nextQn != null) {
			question = (Question) getSession()
					.createQuery(
							"select a.question from AttendeeAnswer a where a.id=:id")
					.setLong("id", (Long) nextQn).uniqueResult();

			long count = (long) getSession()
					.createQuery(
							"select count(id) from AttendeeAnswer where id<=:id and question.test.id=:tid and attendee.userId=:uid ")
					.setLong("id", (Long) nextQn).setLong("tid", testId)
					.setLong("uid", userId).uniqueResult();

			answerBean.setQuestionNumber((int) count);

		}
		answerBean.setQuestion(question);

		answerBean.setRemainingSeconds(remainingSeconds);

		return answerBean;
	}

	/*
	 * Method to finish the test
	 * 
	 * @return Result
	 */
	@Override
	public AnsweringBean finishTest(long testId, long userId, long questionid,
			int answer, int remainingSeconds) throws Exception {

		if (answer > 0) {
			getSession()
					.createQuery(
							"update AttendeeAnswer set answer=:answer, status=1  where question.id=:qid and attendee.userId=:uid")
					.setInteger("answer", answer).setLong("qid", questionid)
					.setLong("uid", userId).executeUpdate();
		} else {
			getSession()
					.createQuery(
							" update AttendeeAnswer set status=-1 where question.id=:qid and attendee.userId=:uid")
					.setLong("qid", questionid).setLong("uid", userId)
					.executeUpdate();
		}

		getSession()
				.createQuery(
						"update AttendeeTest set status=2,remainingSeconds=:rs where test.id=:tid and attendee.userId=:uid")
				.setLong("tid", testId).setLong("uid", userId)
				.setInteger("rs", remainingSeconds).executeUpdate();

		AnsweringBean answerBean = new AnsweringBean();
		answerBean.setFinished(true);

		// For Result

		long rightAnswers = (long) getSession()
				.createQuery(
						"select count(a.id) from AttendeeAnswer a  where question.test.id=:tid and attendee.userId=:uid and a.answer=a.question.answer")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();
		long totalAnswered = (long) getSession()
				.createQuery(
						"select count(a.id) from AttendeeAnswer a  where question.test.id=:tid and attendee.userId=:uid and status=1")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();
		long totalQuestions = (long) getSession()
				.createQuery(
						"select count(a.id) from AttendeeAnswer a  where question.test.id=:tid and attendee.userId=:uid")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();

		answerBean.setTotalQuestions((int) totalQuestions);
		answerBean.setTotalAnswered((int) totalAnswered);
		answerBean.setCorrectAnswers((int) rightAnswers);

		OnlineTest test = (OnlineTest) getSession().load(OnlineTest.class,
				testId);
		answerBean.setTestName(test.getTitle());
		answerBean.setCorrectAnswerMark(test.getCorrectAnswerMark());
		answerBean.setWrongAnswerMark(test.getWrongAnswerMark());
		answerBean.setNotAttendedMark(test.getNotAttendedMark());

		answerBean.setPassMark(test.getPassMark());
		answerBean
				.setMark(rightAnswers
						* test.getCorrectAnswerMark()
						- ((totalAnswered - rightAnswers) * test
								.getWrongAnswerMark())
						- ((totalQuestions - totalAnswered) * test
								.getNotAttendedMark()));

		answerBean.setPercentage(answerBean.getMark() * 100
				/ (totalQuestions * test.getCorrectAnswerMark()));

		answerBean.setMaximumMark(totalQuestions * test.getCorrectAnswerMark());

		getSession()
				.createQuery(
						"update AttendeeTest set mark=:mrk,totalAnswered=:ta where test.id=:tid and attendee.userId=:uid")
				.setFloat("mrk", answerBean.getMark()).setLong("tid", testId)
				.setLong("uid", userId).setInteger("ta", (int) totalAnswered)
				.executeUpdate();

		List<TestResultBean> answerList = getSession()
				.createQuery(
						"select new com.pumex.tms.onlinetest.bean.TestResultBean("
								+ "b.question,a.answer,b.answer,b.choice1,b.choice2,b.choice3,b.choice4)"
								+ " from AttendeeAnswer a ,Question b where b.test.id = :testid and a.attendee.userId = :userid and a.question.id = b.id")
				.setLong("testid", testId).setLong("userid", userId).list();
		answerBean.setAnswerList(answerList);

		return answerBean;
	}

	/*
	 * Method to show result of test
	 * 
	 * @return Result
	 */
	@Override
	public AnsweringBean showResult(long testId, long userId) throws Exception {

		AnsweringBean answerBean = new AnsweringBean();
		answerBean.setFinished(true);

		// For Result

		long rightAnswers = (long) getSession()
				.createQuery(
						"select count(a.id) from AttendeeAnswer a  where question.test.id=:tid and attendee.userId=:uid and a.answer=a.question.answer")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();
		long totalAnswered = (long) getSession()
				.createQuery(
						"select count(a.id) from AttendeeAnswer a  where question.test.id=:tid and attendee.userId=:uid and status=1")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();
		long totalQuestions = (long) getSession()
				.createQuery(
						"select count(a.id) from AttendeeAnswer a  where question.test.id=:tid and attendee.userId=:uid")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();

		answerBean.setTotalQuestions((int) totalQuestions);
		answerBean.setTotalAnswered((int) totalAnswered);
		answerBean.setCorrectAnswers((int) rightAnswers);

		OnlineTest test = (OnlineTest) getSession().load(OnlineTest.class,
				testId);
		answerBean.setTestName(test.getTitle());
		answerBean.setCorrectAnswerMark(test.getCorrectAnswerMark());
		answerBean.setWrongAnswerMark(test.getWrongAnswerMark());
		answerBean.setNotAttendedMark(test.getNotAttendedMark());

		answerBean.setPassMark(test.getPassMark());
		answerBean
				.setMark(rightAnswers
						* test.getCorrectAnswerMark()
						- ((totalAnswered - rightAnswers) * test
								.getWrongAnswerMark())
						- ((totalQuestions - totalAnswered) * test
								.getNotAttendedMark()));

		answerBean.setPercentage(answerBean.getMark() * 100
				/ (totalQuestions * test.getCorrectAnswerMark()));

		answerBean.setMaximumMark(totalQuestions * test.getCorrectAnswerMark());

		List<TestResultBean> answerList = getSession()
				.createQuery(
						"select new com.pumex.tms.onlinetest.bean.TestResultBean("
								+ "b.question,a.answer,b.answer,b.choice1,b.choice2,b.choice3,b.choice4)"
								+ " from AttendeeAnswer a ,Question b where b.test.id = :testid and a.attendee.userId = :userid and a.question.id = b.id")
				.setLong("testid", testId).setLong("userid", userId).list();
		answerBean.setAnswerList(answerList);
		
		UserDetails user = (UserDetails) getSession().load(UserDetails.class,
				userId);
		answerBean.setUserName(user.getFirstName()+" "+user.getLastName());
		
		return answerBean;
	}

	/*
	 * Method to automatically close the test when time out
	 * 
	 * @return Result
	 */
	@Override
	public AnsweringBean closeTest(long testId, long userId, long questionid,
			int answer, int remainingSeconds) throws Exception {

		if (answer > 0) {
			getSession()
					.createQuery(
							"update AttendeeAnswer set answer=:answer, status=1  where question.id=:qid and attendee.userId=:uid")
					.setInteger("answer", answer).setLong("qid", questionid)
					.setLong("uid", userId).executeUpdate();
		}

		List ids = getSession()
				.createQuery(
						"select a.id from AttendeeAnswer a join a.question b where b.test.id=:tid and a.attendee.userId=:uid and a.status=0")
				.setLong("tid", testId).setLong("uid", userId).list();

		if (ids.size() > 0) {
			getSession()
					.createQuery(
							"update AttendeeAnswer ans set ans.status=-1 where ans.id in (:ids)")
					.setParameterList("ids", ids).executeUpdate();
		}

		getSession()
				.createQuery(
						"update AttendeeTest set status=2,remainingSeconds=:rs where test.id=:tid and attendee.userId=:uid")
				.setLong("tid", testId).setLong("uid", userId)
				.setInteger("rs", remainingSeconds).executeUpdate();

		AnsweringBean answerBean = new AnsweringBean();
		answerBean.setFinished(true);

		// For Result

		long rightAnswers = (long) getSession()
				.createQuery(
						"select count(a.id) from AttendeeAnswer a  where question.test.id=:tid and attendee.userId=:uid and a.answer=a.question.answer")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();
		long totalAnswered = (long) getSession()
				.createQuery(
						"select count(a.id) from AttendeeAnswer a  where question.test.id=:tid and attendee.userId=:uid and status=1")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();
		long totalQuestions = (long) getSession()
				.createQuery(
						"select count(a.id) from AttendeeAnswer a  where question.test.id=:tid and attendee.userId=:uid")
				.setLong("tid", testId).setLong("uid", userId).uniqueResult();

		answerBean.setTotalQuestions((int) totalQuestions);
		answerBean.setTotalAnswered((int) totalAnswered);
		answerBean.setCorrectAnswers((int) rightAnswers);

		OnlineTest test = (OnlineTest) getSession().load(OnlineTest.class,
				testId);

		answerBean.setTestName(test.getTitle());
		answerBean.setCorrectAnswerMark(test.getCorrectAnswerMark());
		answerBean.setWrongAnswerMark(test.getWrongAnswerMark());
		answerBean.setNotAttendedMark(test.getNotAttendedMark());
		answerBean.setPassMark(test.getPassMark());

		answerBean
				.setMark(rightAnswers
						* test.getCorrectAnswerMark()
						- ((totalAnswered - rightAnswers) * test
								.getWrongAnswerMark())
						- ((totalQuestions - totalAnswered) * test
								.getNotAttendedMark()));

		answerBean.setPercentage(answerBean.getMark() * 100
				/ (totalQuestions * test.getCorrectAnswerMark()));

		answerBean.setMaximumMark(totalQuestions * test.getCorrectAnswerMark());

		getSession()
				.createQuery(
						"update AttendeeTest set mark=:mrk,totalAnswered=:ta where test.id=:tid and attendee.userId=:uid")
				.setFloat("mrk", answerBean.getMark()).setLong("tid", testId)
				.setLong("uid", userId).setInteger("ta", (int) totalAnswered)
				.executeUpdate();

		List<TestResultBean> answerList = getSession()
				.createQuery(
						"select new com.pumex.tms.onlinetest.bean.TestResultBean("
								+ "b.question,a.answer,b.answer,b.choice1,b.choice2,b.choice3,b.choice4)"
								+ " from AttendeeAnswer a ,Question b where b.test.id = :testid and a.attendee.userId = :userid and a.question.id = b.id")
				.setLong("testid", testId).setLong("userid", userId).list();
		answerBean.setAnswerList(answerList);

		return answerBean;

	}

}
