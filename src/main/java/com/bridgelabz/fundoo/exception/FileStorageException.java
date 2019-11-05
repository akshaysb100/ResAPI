package com.bridgelabz.fundoo.exception;

@SuppressWarnings("serial")
public class FileStorageException extends RuntimeException {

	String massage;

	public FileStorageException() {
		super();
	}

	public FileStorageException(String massage) {
		super(massage);
		
	}
	
}
