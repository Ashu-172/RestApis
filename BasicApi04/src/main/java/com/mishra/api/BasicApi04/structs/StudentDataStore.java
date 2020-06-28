package com.mishra.api.BasicApi04.structs;

import java.util.HashMap;

public class StudentDataStore {
	StudentInfo	studentData;
	public HashMap<Integer, ExamResult> results = new HashMap<Integer, ExamResult>(); 
	
//	List<ExamResult> results = new ArrayList<ExamResult>();
	
	public void setStudentData(StudentInfo studentData) {
		this.studentData = studentData;
	}
	public StudentInfo getStudentData() {
		return studentData;
	}
	public HashMap<Integer, ExamResult> getResults() {
		return results;
	}
	public void setResults(HashMap<Integer, ExamResult> results) {
		this.results = results;
	}
	
	
}
