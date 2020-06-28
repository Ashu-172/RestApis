package com.mishra.api.BasicApi04.response;

import java.util.List;

import com.mishra.api.BasicApi04.structs.FailedResult;
import com.mishra.api.BasicApi04.structs.Response;
import com.mishra.api.BasicApi04.structs.SubjectScore;

public class InsertResultResponse {
	String txnId;
	Response response;
	List<SubjectScore> successResult;
	List<FailedResult> failedResult;
	
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	
	public void setSuccessResult(List<SubjectScore> successResult) {
		this.successResult = successResult;
	}

	public void setFailedResult(List<FailedResult> failedResult) {
		this.failedResult = failedResult;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getTxnId() {
		return txnId;
	}

	public Response getResponse() {
		return response;
	}

	public List<SubjectScore> getSuccessResult() {
		return successResult;
	}

	public List<FailedResult> getFailedResult() {
		return failedResult;
	}
	
}
