package com.pumex.tms.report.bean;

import com.pumex.tms.models.TrainingTopic;

public class PerformanceAnalysisBean {

	private long totalPrograms;
	private long upcomingPrograms;
	private long attendedPrograms;
	private long completedPrograms;

	private TrainingTopic topic;
	private int attendedStatus;
	private boolean feedbacked;

	public PerformanceAnalysisBean() {
		super();
	}

	public PerformanceAnalysisBean(TrainingTopic topic, int attendedStatus) {
		super();
		this.topic = topic;
		this.attendedStatus = attendedStatus;
	}

	public long getTotalPrograms() {
		return totalPrograms;
	}

	public void setTotalPrograms(long totalPrograms) {
		this.totalPrograms = totalPrograms;
	}

	public long getUpcomingPrograms() {
		return upcomingPrograms;
	}

	public void setUpcomingPrograms(long upcomingPrograms) {
		this.upcomingPrograms = upcomingPrograms;
	}

	public long getAttendedPrograms() {
		return attendedPrograms;
	}

	public void setAttendedPrograms(long attendedPrograms) {
		this.attendedPrograms = attendedPrograms;
	}

	public long getCompletedPrograms() {
		return completedPrograms;
	}

	public void setCompletedPrograms(long completedPrograms) {
		this.completedPrograms = completedPrograms;
	}

	public TrainingTopic getTopic() {
		return topic;
	}

	public void setTopic(TrainingTopic topic) {
		this.topic = topic;
	}

	public int getAttendedStatus() {
		return attendedStatus;
	}

	public void setAttendedStatus(int attendedStatus) {
		this.attendedStatus = attendedStatus;
	}

	public boolean isFeedbacked() {
		return feedbacked;
	}

	public void setFeedbacked(boolean feedbacked) {
		this.feedbacked = feedbacked;
	}

}
