package com.alkemy.ong.exception;

import com.alkemy.ong.util.MessageHandler;

public class PermissionDeniedException extends RuntimeException{
    private static final long serialVersionUID = 2L;
    private static MessageHandler messageHandler;

    public PermissionDeniedException() {
        super( messageHandler.resourceNotFound);
    }
}
