package com.pumex.tms.onlinetest.bean;

import java.util.List;

public class TestReportBean {
	private List<TestResultBean> testresult;
	private long nopass;
	private long nofail;
	
	public TestReportBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<TestResultBean> getTestresult() {
		return testresult;
	}
	public void setTestresult(List<TestResultBean> testresult) {
		this.testresult = testresult;
	}
	public long getNopass() {
		return nopass;
	}
	public void setNopass(long nopass) {
		this.nopass = nopass;
	}
	public long getNofail() {
		return nofail;
	}
	public void setNofail(long nofail) {
		this.nofail = nofail;
	}
	@Override
	public String toString() {
		return "TestReportBean [testresult=" + testresult + ", nopass="
				+ nopass + ", nofail=" + nofail + "]";
	}
	

}
