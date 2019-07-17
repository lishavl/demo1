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
 * @Date 19/09/2016
 */

@Entity
@Table(name = AppConstants.TABLE_NAMES.NOTIFICATIONS)
public class Notifications implements Serializable {

	private static final long serialVersionUID = -2196679438587976013L;

	public Notifications() {
		super();
	}

	public Notifications(long id) {
		super();
		this.id = id;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition = "bigint(15)")
	private long id;

	@Column(name = "user_id", columnDefinition = "bigint(15)")
	private long userId;

	@OneToOne
	@JoinColumn(name = "training_id")
	private TrainingTopic training;

	@Column(name = "type", columnDefinition = "int(2)")
	private int type;

	@Column(name = "status", columnDefinition = "int(1) default '0'")
	private int status;
	
	@Column(name = "adminstatus", columnDefinition = "int(1) default '0'")
	private int adminstatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public TrainingTopic getTraining() {
		return training;
	}

	public void setTraining(TrainingTopic training) {
		this.training = training;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAdminstatus() {
		return adminstatus;
	}

	public void setAdminstatus(int adminstatus) {
		this.adminstatus = adminstatus;
	}
	
	

}
