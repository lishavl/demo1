package com.pumex.tms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pumex.tms.util.AppConstants;

/**
 * @Author JINSHAD P.T.
 * @Date 15/06/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.QUESTIONS)
public class Question implements Serializable {

	private static final long serialVersionUID = 2260537373959991263L;

	public Question() {
		super();
	}

	public Question(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "question_id", columnDefinition = "bigint(15)")
	private long id;

	@OneToOne
	@JoinColumn(name = "test_id", columnDefinition = "bigint(15)")
	private OnlineTest test;

	@Column(name = "question", columnDefinition = "varchar(500)")
	private String question;

	@Column(name = "choice1", columnDefinition = "varchar(200)")
	private String choice1;

	@Column(name = "choice2", columnDefinition = "varchar(200)")
	private String choice2;

	@Column(name = "choice3", columnDefinition = "varchar(200)")
	private String choice3;

	@Column(name = "choice4", columnDefinition = "varchar(200)")
	private String choice4;

	@Column(name = "answer", columnDefinition = "int(1)")
	private int answer;

	@Column(name = "no_of_choices", columnDefinition = "int(1) default '4'", nullable = false)
	private int noOfChoices;

	@Column(name = "status", columnDefinition = "int(1) default '1'", nullable = false)
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OnlineTest getTest() {
		return test;
	}

	public void setTest(OnlineTest test) {
		this.test = test;
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
