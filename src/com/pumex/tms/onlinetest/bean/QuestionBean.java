package com.pumex.tms.onlinetest.bean;


/**
 * Bean class for Question
 * 
 * @Author JINSHAD P.T.
 * @Date 26/06/2016
 */
public class QuestionBean {

	private long id;

	private String question;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private long testId;
	private int answer;
	private int status;
	private int noOfChoices;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getChoice2() {
		return choice2;
	}

	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	public String getChoice3() {
		return choice3;
	}

	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	public String getChoice4() {
		return choice4;
	}

	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNoOfChoices() {
		return noOfChoices;
	}

	public void setNoOfChoices(int noOfChoices) {
		this.noOfChoices = noOfChoices;
	}
}
