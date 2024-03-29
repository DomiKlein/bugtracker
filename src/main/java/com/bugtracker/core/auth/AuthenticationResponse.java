package com.bugtracker.core.auth;

import com.bugtracker.core.user.User;
import com.bugtracker.utils.ExportToTypeScript;
import com.fasterxml.jackson.annotation.JsonProperty;

@ExportToTypeScript
public class AuthenticationResponse {

	@JsonProperty(required = true)
	private final User user;

	@JsonProperty(required = true)
	private final String authenticationToken;

	@JsonProperty(required = true)
	private final String refreshToken;

	public AuthenticationResponse(User user, String authenticationToken, String refreshToken) {
		this.user = user;
		this.authenticationToken = authenticationToken;
		this.refreshToken = refreshToken;
	}

	public User getUser() {
		return user;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
}
