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
@Table(name = AppConstants.TABLE_NAMES.TOPIC_ATTENDEE_MAPPING)
public class TopicAttendee implements Serializable {

	private static final long serialVersionUID = 6064769313933799975L;

	public TopicAttendee() {
		super();
	}

	public TopicAttendee(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition = "bigint(15)")
	private long id;

	@OneToOne
	@JoinColumn(name = "topic_id", columnDefinition = "bigint(15)")
	private TrainingTopic topic;

	@OneToOne
	@JoinColumn(name = "attendee_id", columnDefinition = "bigint(15)")
	private UserDetails attendee;

	@Column(name = "attended_status", columnDefinition = "int(1) default '0'")
	private int attendedStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TrainingTopic getTopic() {
		return topic;
	}

	public void setTopic(TrainingTopic topic) {
		this.topic = topic;
	}

	public UserDetails getAttendee() {
		return attendee;
	}

	public void setAttendee(UserDetails attendee) {
		this.attendee = attendee;
	}

	public int getAttendedStatus() {
		return attendedStatus;
	}

	public void setAttendedStatus(int attendedStatus) {
		this.attendedStatus = attendedStatus;
	}

}
