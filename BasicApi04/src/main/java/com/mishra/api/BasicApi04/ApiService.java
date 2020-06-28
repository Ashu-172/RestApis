package com.mishra.api.BasicApi04;

import com.mishra.api.BasicApi04.request.AddStudentRequest;
import com.mishra.api.BasicApi04.request.GetClassStudentsRequest;
import com.mishra.api.BasicApi04.request.GetStudentRequest;
import com.mishra.api.BasicApi04.request.GetStudentResultRequest;
import com.mishra.api.BasicApi04.request.InsertResultRequest;
import com.mishra.api.BasicApi04.response.AddStudentResponse;
import com.mishra.api.BasicApi04.response.GetClassStudentsResponse;
import com.mishra.api.BasicApi04.response.GetStudentResponse;
import com.mishra.api.BasicApi04.response.GetStudentResultResponse;
import com.mishra.api.BasicApi04.response.InsertResultResponse;
import com.mishra.api.BasicApi04.structs.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiService {

	ServiceProcessor mServiceProcessor = new ServiceProcessor();

	public String addStudent(spark.Request request, spark.Response response) {
		AddStudentRequest lAddStudentRequest = null;
		try {
			response.type("application/json");
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			lAddStudentRequest = objMapper.readValue(request.body(), AddStudentRequest.class);

			AddStudentResponse lAddStudentResponse = mServiceProcessor.processAddStudentRequest(lAddStudentRequest);
			return ApiUtil.dataToJson(lAddStudentResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return getErrorResponse("Invalid Request Param..!");
		}catch(Exception e) {
			e.printStackTrace();
			return getErrorResponse("System Error..!");
		}
	}

	public String getStudent(spark.Request request, spark.Response response) {
		GetStudentRequest lGetStudentRequest = null;
		try {
			response.type("application/json");
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

			lGetStudentRequest = objMapper.readValue(request.body(), GetStudentRequest.class);

			GetStudentResponse lGetStudentResponse = mServiceProcessor.processGetStudentRequest(lGetStudentRequest);
			return ApiUtil.dataToJson(lGetStudentResponse);
		} catch (Exception e) {
			// Logs to be Added
			e.printStackTrace();
			return getErrorResponse(e.getMessage());
		}
	}

	public String getClassStudents(spark.Request request, spark.Response response) {
		GetClassStudentsRequest lGetClassStudentsRequest = null;
		try {
			response.type("application/json");
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

			lGetClassStudentsRequest = objMapper.readValue(request.body(), GetClassStudentsRequest.class);

			GetClassStudentsResponse lGetClassStudentsResponse = mServiceProcessor.processGetClassStudentRequest(lGetClassStudentsRequest);

			return ApiUtil.dataToJson(lGetClassStudentsResponse);
		} catch (Exception e) {
			e.printStackTrace();
			return getErrorResponse(e.getMessage());
		}
	}

	public String addSubjectResult(spark.Request request, spark.Response response) {
		InsertResultRequest lInsertResultRequest = null;
		try {
			response.type("application/json");
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			
			lInsertResultRequest = objMapper.readValue(request.body(), InsertResultRequest.class);
			
			InsertResultResponse lInsertResultResponse = mServiceProcessor.processInsertResultRequest(lInsertResultRequest);
			
			return ApiUtil.dataToJson(lInsertResultResponse);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return getErrorResponse(e.getMessage());
		}
	}

	public String getResult(spark.Request request, spark.Response response) {
		GetStudentResultRequest lStudentResultRequest = null;
		try {
			response.type("application/json");
			ObjectMapper objMapper = new ObjectMapper();
			objMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
			
			lStudentResultRequest = objMapper.readValue(request.body(), GetStudentResultRequest.class);
			
			GetStudentResultResponse lStudentResultResponse = mServiceProcessor.processGetStudentResultRequest(lStudentResultRequest);
			
			return ApiUtil.dataToJson(lStudentResultResponse);
		}
		catch(Exception e) {
			e.printStackTrace();
			return getErrorResponse(e.getMessage());
		}
	}

	private String getErrorResponse(String message) {
		Response lResponse = new Response();
		lResponse.setStatus(ApiUtil.STATUS_SUCCESS);
		lResponse.setErrorCode(ApiUtil.SYSTEM_ERROR);
		lResponse.setDescription(message);
		return ApiUtil.dataToJson(lResponse);
	}
}
