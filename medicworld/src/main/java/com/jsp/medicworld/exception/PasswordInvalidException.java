package com.jsp.medicworld.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PasswordInvalidException extends RuntimeException{
	
	String inavildPassword;

	public PasswordInvalidException(String inavildPassword) {
		super(inavildPassword);
		this.inavildPassword = inavildPassword;
	}
	

}