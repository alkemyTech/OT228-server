package com.alkemy.ong.exception;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 2L;

    private static final String ERROR_TYPE = "Error (404) Resource Not Found Exception.";

    public ResourceNotFoundException(String errorDetails) {
        super(ERROR_TYPE + " " + errorDetails);
    }

}