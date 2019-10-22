package com.bridgelabz.fundoo.exception;

public class ExceptionResponse {

	
	private int id;
	private String massage;
	
	public ExceptionResponse(int id, String massage) {
		super();
		this.id = id;
		this.massage = massage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [id=" + id + ", massage=" + massage + "]";
	}
	
	
}
