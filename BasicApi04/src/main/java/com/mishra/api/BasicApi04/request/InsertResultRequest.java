package com.mishra.api.BasicApi04.request;
import java.util.List;

import com.mishra.api.BasicApi04.structs.SubjectScore;
 

public class InsertResultRequest {
	String txnId;
	int enrollNo;
	int examType;
	List<SubjectScore> result;
//	int subjectId;
//	int totalMarks;
//	int obtainedMarks;
	
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public int getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(int enrollNo) {
		this.enrollNo = enrollNo;
	}
	public int getExamType() {
		return examType;
	}
	public void setExamType(int examType) {
		this.examType = examType;
	}
//	public int getSubjectId() {
//		return subjectId;
//	}
//	public void setSubjectId(int subjectId) {
//		this.subjectId = subjectId;
//	}
//	public int getTotalMarks() {
//		return totalMarks;
//	}
//	public void setTotalMarks(int totalMarks) {
//		this.totalMarks = totalMarks;
//	}
//	public int getObtainedMarks() {
//		return obtainedMarks;
//	}
//	public void setObtainedMarks(int obtainedMarks) {
//		this.obtainedMarks = obtainedMarks;
//	}
	public List<SubjectScore> getResult() {
		return result;
	}
	public void setResult(List<SubjectScore> result) {
		this.result = result;
	}
}
