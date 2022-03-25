package com.ehcare.ehcare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseSuccess {

	private String message;
	private boolean success;
	@JsonInclude(Include.NON_NULL)
	private Object data;

	public ResponseSuccess(String message,boolean success) {
		this.message = message;
		this.success = success;
	}
	public ResponseSuccess(String message,boolean success, Object data) {
		this.message = message;
		this.success = success;
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
