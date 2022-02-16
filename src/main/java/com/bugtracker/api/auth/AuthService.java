package com.bugtracker.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bugtracker.utils.JwtTokenUtil;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
			AuthenticationResponse response = new AuthenticationResponse(
					jwtTokenUtil.generateAuthenticationToken(userDetails),
					jwtTokenUtil.generateRefreshToken(userDetails));

			return ResponseEntity.ok().body(response);
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
}
