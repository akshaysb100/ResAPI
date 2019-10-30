package com.bridgelabz.fundoo.exception;

public class Response {

	private int Status;
	private String massage;
	
	public Response(int status, String massage) {
		super();
		Status = status;
		this.massage = massage;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}

	@Override
	public String toString() {
		return "Response [Status=" + Status + ", massage=" + massage + "]";
	}
		
}
