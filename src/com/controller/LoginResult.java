package com.controller;

import java.util.List;

import com.domain.Role;
import com.domain.User;

public class LoginResult {
	private List<Role> currentUserRole;
	private String token;
	private User user;
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Role> getCurrentUserRole() {
		return currentUserRole;
	}
	public void setCurrentUserRole(List<Role> currentUserRole) {
		this.currentUserRole = currentUserRole;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
