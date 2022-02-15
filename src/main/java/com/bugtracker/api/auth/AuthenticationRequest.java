package com.bugtracker.api.auth;

/** Data object which holds information regarding an authentication. */
public class AuthenticationRequest {

	private final String username;

	private final String password;

	AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
