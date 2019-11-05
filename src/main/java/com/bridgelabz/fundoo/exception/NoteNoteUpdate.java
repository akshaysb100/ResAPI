package com.bridgelabz.fundoo.exception;


@SuppressWarnings("serial")
public class NoteNoteUpdate extends RuntimeException{
 
	String massage;
	
	public NoteNoteUpdate() {
		super();
	}
	
	public NoteNoteUpdate(String massage) {
		super(massage);
		this.massage = massage;
	}
	
}
