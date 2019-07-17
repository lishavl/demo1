/**
 * @Author JOSSINA JOSE.
 */


package com.pumex.tms.usermanagement.bean;

import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;

public class AttendeeFeedbackBean {
	private long id;
	private UserDetails attendee;
	private TrainingTopic topicname;
	private UserDetails trainername;
	private int report;
	private String data;
	private String comment;
	private int followup;
	private int demo;
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
	public TrainingTopic getTopicname() {
		return topicname;
	}
	public void setTopicname(TrainingTopic topicname) {
		this.topicname = topicname;
	}
	public UserDetails getTrainername() {
		return trainername;
	}
	public void setTrainername(UserDetails trainername) {
		this.trainername = trainername;
	}
	public int getReport() {
		return report;
	}
	public void setReport(int report) {
		this.report = report;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getFollowup() {
		return followup;
	}
	public void setFollowup(int followup) {
		this.followup = followup;
	}
	public int getDemo() {
		return demo;
	}
	public void setDemo(int demo) {
		this.demo = demo;
	}
	@Override
	public String toString() {
		return "AttendeeFeedbackBean [id=" + id + ", attendee=" + attendee
				+ ", topicname=" + topicname + ", trainername=" + trainername
				+ ", report=" + report + ", data=" + data + ", comment="
				+ comment + ", followup=" + followup + ", demo=" + demo + "]";
	}
	public AttendeeFeedbackBean(long id, UserDetails attendee,
			TrainingTopic topicname, UserDetails trainername, int report,
			String data, String comment, int followup, int demo) {
		super();
		this.id = id;
		this.attendee = attendee;
		this.topicname = topicname;
		this.trainername = trainername;
		this.report = report;
		this.data = data;
		this.comment = comment;
		this.followup = followup;
		this.demo = demo;
	}
	
	

}
