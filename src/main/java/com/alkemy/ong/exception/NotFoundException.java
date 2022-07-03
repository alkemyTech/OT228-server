package com.alkemy.ong.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String ERROR_TYPE = "Error (404) Not Found Exception.";

	public NotFoundException() {
		// For Spring framework
		super(ERROR_TYPE);
	}

	public NotFoundException(String errorDetails) {
		super(ERROR_TYPE + " " + errorDetails);
	}

}
