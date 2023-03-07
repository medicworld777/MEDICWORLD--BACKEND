package com.jsp.medicworld.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserNotFoundException extends RuntimeException {

	String exception;

	public UserNotFoundException(String exception) {
		super(exception);
		this.exception = exception;
	}

}
