package com.pumex.tms.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pumex.tms.util.AppConstants;

/**
 * @Author JINSHAD P.T.
 * @Date 15/06/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.ONLINE_TEST)
public class OnlineTest implements Serializable {

	private static final long serialVersionUID = 5542800624354330823L;

	public OnlineTest() {
		super();
	}

	public OnlineTest(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "test_id", columnDefinition = "bigint(15)")
	private long id;

	@Column(name = "title", columnDefinition = "varchar(100)")
	private String title;

	@Column(name = "description", columnDefinition = "varchar(200)")
	private String description;

	@OneToOne
	@JoinColumn(name = "training_id")
	private TrainingTopic training;

	@Column(name = "no_of_questions", columnDefinition = "int(3)")
	private int numberOfQuestions;

	@Column(name = "time_minutes", columnDefinition = "int(5)")
	private int timeMinutes;

	@Column(name = "pass_mark", columnDefinition = "float(6)")
	private float passMark;

	@Column(name = "correct_answer_mark", columnDefinition = "float(6)")
	private float correctAnswerMark;

	@Column(name = "wrong_answer_mark", columnDefinition = "float(6)")
	private float wrongAnswerMark;

	@Column(name = "not_attended_mark", columnDefinition = "float(6)")
	private float notAttendedMark;

	@Column(name = "status", columnDefinition = "int(1) default '1'", nullable = false)
	private int status;

	@Transient
	private boolean finished;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TrainingTopic getTraining() {
		return training;
	}

	public void setTraining(TrainingTopic training) {
		this.training = training;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getTimeMinutes() {
		return timeMinutes;
	}

	public void setTimeMinutes(int timeMinutes) {
		this.timeMinutes = timeMinutes;
	}

	public float getPassMark() {
		return passMark;
	}

	public void setPassMark(float passMark) {
		this.passMark = passMark;
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

}
