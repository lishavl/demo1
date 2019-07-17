package com.pumex.tms.onlinetest.bean;

import java.util.List;

import javax.persistence.Column;

import com.pumex.tms.models.Question;

/**
 * Bean class for answering
 * 
 * @Author JINSHAD P.T.
 * @Date 08/05/2016
 */
public class AnsweringBean {

	private Question question;
	private boolean isAnswered;
	private boolean isAttended;
	private boolean isLastQuestion;
	private boolean finished;

	private String userName;
	private String testName;
	private float correctAnswerMark;
	private float wrongAnswerMark;
	private float notAttendedMark;

	private long testId;
	private long questionId;
	private int answer;

	private int questionNumber;

	private int remainingSeconds;

	private int totalQuestions;
	private int totalAnswered;
	private int correctAnswers;
	private float passMark;
	private float percentage;
	private float mark;
	private float maximumMark;
	private List answerList;

	public AnsweringBean() {
		super();
	}

	public AnsweringBean(boolean isAnswered) {
		super();
		this.isAnswered = isAnswered;
	}

	public AnsweringBean(Question question, boolean isAnswered) {
		super();
		this.question = question;
		this.isAnswered = isAnswered;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public boolean isLastQuestion() {
		return isLastQuestion;
	}

	public void setLastQuestion(boolean isLastQuestion) {
		this.isLastQuestion = isLastQuestion;
	}

	public boolean isAttended() {
		return isAttended;
	}

	public void setAttended(boolean isAttended) {
		this.isAttended = isAttended;
	}

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public int getTotalAnswered() {
		return totalAnswered;
	}

	public void setTotalAnswered(int totalAnswered) {
		this.totalAnswered = totalAnswered;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public int getRemainingSeconds() {
		return remainingSeconds;
	}

	public void setRemainingSeconds(int remainingSeconds) {
		this.remainingSeconds = remainingSeconds;
	}

	public float getPassMark() {
		return passMark;
	}

	public void setPassMark(float passMark) {
		this.passMark = passMark;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public float getMaximumMark() {
		return maximumMark;
	}

	public void setMaximumMark(float maximumMark) {
		this.maximumMark = maximumMark;
	}

	public List getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public float getCorrectAnswerMark() {
		return correctAnswerMark;
	}

	public void setCorrectAnswerMark(float correctAnswerMark) {
		this.correctAnswerMark = correctAnswerMark;
	}

	public float getWrongAnswerMark() {
		return wrongAnswerMark;
	}

	public void setWrongAnswerMark(float wrongAnswerMark) {
		this.wrongAnswerMark = wrongAnswerMark;
	}

	public float getNotAttendedMark() {
		return notAttendedMark;
	}

	public void setNotAttendedMark(float notAttendedMark) {
		this.notAttendedMark = notAttendedMark;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
