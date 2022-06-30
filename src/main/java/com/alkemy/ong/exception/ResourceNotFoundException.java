package com.alkemy.ong.exception;

import com.alkemy.ong.util.MessageHandler;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 2L;
    private static MessageHandler messageHandler;

    public ResourceNotFoundException(String errorDetails) {
        super( messageHandler.resourceNotFound + " " + errorDetails);
    }

}