package com.alkemy.ong.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String ERROR_TYPE = "Error (400) Bad Request Exception.";
	
	public BadRequestException(String errorDetails) {
		super(ERROR_TYPE + " " + errorDetails);
	}

}
