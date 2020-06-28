package com.mishra.api.BasicApi04.response;

import com.mishra.api.BasicApi04.structs.Response;
import com.mishra.api.BasicApi04.structs.StudentInfo;

public class GetStudentResponse {

	String txnId;
	StudentInfo studentinfo;
	Response response;
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public StudentInfo getStudentinfo() {
		return studentinfo;
	}
	public void setStudentinfo(StudentInfo studentinfo) {
		this.studentinfo = studentinfo;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
}
