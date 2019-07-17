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
@Table(name = AppConstants.TABLE_NAMES.TOPIC_TRAINER_MAPPING)
public class TopicTrainer implements Serializable {

	private static final long serialVersionUID = 8499707163394893282L;

	public TopicTrainer() {
		super();
	}

	public TopicTrainer(long id) {
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
	@JoinColumn(name = "trainer_id", columnDefinition = "bigint(15)")
	private UserDetails trainer;

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

	public UserDetails getTrainer() {
		return trainer;
	}

	public void setTrainer(UserDetails trainer) {
		this.trainer = trainer;
	}

}
