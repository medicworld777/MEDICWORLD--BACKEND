package com.jsp.medicworld.exception;

public class UserAlreadyExistsException extends RuntimeException {

	String expection;

	public UserAlreadyExistsException(String expection) {
		super(expection);
		this.expection = expection;
	}
}

