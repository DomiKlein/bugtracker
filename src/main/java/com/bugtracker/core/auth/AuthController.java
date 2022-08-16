package com.bugtracker.core.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		return authService.login(request);
	}

	@PostMapping("/refresh")
	@ResponseBody
	public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody String refreshToken) {
		return authService.refreshToken(refreshToken);
	}
}
