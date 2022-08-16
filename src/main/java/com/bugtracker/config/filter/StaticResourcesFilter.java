package com.bugtracker.config.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/** A filter used to server static resources from the server. */
public class StaticResourcesFilter extends OncePerRequestFilter {

	private final StaticResourcesInfo staticResourcesInfo;

	public StaticResourcesFilter(StaticResourcesInfo staticResourcesInfo) {
		this.staticResourcesInfo = staticResourcesInfo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		if (!requestUri.startsWith("/api/")) {
			String requestedResource = requestUri.substring(1);
			Optional<String> forwardTo = staticResourcesInfo.forward(requestedResource);

			if (forwardTo.isPresent()) {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forwardTo.get());
				dispatcher.forward(request, response);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}
