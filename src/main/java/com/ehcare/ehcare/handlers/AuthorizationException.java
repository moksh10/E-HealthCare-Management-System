package com.ehcare.ehcare.handlers;

@SuppressWarnings("serial")
public class AuthorizationException extends RuntimeException {

	public AuthorizationException() {
		super("Authorization failed");
	}
}
