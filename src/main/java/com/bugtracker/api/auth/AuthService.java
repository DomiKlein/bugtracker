package com.bugtracker.api.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bugtracker.api.users.UserService;
import com.bugtracker.database.model.User;
import com.bugtracker.utils.JwtTokenUtil;
import com.bugtracker.utils.UserUtils;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
		try {
			String username = request.getUsername();
			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));

			Optional<User> u = userService.findByUsername(username);
			if (u.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

			UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
			return ResponseEntity.ok().body(generateResponse(u.get(), userDetails));
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	public ResponseEntity<AuthenticationResponse> refreshToken(String refreshToken) {
		if (!jwtTokenUtil.isRefreshToken(refreshToken) || jwtTokenUtil.isTokenExpired(refreshToken)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		String username = jwtTokenUtil.getUsername(refreshToken);
		Optional<User> u = userService.findByUsername(username);
		if (u.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		User user = u.get();
		UserDetails userDetails = UserUtils.createUserDetails(user);
		return ResponseEntity.ok().body(generateResponse(user, userDetails));
	}

	private AuthenticationResponse generateResponse(User user, UserDetails userDetails) {
		return new AuthenticationResponse(user, jwtTokenUtil.generateAuthenticationToken(userDetails),
				jwtTokenUtil.generateRefreshToken(userDetails));
	}
}
