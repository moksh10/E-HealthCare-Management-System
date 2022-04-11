package com.ehcare.ehcare.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
public class AuthenticationRequest implements Serializable {

	@NotEmpty(message = "Email required")
	@Email(message = "Not a valid email")
	private String email;

	@NotEmpty(message = "Password required")
	private String password;

	@NotEmpty(message = "Role required")
	private String role;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AuthenticationRequest() {

	}

	public AuthenticationRequest(String email, String password, String role) {
		this.setEmail(email);
		this.setPassword(password);
		this.setRole(role);
	}
}