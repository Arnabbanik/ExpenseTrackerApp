package com.example.expensetracerapi.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1832860507739141970L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
