package com.pumex.tms.models;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = AppConstants.TABLE_NAMES.TRAINING_TOPICS)
public class TrainingTopic implements Serializable {

	private static final long serialVersionUID = -759640592748726L;

	public TrainingTopic() {
		super();
	}

	public TrainingTopic(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "topic_id", columnDefinition = "bigint(15)")
	private long id;

	@Column(name = "topic", length = 100)
	private String topic;

	@Column(name = "description", length = 250)
	private String description;

	@OneToOne
	@JoinColumn(name = "category_id")
	private TrainingCategory category;

	@Column(name = "start_date_time")
	private Timestamp startDateTime;

	@Column(name = "end_date_time")
	private Timestamp endDateTime;

	@Column(name = "status", columnDefinition = "int(1) default '1'", nullable = false)
	private int status;
	
	@Column(name = "email_status", columnDefinition = "int(1) default '0'", nullable = false)
	private int emailstatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TrainingCategory getCategory() {
		return category;
	}

	public void setCategory(TrainingCategory category) {
		this.category = category;
	}

	public Timestamp getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Timestamp startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Timestamp getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Timestamp endDateTime) {
		this.endDateTime = endDateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getEmailstatus() {
		return emailstatus;
	}

	public void setEmailstatus(int emailstatus) {
		this.emailstatus = emailstatus;
	}
	
	

}
