package com.mishra.api.BasicApi04.response;

import java.util.ArrayList;
import java.util.List;

import com.mishra.api.BasicApi04.structs.ExamResult;
import com.mishra.api.BasicApi04.structs.Response;
import com.mishra.api.BasicApi04.structs.StudentInfo;

public class GetStudentResultResponse {

	String txnId;
	StudentInfo studentInfo;
	List<ExamResult> result = new ArrayList<ExamResult>();
	Response response;
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}
	public void setResult(List<ExamResult> result) {
		this.result = result;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public String getTxnId() {
		return txnId;
	}
	public StudentInfo getStudentInfo() {
		return studentInfo;
	}
	public List<ExamResult> getResult() {
		return result;
	}
	public Response getResponse() {
		return response;
	}	
}
