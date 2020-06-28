package com.mishra.api.BasicApi04.structs;

public class SubjectResult {
	int subId;
	String subName;
	int totalMarks;
	int obtainedMarks;
	int status;
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	public int getObtainedMarks() {
		return obtainedMarks;
	}
	public void setObtainedMarks(int obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
