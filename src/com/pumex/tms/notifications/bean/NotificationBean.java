package com.pumex.tms.notifications.bean;

import java.util.Date;
import java.util.List;

public class NotificationBean {

	private long count;
	private long topicid;
	private long userid;
	private String topicname;
	private Date sdate;
	private List<Long> trainers;
	private List<Long> attendees;

	private String firstName;
	private String lastName;
	private String email;

	private long testid;
	private String title;
	
	private long notificationid;
	private int type;
	
	private int status;
	private String etime;

	public NotificationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificationBean(long notificationid,long topicid, String topicname, Date sdate,int status,int type) {
		super();
		this.notificationid = notificationid;
		this.topicid = topicid;
		this.topicname = topicname;
		this.sdate = sdate;
		this.status = status;
		this.type = type;
	}

	public NotificationBean(long userid, String firstName, String lastName,
			long notificationid, int status,int type) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.notificationid = notificationid;
		this.status = status;
		this.type = type;
	}

	public NotificationBean(long topicid, long userid, long testid, int status,String title) {
		super();
		this.topicid = topicid;
		this.userid = userid;
		this.testid = testid;
		this.status = status;
		this.title = title;
	}

	public NotificationBean(long testid,long topicid, String title,long userid, String firstName,
			String lastName,String email) {
		super();
		this.testid = testid;
		this.topicid = topicid;
		this.title = title;
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;

	}

	public NotificationBean(long topicid, long userid, String topicname,
			String firstName, String lastName,String email) {
		super();
		this.topicid = topicid;
		this.userid = userid;
		this.topicname = topicname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public NotificationBean(long topicid, long userid, String topicname,
			Date sdate, String firstName, String lastName, String email) {
		super();
		this.topicid = topicid;
		this.userid = userid;
		this.topicname = topicname;
		this.sdate = sdate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	

	public NotificationBean(long notificationid,long topicid, long userid, String topicname,
			String firstName, String lastName, int status,int type) {
		super();
		this.notificationid = notificationid;
		this.topicid = topicid;
		this.userid = userid;
		this.topicname = topicname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.type = type;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getTopicid() {
		return topicid;
	}

	public void setTopicid(long topicid) {
		this.topicid = topicid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getTopicname() {
		return topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Long> getTrainers() {
		return trainers;
	}

	public List<Long> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Long> attendees) {
		this.attendees = attendees;
	}

	public void setTrainers(List<Long> trainers) {
		this.trainers = trainers;
	}

	public long getTestid() {
		return testid;
	}

	public void setTestid(long testid) {
		this.testid = testid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

	public long getNotificationid() {
		return notificationid;
	}

	public void setNotificationid(long notificationid) {
		this.notificationid = notificationid;
	}
	
	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "NotificationBean [count=" + count + ", topicid=" + topicid
				+ ", userid=" + userid + ", topicname=" + topicname
				+ ", sdate=" + sdate + "]";
	}

}
