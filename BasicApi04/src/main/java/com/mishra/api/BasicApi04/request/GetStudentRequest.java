package com.mishra.api.BasicApi04.request;

public class GetStudentRequest {
	private String txnId;
	private int	enrollNo;
	
	public String getTxnid() {
		return txnId;
	}
	public void setTxnid(String txnId) {
		this.txnId = txnId;
	}
	public int getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(int enrollNo) {
		this.enrollNo = enrollNo;
	} 
	
	
}
