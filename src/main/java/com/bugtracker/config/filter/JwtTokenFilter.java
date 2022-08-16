package com.bugtracker.config.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bugtracker.core.user.User;
import com.bugtracker.core.user.UserUtils;
import com.bugtracker.core.user.UsersRepository;
import com.bugtracker.utils.JwtTokenUtil;

public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenUtil jwtTokenUtil;
	private final UsersRepository usersRepository;

	public JwtTokenFilter(JwtTokenUtil jwtTokenUtil, UsersRepository usersRepository) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.usersRepository = usersRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// Get authorization header and validate
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}

		// Get jwt token and validate
		final String token = header.split(" ")[1].trim();

		// Validate token
		User user = usersRepository.findByUsername(jwtTokenUtil.getUsername(token)).orElse(null);
		if (user == null) {
			chain.doFilter(request, response);
			return;
		}

		UserDetails userDetails = UserUtils.createUserDetails(user);
		if (!jwtTokenUtil.validate(token, userDetails) || jwtTokenUtil.isRefreshToken(token)) {
			chain.doFilter(request, response);
			return;
		}

		// Set authentication context
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
				userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(request, response);
	}

}
