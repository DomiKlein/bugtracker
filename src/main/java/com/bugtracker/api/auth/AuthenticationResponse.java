package com.bugtracker.api.auth;

import com.bugtracker.database.model.util.ExportToTypeScript;

@ExportToTypeScript
public class AuthenticationResponse {

	private final String authenticationToken;

	private final String refreshToken;

	public AuthenticationResponse(String authenticationToken, String refreshToken) {
		this.authenticationToken = authenticationToken;
		this.refreshToken = refreshToken;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}
}
