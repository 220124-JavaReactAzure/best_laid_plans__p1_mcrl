package com.revature.wedding_planner.models;

public class UserTypes {
	private int id;
	private String userType;
	
	public UserTypes() {
		super();
	}

	public UserTypes(int id, String userType) {
		super();
		this.id = id;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
}
