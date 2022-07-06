package com.alkemy.ong.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.exception.messages.ErrorMessage;

@ControllerAdvice
public class ExceptionHandlerController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({BadRequestException.class,
			org.springframework.web.bind.MethodArgumentNotValidException.class,
			org.springframework.web.bind.MissingRequestHeaderException.class,
			org.springframework.web.bind.MissingServletRequestParameterException.class,
			org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
			org.springframework.http.converter.HttpMessageNotReadableException.class})
	@ResponseBody
	public ErrorMessage handleBadRequestException(HttpServletRequest request, Exception exception) {
		return ErrorMessage.builder()
				.httpStatus(HttpStatus.BAD_REQUEST)
				.errorText(exception.getMessage())
				.requestURL(request.getRequestURL().toString())
				.build();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({NotFoundException.class,
						ResourceNotFoundException.class})
	@ResponseBody
	public ErrorMessage handleNotFoundException(HttpServletRequest request, Exception exception) {
		return ErrorMessage.builder()
				.httpStatus(HttpStatus.NOT_FOUND)
				.errorText(exception.getMessage())
				.requestURL(request.getRequestURL().toString())
				.build();
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ErrorMessage handleInternalServerError(HttpServletRequest request, Exception exception) {
		return ErrorMessage.builder()
				.httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
				.errorText(exception.getMessage())
				.requestURL(request.getRequestURL().toString())
				.build();
	}

}
