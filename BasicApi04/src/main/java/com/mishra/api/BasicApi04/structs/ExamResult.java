package com.mishra.api.BasicApi04.structs;

import java.util.HashMap;

public class ExamResult {
	
	int 	examType;
	int 	totalMarks;
	int 	sum;
	double	percentResult;
	int 	resultStatus;
	String	resultDate;
	public HashMap<Integer, SubjectResult> subjectResult = new HashMap<Integer, SubjectResult>();
	//List<SubjectResult> subjectResult;
	
	public int getExamType() {
		return examType;
	}
	public void setExamType(int examType) {
		this.examType = examType;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public double getPercentResult() {
		return percentResult;
	}
	public void setPercentResult(double resultPercent) {
		this.percentResult = resultPercent;
	}
	public int getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(int resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getResultDate() {
		return resultDate;
	}
	public void setResultDate(String resultDate) {
		this.resultDate = resultDate;
	}
//	public HashMap<Integer, SubjectResult> getSubjectResult() {
//		return subjectResult;
//	}
//	public void setSubjectResult(HashMap<Integer, SubjectResult> subjectResult) {
//		this.subjectResult = subjectResult;
//	}
	
	
}
