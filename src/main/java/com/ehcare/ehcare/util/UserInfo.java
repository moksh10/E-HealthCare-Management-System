package com.ehcare.ehcare.util;

public class UserInfo {
	private int userID;
	private String role;

	public UserInfo(int userID, String role) {
		this.userID = userID;
		this.role = role;
	}

	public UserInfo() {
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
