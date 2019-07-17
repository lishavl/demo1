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
@Table(name = AppConstants.TABLE_NAMES.ATTENDEE_ANSWER)
public class AttendeeAnswer implements Serializable {

	private static final long serialVersionUID = 8716817816831034520L;

	public AttendeeAnswer() {
		super();
	}

	public AttendeeAnswer(long id) {
		super();
		this.id = id;
	}

	public AttendeeAnswer(Question question, UserDetails attendee, int answer) {
		super();
		this.question = question;
		this.attendee = attendee;
		this.answer = answer;
	}

	@Id
	@GeneratedValue
	@Column(name = "answer_id", columnDefinition = "bigint(15)")
	private long id;

	@OneToOne
	@JoinColumn(name = "question_id", columnDefinition = "bigint(15)")
	private Question question;

	@OneToOne
	@JoinColumn(name = "attendee_id", columnDefinition = "bigint(15)")
	private UserDetails attendee;

	@Column(name = "answer", columnDefinition = "int(1)")
	private int answer;

	@Column(name = "status", columnDefinition = "int(1) default '0'")
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public UserDetails getAttendee() {
		return attendee;
	}

	public void setAttendee(UserDetails attendee) {
		this.attendee = attendee;
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

}
