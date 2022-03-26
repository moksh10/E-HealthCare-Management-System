package com.ehcare.ehcare.handlers;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends RuntimeException{

	public UserAlreadyExistsException() {
		super("User of this email already exists");
	}
}
