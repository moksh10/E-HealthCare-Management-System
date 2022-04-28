package com.ehcare.ehcare.util;

public class UserInfo {
	private int userID;
	private String role;
	private String userName;

	public UserInfo(int userID, String role,String userName) {
		this.userID = userID;
		this.role = role;
		this.userName=userName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
