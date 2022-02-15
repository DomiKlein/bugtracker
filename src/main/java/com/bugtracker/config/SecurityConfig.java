package com.bugtracker.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bugtracker.database.repository.UsersRepository;
import com.bugtracker.filter.JwtTokenFilter;
import com.bugtracker.filter.StaticResourcesFilter;
import com.bugtracker.utils.StaticResourcesInfo;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private StaticResourcesInfo staticResourcesInfo;

	@Autowired
	private UsersRepository usersRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Enable CORS and disable CSRF
		http = http.cors().and().csrf().disable();

		// Set session management to stateless
		http = http.sessionManagement() //
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
				.and();

		// Set unauthorized requests exception handler
		http = http.exceptionHandling() //
				.authenticationEntryPoint((request, response, ex) -> response
						.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage()))
				.and();

		// Configure endpoints authentication
		http.authorizeRequests()
				// All others authenticated
				.anyRequest().authenticated();

		// Add filters
		http.addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(new StaticResourcesFilter(staticResourcesInfo), JwtTokenFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/bundle.js");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> usersRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User: '%s' not found", username))));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
