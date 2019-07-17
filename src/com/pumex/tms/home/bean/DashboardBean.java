package com.pumex.tms.home.bean;

/**
 * Bean class for dashboard
 * 
 * @Author JINSHAD P.T.
 * @Date 13/08/2016
 */
public class DashboardBean {

	private long upcomingPgmCount;
	private long completedPgmCount;
	private long monthlyPgmCount;
	private long monthlyAttendedPgmCount;
	private long totalPgmCount;
	private long totalUsersCount;
	private long attendedTests;
	private long pendingTests;

	public long getUpcomingPgmCount() {
		return upcomingPgmCount;
	}

	public void setUpcomingPgmCount(long upcomingPgmCount) {
		this.upcomingPgmCount = upcomingPgmCount;
	}

	public long getCompletedPgmCount() {
		return completedPgmCount;
	}

	public void setCompletedPgmCount(long completedPgmCount) {
		this.completedPgmCount = completedPgmCount;
	}

	public long getMonthlyPgmCount() {
		return monthlyPgmCount;
	}

	public void setMonthlyPgmCount(long monthlyPgmCount) {
		this.monthlyPgmCount = monthlyPgmCount;
	}

	public long getMonthlyAttendedPgmCount() {
		return monthlyAttendedPgmCount;
	}

	public void setMonthlyAttendedPgmCount(long monthlyAttendedPgmCount) {
		this.monthlyAttendedPgmCount = monthlyAttendedPgmCount;
	}

	public long getTotalPgmCount() {
		return totalPgmCount;
	}

	public void setTotalPgmCount(long totalPgmCount) {
		this.totalPgmCount = totalPgmCount;
	}

	public long getTotalUsersCount() {
		return totalUsersCount;
	}

	public void setTotalUsersCount(long totalUsersCount) {
		this.totalUsersCount = totalUsersCount;
	}

	public long getAttendedTests() {
		return attendedTests;
	}

	public void setAttendedTests(long attendedTests) {
		this.attendedTests = attendedTests;
	}

	public long getPendingTests() {
		return pendingTests;
	}

	public void setPendingTests(long pendingTests) {
		this.pendingTests = pendingTests;
	}

}
