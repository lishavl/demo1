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
@Table(name = AppConstants.TABLE_NAMES.ATTENDEES_FEEDBACKS)
public class AttendeeFeedback implements Serializable {

	private static final long serialVersionUID = 5807690005010468808L;

	public AttendeeFeedback() {
		super();
	}

	public AttendeeFeedback(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "feedback_id", columnDefinition = "bigint(15)")
	private long id;

	@OneToOne
	@JoinColumn(name = "attendee_id", columnDefinition = "bigint(15)")
	private UserDetails attendee;

	@OneToOne
	@JoinColumn(name = "topic_id", columnDefinition = "bigint(15)")
	private TrainingTopic topic;

	@OneToOne
	@JoinColumn(name = "trainer_id", columnDefinition = "bigint(15)")
	private UserDetails trainer;

	@Column(name = "feedback", columnDefinition = "int(2)")
	private int feedback;

	@Column(name = "why_appropriate", columnDefinition = "varchar(500)")
	private String whyAppropriate;

	@Column(name = "comment", columnDefinition = "varchar(500)")
	private String comment;

	@Column(name = "followup_session_needed", columnDefinition = "int(1)")
	private int followupSessionNeeded;

	@Column(name = "demo_needed", columnDefinition = "int(1)")
	private int demoNeeded;
	
	@Column(name = "status", columnDefinition = "int(1)")
	private int status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserDetails getAttendee() {
		return attendee;
	}

	public void setAttendee(UserDetails attendee) {
		this.attendee = attendee;
	}

	public TrainingTopic getTopic() {
		return topic;
	}

	public void setTopic(TrainingTopic topic) {
		this.topic = topic;
	}

	public UserDetails getTrainer() {
		return trainer;
	}

	public void setTrainer(UserDetails trainer) {
		this.trainer = trainer;
	}

	public int getFeedback() {
		return feedback;
	}

	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}

	public String getWhyAppropriate() {
		return whyAppropriate;
	}

	public void setWhyAppropriate(String whyAppropriate) {
		this.whyAppropriate = whyAppropriate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getFollowupSessionNeeded() {
		return followupSessionNeeded;
	}

	public void setFollowupSessionNeeded(int followupSessionNeeded) {
		this.followupSessionNeeded = followupSessionNeeded;
	}

	public int getDemoNeeded() {
		return demoNeeded;
	}

	public void setDemoNeeded(int demoNeeded) {
		this.demoNeeded = demoNeeded;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
