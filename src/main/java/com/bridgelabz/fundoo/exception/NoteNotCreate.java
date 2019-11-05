package com.bridgelabz.fundoo.exception;

@SuppressWarnings("serial")
public class NoteNotCreate extends RuntimeException {

	String massage;
	
	public NoteNotCreate() {
		super();
	}

	public NoteNotCreate(String massage) {
		super(massage);

	}
	
	
}
