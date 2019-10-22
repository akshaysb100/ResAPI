package com.bridgelabz.fundoo.utility;

public class Response {

	private String massage;
	private int id;
	
	public Response(String massage, int id) {
		super();
		this.massage = massage;
		this.id = id;
	}
	
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
