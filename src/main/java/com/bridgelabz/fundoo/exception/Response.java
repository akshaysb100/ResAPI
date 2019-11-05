package com.bridgelabz.fundoo.exception;

import java.time.LocalDateTime;

public class Response {

	private LocalDateTime dateTime;
	private int Status;
	private String massage;
	public Response(LocalDateTime dateTime, int status, String massage) {
		super();
		this.dateTime = dateTime;
		Status = status;
		this.massage = massage;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
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
		return "Response [dateTime=" + dateTime + ", Status=" + Status + ", massage=" + massage + "]";
	}
	
	
}
