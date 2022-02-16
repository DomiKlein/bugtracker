package com.bugtracker.api.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Data object which holds information regarding an authentication. */
public class AuthenticationRequest {

	private final String username;

	private final String password;

	@JsonCreator
	public AuthenticationRequest(@JsonProperty(value = "username") String username,
			@JsonProperty(value = "password") String password) {
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
