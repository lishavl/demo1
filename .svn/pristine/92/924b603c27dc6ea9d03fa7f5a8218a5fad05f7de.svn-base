package com.pumex.tms.trainingmanagement.bean;

import java.sql.Timestamp;
import java.util.Date;

public class TrainerTopicBean {
	private long id;
	private String topic;
	private String name;
	private String description;
	private int day;
	private String mail;
	private long trainerid;
	private String month;
	private Date date;
	private long attendedCount;
	private long notAttendedCount;

	public TrainerTopicBean(String topic, String name, String mail,
			long trainerid, Date date) {
		super();
		this.topic = topic;
		this.name = name;
		this.mail = mail;
		this.trainerid = trainerid;
		this.date = date;
	}

	public TrainerTopicBean(long id, String topic, String description, Date date) {
		super();
		this.id = id;
		this.topic = topic;
		this.description = description;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(long trainerid) {
		this.trainerid = trainerid;
	}

	public TrainerTopicBean() {
		super();
	}

	public TrainerTopicBean(long id, String topic) {
		super();
		this.id = id;
		this.topic = topic;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "TrainerTopicBean [id=" + id + ", topic=" + topic + ", name="
				+ name + "]";
	}

	public long getAttendedCount() {
		return attendedCount;
	}

	public void setAttendedCount(long attendedCount) {
		this.attendedCount = attendedCount;
	}

	public long getNotAttendedCount() {
		return notAttendedCount;
	}

	public void setNotAttendedCount(long notAttendedCount) {
		this.notAttendedCount = notAttendedCount;
	}

}
