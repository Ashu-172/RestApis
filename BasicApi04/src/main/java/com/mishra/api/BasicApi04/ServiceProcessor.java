package com.mishra.api.BasicApi04;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mishra.api.BasicApi04.request.*;
import com.mishra.api.BasicApi04.response.*;
import com.mishra.api.BasicApi04.structs.*;

public class ServiceProcessor {
	HashMap<Integer,StudentDataStore> dataStore = new HashMap<Integer, StudentDataStore>();
	
	public AddStudentResponse processAddStudentRequest(AddStudentRequest lAddStudentRequest) {
		String 		lName;
		int 		lAge;
		int 		lClassId;
		
		LocalDate 	lDob = null;
		LocalDate 	lNow = null;
		
		Response   			lResponse 				= new Response();
		AddStudentResponse 	lAddStudentResponse 	= new AddStudentResponse();
		StudentInfo 		lStudentInfo 			= new StudentInfo();
		StudentDataStore	lDataStoreRecord		= new StudentDataStore();
		
		DateTimeFormatter 	format 					= DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		lAddStudentResponse.setTxnId(lAddStudentRequest.getTxnId());
		lName = lAddStudentRequest.getFirstname()+" "+lAddStudentRequest.getLastName();
		lNow = LocalDate.now();
		lClassId = lAddStudentRequest.getClassId();
		
		//Validating Date Of Birth
		try {
			lDob = ApiUtil.dateFormatter(lAddStudentRequest.getDob());
		} catch (Exception e) {
			e.printStackTrace();
			lResponse.setErrorCode(ApiUtil.INVALID_DOB_PARAM);
			lResponse.setDescription("Invalid Date Of Birth format..!");
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lAddStudentResponse.setResponse(lResponse);
			lAddStudentResponse.setTxnId(lAddStudentRequest.getTxnId());
			return lAddStudentResponse;
		}
		
		//Calculating Age
		Period diff = Period.between(lDob, lNow);
		lAge = diff.getYears();
		
		//Validating Class
		ApiUtil.Class lClass = ApiUtil.Class.get(lClassId);
		if(lClass == null) {
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.INVALID_CLASS_PARAM);
			lResponse.setDescription("Invalid Class Id (Range: 101 - 110)..!");
			lAddStudentResponse.setTxnId(lAddStudentRequest.getTxnId());
			lAddStudentResponse.setResponse(lResponse);
			return lAddStudentResponse;
		}
		
		//Filling Data to DataStore
		lStudentInfo.setName(lName);
		lStudentInfo.setAge(lAge);
		lStudentInfo.setDob(lDob.format(format));
		lStudentInfo.setMotherName(lAddStudentRequest.getMotherName());
		lStudentInfo.setRollNo(lAddStudentRequest.getRollNo());
		lStudentInfo.setEnrollNo(lAddStudentRequest.getEnrollNo());
		lStudentInfo.setSec(lAddStudentRequest.getSec());
		lStudentInfo.setClassId(lClassId);
		lStudentInfo.setClassName(lClass.toString());
		lDataStoreRecord.setStudentData(lStudentInfo);
		dataStore.put(lStudentInfo.getEnrollNo(), lDataStoreRecord);
		
		lResponse.setStatus(ApiUtil.STATUS_SUCCESS);
		lResponse.setErrorCode(ApiUtil.SUCCESS);
		lResponse.setDescription("Student Data Added Successfully..");
		lAddStudentResponse.setTxnId(lAddStudentRequest.getTxnId());
		lAddStudentResponse.setTotalStudents(dataStore.size());
		lAddStudentResponse.setResponse(lResponse);
		
		return lAddStudentResponse;
	}

	public GetStudentResponse processGetStudentRequest(GetStudentRequest lGetStudentRequest) {
		GetStudentResponse lGetStudentResponse 	= new GetStudentResponse();
		StudentDataStore lStudentDataStore 		= null;
		StudentInfo lStudentInfo 				= null;
		Response lResponse						= new Response();
		
		lGetStudentResponse.setTxnId(lGetStudentRequest.getTxnid());
		lStudentDataStore = dataStore.get(lGetStudentRequest.getEnrollNo());
		if(lStudentDataStore == null) {
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.STUDENT_NOT_FOUND);
			lResponse.setDescription("Student Details Not Found..!");
			lGetStudentResponse.setResponse(lResponse);
			
			return lGetStudentResponse;
		}
		
		lStudentInfo = lStudentDataStore.getStudentData();
		
		if(lStudentInfo == null) {
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.STUDENT_INFO_NOT_FOUND);
			lResponse.setDescription("Internal Error.." );
			lGetStudentResponse.setResponse(lResponse);
			
			return lGetStudentResponse;
		}
		
		lResponse.setStatus(ApiUtil.STATUS_SUCCESS);
		lResponse.setErrorCode(ApiUtil.SUCCESS);
		lResponse.setDescription("Student Information Found..");
		
		lGetStudentResponse.setStudentinfo(lStudentInfo);
		lGetStudentResponse.setResponse(lResponse);
		
		return lGetStudentResponse;
	}

	public GetClassStudentsResponse processGetClassStudentRequest(GetClassStudentsRequest lGetClassStudentsRequest) {
		GetClassStudentsResponse lGetClassStudentsResponse 	= new GetClassStudentsResponse();
		Response lResponse 									= new Response();
		List<StudentInfo> lStudents 						= new ArrayList<StudentInfo>();
		
		lGetClassStudentsResponse.setTxnId(lGetClassStudentsRequest.getTxnId());
		int lClassId = lGetClassStudentsRequest.getClassId();
		lGetClassStudentsResponse.setClassId(lClassId);
		
		//Validating ClassId
		ApiUtil.Class lClass = ApiUtil.Class.get(lClassId);
		if(lClass == null) {
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.INVALID_CLASS_PARAM);
			lResponse.setDescription("Invalid Class Id (Range: 101 - 110)..!");
			lGetClassStudentsResponse.setResponse(lResponse);
			
			return lGetClassStudentsResponse;
		}
		
		List<StudentDataStore> lCollection = new ArrayList<StudentDataStore>(dataStore.values());
		
		for(StudentDataStore data : lCollection) {
			StudentInfo lInfo;
			if((lInfo = data.getStudentData()).getClassId()==lClassId) {
				lStudents.add(lInfo);
			}
		}
		if(lStudents.size()==0) {
			lGetClassStudentsResponse.setTotalStudents(0);
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.NO_STUDENT_REGISTERED);
			lResponse.setDescription("No Student is Registered in the class..!");
			lGetClassStudentsResponse.setResponse(lResponse);
			
			return lGetClassStudentsResponse;
		}
		
		lResponse.setStatus(ApiUtil.STATUS_SUCCESS);
		lResponse.setErrorCode(ApiUtil.SUCCESS);
		lResponse.setDescription("Student(s) Found..");
		
		lGetClassStudentsResponse.setTotalStudents(lStudents.size());
		lGetClassStudentsResponse.setStudents(lStudents);
		lGetClassStudentsResponse.setResponse(lResponse);
		
		return lGetClassStudentsResponse;
	}

	public InsertResultResponse processInsertResultRequest(InsertResultRequest lInsertResultRequest) {
		InsertResultResponse lInsertResultResponse 	= new InsertResultResponse();
		Response lResponse 							= new Response();
		DateTimeFormatter 	format 					= DateTimeFormatter.ofPattern("dd-MM-yyyy");
		StudentDataStore lStudentDataStore;
		ExamResult lExamResult = null;						
		List<SubjectScore> lReqResultList; 
		int lExamType;
		int lEnrollNo;
		int lTotalMarks = 0;
		int lSumMarks = 0;
		
		List<SubjectScore> lSuccess 				= new ArrayList<SubjectScore>();
		List<FailedResult> lFailure 				= new ArrayList<FailedResult>();
		//List<SubjectResult> lResultList				= new ArrayList<SubjectResult>();
		//HashMap<Integer, SubjectResult> lResultMap	= new HashMap<Integer, SubjectResult>();
		
		lInsertResultResponse.setTxnId(lInsertResultRequest.getTxnId());
		
		lExamType = lInsertResultRequest.getExamType();
		lEnrollNo = lInsertResultRequest.getEnrollNo();
		//Fetching Student 
		if((lStudentDataStore = dataStore.get(lEnrollNo))==null) {
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.STUDENT_NOT_FOUND);
			lResponse.setDescription("Student does not exist..!");
			lInsertResultResponse.setResponse(lResponse);
			return lInsertResultResponse;
		}
		
		//Exam Type Validation
		if(lExamType != 101 && lExamType != 102 && lExamType != 103) {
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.INVALID_EXAM_PARAM);
			lResponse.setDescription("Invalid ExamType Param..!");
			lInsertResultResponse.setResponse(lResponse);
			return lInsertResultResponse;
		}
				
				
		HashMap<Integer, ExamResult> map = lStudentDataStore.getResults();
		if(map != null) {
			lExamResult = map.get(lExamType);
			System.out.println("TBR====1");
		}else {
			lExamResult = new ExamResult();
			System.out.println("TBR====2");
		}
		
		if((lReqResultList = lInsertResultRequest.getResult()).size() > 0) {
			for(SubjectScore data : lReqResultList) {
				//validating Subject Id
				String subName = ApiUtil.Subject.getSubject(data.getSubId());
				FailedResult lFailedResult = new FailedResult();
				if(subName == null) {
					lFailedResult.setSubId(data.getSubId());
					lFailedResult.setTotalMarks(data.getTotalMarks());
					lFailedResult.setObtainedMarks(data.getObtainedMarks());
					lFailedResult.setErrorCode(ApiUtil.INVALID_SUBJECT_ID_PARAM);
					lFailedResult.setDescription("Invalid Subject Id (Range 1001-1005)..!");
					lFailure.add(lFailedResult);
					continue;
				}
				//validating Marks
				SubjectResult lResult 	= new SubjectResult();
				double lMaxMarks 		= data.getTotalMarks();
				double lObtainedMarks 	= data.getObtainedMarks();
				
				if(lMaxMarks < lObtainedMarks) {
					lFailedResult.setSubId(data.getSubId());
					lFailedResult.setTotalMarks(data.getTotalMarks());
					lFailedResult.setObtainedMarks(data.getObtainedMarks());
					lFailedResult.setErrorCode(ApiUtil.INVALID_MARKS_PARAM);
					lFailedResult.setDescription("Invalid value of total or obtained marks..!");
					lFailure.add(lFailedResult);
					continue;
				}
				//Calculating Percents
				double lPercent = 0.0;
				lPercent = (lObtainedMarks/lMaxMarks)*100;
				
				//preparing finalResult
				if(lPercent<33.0) 
					lResult.setStatus(ApiUtil.RESULT_FAIL);
				else
					lResult.setStatus(ApiUtil.RESULT_PASS);
				
				lResult.setSubId(data.getSubId());
				lResult.setSubName(subName);
				lResult.setTotalMarks(data.getTotalMarks());
				lResult.setObtainedMarks(data.getObtainedMarks());
				
				lTotalMarks += lMaxMarks;
				lSumMarks += lObtainedMarks;
				
				lSuccess.add(data);
				//lResultList.add(lResult);
				lExamResult.subjectResult.put(lResult.getSubId(), lResult);
			}
			//inserting in the datastore
			
//			if(lExamResult == null) {
//				lExamResult = new ExamResult();
				lExamResult.setTotalMarks(lTotalMarks);
				lExamResult.setSum(lSumMarks);
//			}
//			else {
//				lExamResult.setTotalMarks(lExamResult.getTotalMarks()+lTotalMarks);
//				lExamResult.setSum(lExamResult.getSum()+lSumMarks);
//			}

			lExamResult.setExamType(lExamType);
			lExamResult.setResultDate(LocalDate.now().format(format));
			double ResultPercent = (lExamResult.getSum()/lExamResult.getTotalMarks())*100;
			lExamResult.setPercentResult(ResultPercent);
			if(ResultPercent < 33.0) {
				lExamResult.setResultStatus(ApiUtil.RESULT_FAIL);
			}else {
				lExamResult.setResultStatus(ApiUtil.RESULT_PASS);
			}
			//lExamResult.setSubjectResult(lResultMap);
			lStudentDataStore.results.put(lExamType, lExamResult);
			
			
			lResponse.setStatus(ApiUtil.STATUS_SUCCESS);
			if(lSuccess.size()>0 && lFailure.size()>0) {
				lResponse.setErrorCode(ApiUtil.PARTIAL_SUCCESS);
				lResponse.setDescription("Student Result Insertion partially Success..");
			}
			else if(lSuccess.size()>0) {
				lResponse.setErrorCode(ApiUtil.SUCCESS);
				lResponse.setDescription("Student Result Inserted Successfully..");
			}else {
				lResponse.setErrorCode(ApiUtil.FAILURE);
				lResponse.setDescription("Student Result Insertion Failed..");
			}
			
			lInsertResultResponse.setSuccessResult(lSuccess);
			lInsertResultResponse.setFailedResult(lFailure);
			lInsertResultResponse.setResponse(lResponse);
			
			return lInsertResultResponse;
		}
		lResponse.setStatus(ApiUtil.STATUS_FAILED);
		lResponse.setErrorCode(ApiUtil.INVALID_RESULT_PARAM);
		lResponse.setDescription("Invalid Result Param..!");
		
		lInsertResultResponse.setResponse(lResponse);
		return lInsertResultResponse;
	}

	
	public GetStudentResultResponse processGetStudentResultRequest(GetStudentResultRequest lStudentResultRequest) {
		GetStudentResultResponse lGetStudentResultResponse 	= new GetStudentResultResponse();
		Response lResponse 									= new Response();
		StudentDataStore lStudentDataStore 					= null;
		StudentInfo lStudentInfo 							= null;
		List<ExamResult> lResults 							= null;
		
		lGetStudentResultResponse.setTxnId(lStudentResultRequest.getTxnId());
		if ((lStudentDataStore = dataStore.get(lStudentResultRequest.getEnrollNo()))== null) {
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.STUDENT_NOT_FOUND);
			lResponse.setDescription("Student Not Found..!");
			lGetStudentResultResponse.setResponse(lResponse);
			return lGetStudentResultResponse;
		}
		
		if((lStudentInfo = lStudentDataStore.getStudentData()) == null) {
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.STUDENT_INFO_NOT_FOUND);
			lResponse.setDescription("Student Info Not Found..!");
			lGetStudentResultResponse.setResponse(lResponse);
			return lGetStudentResultResponse;
		}
		
		lResults = new ArrayList<ExamResult>(lStudentDataStore.getResults().values());
		if(lResults.size()==0) {
			lResponse.setStatus(ApiUtil.STATUS_FAILED);
			lResponse.setErrorCode(ApiUtil.NO_RESULT_AVAILABLE);
			lResponse.setDescription("No Result Available for the Student..!");
			lGetStudentResultResponse.setResponse(lResponse);
			return lGetStudentResultResponse;
		}
		
		lResponse.setStatus(ApiUtil.STATUS_SUCCESS);
		lResponse.setErrorCode(ApiUtil.SUCCESS);
		lResponse.setDescription("Student Result Fetched Successfully..");
		
		lGetStudentResultResponse.setStudentInfo(lStudentInfo);
		lGetStudentResultResponse.setResult(lResults);
		lGetStudentResultResponse.setResponse(lResponse);
		return lGetStudentResultResponse;
	}

}
