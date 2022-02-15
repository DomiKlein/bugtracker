package com.bugtracker.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private StaticResourcesInfo staticResourcesInfo;

	@Autowired
	private UsersRepository usersRepository;

	/** Configure http security. */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// Enable CORS and disable CSRF
				.cors().and().csrf().disable()
				// Set session management to stateless
				.sessionManagement() //
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //
				.and()
				// Set unauthorized requests exception handler
				.exceptionHandling() //
				.authenticationEntryPoint((request, response, ex) -> response
						.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage()))
				.and()
				// Configure endpoints authentication
				.authorizeRequests()
				// Login requests should always be permitted
				.antMatchers("/api/auth/**").permitAll()
				// All others authenticated
				// .anyRequest().authenticated() // TODO
				.and()
				// Add filters
				.addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class) //
				.addFilterBefore(new StaticResourcesFilter(staticResourcesInfo),
						UsernamePasswordAuthenticationFilter.class);
	}

	/** Configure web security. */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Prevents "cannot execute" errors in UI
		web.ignoring().antMatchers("/bundle.js");
	}

	/** Configure the authentication manager. */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> usersRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User: '%s' not found", username))));
	}

	/**
	 * Password encoder used to encode passwords and store them in the database.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/** Authentication manager to perform an authentication. */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
