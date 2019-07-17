package com.pumex.tms.trainingmanagement.bean;

import java.util.Arrays;
import java.util.List;

import com.pumex.tms.models.TrainingTopic;
import com.pumex.tms.models.UserDetails;

public class TopicAttendeeBean {
	private long id;
	private String topic;
	private String attendee;
	private int attendedStatus;
	private long testid;
	private Long[] userids;
	private String attendeelastname;
	private long count;
	private long userId;
	private long topicid;
	private List<String> trainernames;
	
	
	public TopicAttendeeBean(long id, String attendee,int attendedStatus) {
		super();
		this.id = id;
		this.attendee = attendee;
		this.attendedStatus = attendedStatus;
	}
	
	public long getTopicid() {
		return topicid;
	}

	public void setTopicid(long topicid) {
		this.topicid = topicid;
	}

	public long getTestid() {
		return testid;
	}
	public void setTestid(long testid) {
		this.testid = testid;
	}
	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Long[] getUserids() {
		return userids;
	}

	public void setUserids(Long[] userids) {
		this.userids = userids;
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
	public String getAttendee() {
		return attendee;
	}
	public void setAttendee(String attendee) {
		this.attendee = attendee;
	}
	public int getAttendedStatus() {
		return attendedStatus;
	}
	public void setAttendedStatus(int attendedStatus) {
		this.attendedStatus = attendedStatus;
	}
	

	public String getAttendeelastname() {
		return attendeelastname;
	}

	public void setAttendeelastname(String attendeelastname) {
		this.attendeelastname = attendeelastname;
	}

	

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	

	public List<String> getTrainernames() {
		return trainernames;
	}

	public void setTrainernames(List<String> trainernames) {
		this.trainernames = trainernames;
	}

	@Override
	public String toString() {
		return "TopicAttendeeBean [id=" + id + ", topic=" + topic
				+ ", attendee=" + attendee + ", attendedStatus="
				+ attendedStatus + ", testid=" + testid + ", userids="
				+ Arrays.toString(userids) + ", attendeelastname="
				+ attendeelastname + ", count=" + count + ", userId=" + userId
				+ "]";
	}

	public TopicAttendeeBean(long id, String attendee, String attendeelastname,
			 long userId,String topic,long topicid) {
		super();
		this.id = id;
		this.attendee = attendee;
		this.attendeelastname = attendeelastname;
		this.userId = userId;
		this.topic = topic;
		this.topicid = topicid;
	}

	public TopicAttendeeBean(long id, String attendee, String attendeelastname,
			 long userId,String topic,long topicid,int attendedStatus) {
		super();
		this.id = id;
		this.attendee = attendee;
		this.attendeelastname = attendeelastname;
		this.userId = userId;
		this.topic = topic;
		this.topicid = topicid;
		this.attendedStatus =attendedStatus;
	}
	
	

	

}
