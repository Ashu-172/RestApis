package com.mishra.api.BasicApi04.response;

import java.util.ArrayList;
import java.util.List;

import com.mishra.api.BasicApi04.structs.Response;
import com.mishra.api.BasicApi04.structs.StudentInfo;

public class GetClassStudentsResponse {
	String txnId;
	int classId;
	int totalStudents;
	List<StudentInfo> students = new ArrayList<StudentInfo>();
	Response response;
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}
	public List<StudentInfo> getStudents() {
		return students;
	}
	public void setStudents(List<StudentInfo> students) {
		this.students = students;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
}
