package com.mishra.api.BasicApi04.structs;

public class FailedResult {
	int subId;
	int totalMarks;
	int obtainedMarks;
	int errorCode;
	
	String description;

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public void setObtainedMarks(int obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSubId() {
		return subId;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public int getObtainedMarks() {
		return obtainedMarks;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getDescription() {
		return description;
	}
	
}
