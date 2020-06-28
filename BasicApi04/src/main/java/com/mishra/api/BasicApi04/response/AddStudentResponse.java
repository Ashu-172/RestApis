package com.mishra.api.BasicApi04.response;

import com.mishra.api.BasicApi04.structs.Response;

public class AddStudentResponse {
	String txnId;
	int totalStudents;
	Response response;
	
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public int getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	
	
}
