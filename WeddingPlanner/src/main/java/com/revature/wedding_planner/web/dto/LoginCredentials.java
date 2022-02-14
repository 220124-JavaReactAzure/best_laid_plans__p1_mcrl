package com.revature.wedding_planner.web.dto;

import java.util.Objects;

public class LoginCredentials {

	private String username;
	private String password;
	
	// JACKSON NEEEDS NO ARG CONSTRUCTOR IN ORDER TO PARSE JSON INTO POJOs
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginCredentials other = (LoginCredentials) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
}