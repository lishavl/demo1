/**
 * Bean class for test result
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.onlinetest.bean;

public class TestResultBean {
	private String attendee;
	private long attendeeid;
	private float mark;
	private int numberOfQuestions;
	private float passMark;
	private int totalAnswered;
	private String status;
	private long passnumber;
	private long failednumber;
	private long notAttendedNumber;
	private float correctAnswerMark;
	private String testname;
	private long testid;
	private String Question;
	private int attendeeanswer;
	private int correctanswer;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private String answerbyattendee;
	private String correctanswertoquestion;
	private int successstatus;

	public TestResultBean() {
		super();
	}
    
	
	
	public TestResultBean( long attendeeid,String attendee,long testid,String testname) {
		super();
		this.attendeeid = attendeeid;
		this.attendee = attendee;
		this.testid=testid;
		this.testname=testname;
	}



	public void setAttendeeid(long attendeeid) {
		this.attendeeid = attendeeid;
	}



	public float getCorrectAnswerMark() {
		return correctAnswerMark;
	}

	public void setCorrectAnswerMark(float correctAnswerMark) {
		this.correctAnswerMark = correctAnswerMark;
	}

	public long getPassnumber() {
		return passnumber;
	}

	public void setPassnumber(long passnumber) {
		this.passnumber = passnumber;
	}

	public long getFailednumber() {
		return failednumber;
	}

	public void setFailednumber(long failednumber) {
		this.failednumber = failednumber;
	}

	public String getAttendee() {
		return attendee;
	}

	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public float getPassMark() {
		return passMark;
	}

	public void setPassMark(float passMark) {
		this.passMark = passMark;
	}

	public int getTotalAnswered() {
		return totalAnswered;
	}

	public void setTotalAnswered(int totalAnswered) {
		this.totalAnswered = totalAnswered;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
	}

	public long getTestid() {
		return testid;
	}

	public void setTestid(long testid) {
		this.testid = testid;
	}

	public int getAttendeeanswer() {
		return attendeeanswer;
	}

	public void setAttendeeanswer(int attendeeanswer) {
		this.attendeeanswer = attendeeanswer;
	}

	public int getCorrectanswer() {
		return correctanswer;
	}

	public void setCorrectanswer(int correctanswer) {
		this.correctanswer = correctanswer;
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

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getChoice1() {
		return choice1;
	}

	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	public String getAnswerbyattendee() {
		return answerbyattendee;
	}

	public void setAnswerbyattendee(String answerbyattendee) {
		this.answerbyattendee = answerbyattendee;
	}

	public String getCorrectanswertoquestion() {
		return correctanswertoquestion;
	}

	public void setCorrectanswertoquestion(String correctanswertoquestion) {
		this.correctanswertoquestion = correctanswertoquestion;
	}
	

	public int getSuccessstatus() {
		return successstatus;
	}

	public void setSuccessstatus(int successstatus) {
		this.successstatus = successstatus;
	}
	

	public long getAttendeeid() {
		return attendeeid;
	}

	public void setAttendeeid(int attendeeid) {
		this.attendeeid = attendeeid;
	}

	@Override
	public String toString() {
		return "TestResultBean [attendee=" + attendee + ", mark=" + mark
				+ ", numberOfQuestions=" + numberOfQuestions + ", passMark="
				+ passMark + ", totalAnswered=" + totalAnswered + ", status="
				+ status + ", passnumber=" + passnumber + ", failednumber="
				+ failednumber + ", notAttendedNumber=" + notAttendedNumber
				+ ", correctAnswerMark=" + correctAnswerMark + ", testname="
				+ testname + ", testid=" + testid + ", Question=" + Question
				+ ", attendeeanswer=" + attendeeanswer + ", correctanswer="
				+ correctanswer + ", choice1=" + choice1 + ", choice2="
				+ choice2 + ", choice3=" + choice3 + ", choice4=" + choice4
				+ ", answerbyattendee=" + answerbyattendee
				+ ", correctanswertoquestion=" + correctanswertoquestion + "]";
	}

	public TestResultBean(long attendeeid,long testid,String testname,String attendee, float mark, int numberOfQuestions,
			float passMark, int totalAnswered, float correctAnswerMark) {
		super();
		this.attendeeid =attendeeid;
		this.testid = testid;
		this.testname = testname;
		this.attendee = attendee;
		this.mark = mark;
		this.numberOfQuestions = numberOfQuestions;
		this.passMark = passMark;
		this.totalAnswered = totalAnswered;
		this.correctAnswerMark = correctAnswerMark;
	}
	
	public TestResultBean(long attendeeid,long testid,String attendee, float mark, int numberOfQuestions,
			float passMark, int totalAnswered, float correctAnswerMark) {
		super();
		this.attendeeid =attendeeid;
		this.testid = testid;
		this.attendee = attendee;
		this.mark = mark;
		this.numberOfQuestions = numberOfQuestions;
		this.passMark = passMark;
		this.totalAnswered = totalAnswered;
		this.correctAnswerMark = correctAnswerMark;
	}

	public TestResultBean(long testid, String testname, float mark,
			int numberOfQuestions, float passMark, int totalAnswered,
			float correctAnswerMark) {
		super();
		this.testid = testid;
		this.testname = testname;
		this.mark = mark;
		this.numberOfQuestions = numberOfQuestions;
		this.passMark = passMark;
		this.totalAnswered = totalAnswered;
		this.correctAnswerMark = correctAnswerMark;
	}

	public TestResultBean(String question, int attendeeanswer,
			int correctanswer, String choice1, String choice2, String choice3,
			String choice4) {
		super();
		Question = question;
		this.attendeeanswer = attendeeanswer;
		this.correctanswer = correctanswer;
		this.choice1 = choice1;
		this.choice2 = choice2;
		this.choice3 = choice3;
		this.choice4 = choice4;
	}

	public long getNotAttendedNumber() {
		return notAttendedNumber;
	}

	public void setNotAttendedNumber(long notAttendedNumber) {
		this.notAttendedNumber = notAttendedNumber;
	}

}
