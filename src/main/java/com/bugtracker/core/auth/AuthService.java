package com.bugtracker.core.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bugtracker.core.user.User;
import com.bugtracker.core.user.UserService;
import com.bugtracker.core.user.UserUtils;
import com.bugtracker.utils.JwtTokenUtil;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	public AuthenticationResponse login(AuthenticationRequest request) {
		String username = request.getUsername();
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));

		Optional<User> u = userService.findByUsername(username);
		if (u.isEmpty()) {
			return null;
		}

		UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
		return generateResponse(u.get(), userDetails);
	}

	public AuthenticationResponse refreshToken(String refreshToken) {
		if (!jwtTokenUtil.isRefreshToken(refreshToken) || jwtTokenUtil.isTokenExpired(refreshToken)) {
			return null;
		}

		String username = jwtTokenUtil.getUsername(refreshToken);
		Optional<User> u = userService.findByUsername(username);
		if (u.isEmpty()) {
			return null;
		}

		User user = u.get();
		UserDetails userDetails = UserUtils.createUserDetails(user);
		return generateResponse(user, userDetails);
	}

	private AuthenticationResponse generateResponse(User user, UserDetails userDetails) {
		return new AuthenticationResponse(user, jwtTokenUtil.generateAuthenticationToken(userDetails),
				jwtTokenUtil.generateRefreshToken(userDetails));
	}
}
