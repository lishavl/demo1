/**
 * @Author JOSSINA JOSE.
 */

package com.pumex.tms.usermanagement.bean;

import java.util.Date;
import java.util.List;

public class FeedBackBean {
	private long id;
	private long topicid;
	private long trainerid;
	private Date startDateTime;
	private String topicname;
	private String trainername;
	private String lastname;
	private int report;
	private String data;
	private String comment;
	private int followup;
	private int demo;
	private int status;
	private long count;
	private String trainermail;
	private List<String> trainernames;

	private String attendeefn;
	private String attendeeln;
	private long attendeeid;
	
	private long user_id;
	private String firstName;
	private String lastName;
	private Date endDateTime;

	

	public FeedBackBean(long user_id, String firstName, String lastName) {
		super();
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public FeedBackBean(long topicid, long attendeeid, String attendeefn,
			String attendeeln) {
		super();
		this.topicid = topicid;
		this.attendeefn = attendeefn;
		this.attendeeln = attendeeln;
		this.attendeeid = attendeeid;
	}

	public FeedBackBean(long id, String attendeefn, String attendeeln,
			int report, String data, String comment, int followup, int demo) {
		super();
		this.id = id;
		this.report = report;
		this.data = data;
		this.comment = comment;
		this.followup = followup;
		this.demo = demo;
		this.attendeefn = attendeefn;
		this.attendeeln = attendeeln;
	}

	public FeedBackBean(long topicid, String topicname, long trainerid,
			String trainername, String lastname, int report, String data,
			String comment, int followup, int demo, int status,long attendeeid,String attendeefn,String attendeeln) {
		super();
		this.trainerid = trainerid;
		this.topicid = topicid;
		this.topicname = topicname;
		this.trainername = trainername;
		this.lastname = lastname;
		this.report = report;
		this.data = data;
		this.comment = comment;
		this.followup = followup;
		this.demo = demo;
		this.status = status;
		this.attendeeid = attendeeid;
		this.attendeefn = attendeefn;
		this.attendeeln = attendeeln;
	}

	public FeedBackBean(long topicid, Date startDateTime, String topicname) {
		super();
		this.topicid = topicid;
		this.startDateTime = startDateTime;
		this.topicname = topicname;
	}
	public FeedBackBean(long topicid, Date startDateTime,Date endDateTime,String topicname) {
		super();
		this.topicid = topicid;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.topicname = topicname;
	}

	public FeedBackBean(long topicid, String topicname, long trainerid,
			String trainername) {
		super();
		this.topicid = topicid;
		this.topicname = topicname;
		this.trainerid = trainerid;
		this.trainername = trainername;
	}

	public FeedBackBean() {
		super();
		this.id = id;
		this.topicid = topicid;
		this.startDateTime = startDateTime;
		this.topicname = topicname;
		this.attendeefn = attendeefn;
		this.attendeeln = attendeeln;
	}

	public FeedBackBean(long id, long topicid, long trainerid,
			Date startDateTime, String topicname, String trainername
			) {
		super();
		this.id = id;
		this.topicid = topicid;
		this.trainerid = trainerid;
		this.startDateTime = startDateTime;
		this.topicname = topicname;
		this.trainername = trainername;
	}
	public FeedBackBean(long id, long topicid, long trainerid,
			Date startDateTime, String topicname, String trainername,int status
			) {
		super();
		this.id = id;
		this.topicid = topicid;
		this.trainerid = trainerid;
		this.startDateTime = startDateTime;
		this.topicname = topicname;
		this.trainername = trainername;
		this.status =status;
	}

	public long getAttendeeid() {
		return attendeeid;
	}

	public void setAttendeeid(long attendeeid) {
		this.attendeeid = attendeeid;
	}

	public List<String> getTrainernames() {
		return trainernames;
	}

	public void setTrainernames(List<String> trainernames) {
		this.trainernames = trainernames;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTopicid() {
		return topicid;
	}

	public void setTopicid(long topicid) {
		this.topicid = topicid;
	}

	public long getTrainerid() {
		return trainerid;
	}

	public void setTrainerid(long trainerid) {
		this.trainerid = trainerid;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	private List<FeedBackBean> feedbacklist;

	public List<FeedBackBean> getFeedbacklist() {
		return feedbacklist;
	}

	public void setFeedbacklist(List<FeedBackBean> feedbacklist) {
		this.feedbacklist = feedbacklist;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	
	
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
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

	public void setFollowup(int followup) {
		this.followup = followup;
	}

	public int getDemo() {
		return demo;
	}

	public void setDemo(int demo) {
		this.demo = demo;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getTopicname() {
		return topicname;
	}

	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}

	public String getTrainername() {
		return trainername;
	}

	public void setTrainername(String trainername) {
		this.trainername = trainername;
	}

	public String getTrainermail() {
		return trainermail;
	}

	public void setTrainermail(String trainermail) {
		this.trainermail = trainermail;
	}

	public String getAttendeefn() {
		return attendeefn;
	}

	public void setAttendeefn(String attendeefn) {
		this.attendeefn = attendeefn;
	}

	public String getAttendeeln() {
		return attendeeln;
	}

	public void setAttendeeln(String attendeeln) {
		this.attendeeln = attendeeln;
	}
	

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Override
	public String toString() {
		return "FeedBackBean [id=" + id + ", topicid=" + topicid
				+ ", trainerid=" + trainerid + ", startDateTime="
				+ startDateTime + ", topicname=" + topicname + ", trainername="
				+ trainername + ", lastname=" + lastname + ", report=" + report
				+ ", data=" + data + ", comment=" + comment + ", followup="
				+ followup + ", demo=" + demo + ", status=" + status
				+ ", count=" + count + ", trainermail=" + trainermail
				+ ", attendeefn=" + attendeefn + ", attendeeln=" + attendeeln
				+ ", feedbacklist=" + feedbacklist + "]";
	}

}
