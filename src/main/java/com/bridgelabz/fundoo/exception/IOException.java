package com.bridgelabz.fundoo.exception;

@SuppressWarnings("serial")
public class IOException extends java.io.IOException{
	
    String massage;

	public IOException() {
		super();
	}
	
	public IOException(String massage) {
		super(massage);
		this.massage = massage;
	}
    
}
