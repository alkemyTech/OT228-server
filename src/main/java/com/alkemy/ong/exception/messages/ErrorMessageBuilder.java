package com.alkemy.ong.exception.messages;

import org.springframework.http.HttpStatus;

public interface ErrorMessageBuilder {
	
	interface HttpStatusType {
		ErrorText httpStatus(HttpStatus httpStatus);
	}
	
	interface ErrorText {
		RequestURL errorText(String errorText);
	}
	
	interface RequestURL {
		Optionals requestURL(String requestURL);
	}
	
	interface Optionals {
		ErrorMessage build();
	}

}
