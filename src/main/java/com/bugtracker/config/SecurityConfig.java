package com.bugtracker.config;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bugtracker.config.filter.JwtTokenFilter;
import com.bugtracker.config.filter.StaticResourcesFilter;
import com.bugtracker.config.filter.StaticResourcesInfo;
import com.bugtracker.core.user.User;
import com.bugtracker.core.user.UserUtils;
import com.bugtracker.core.user.UsersRepository;
import com.bugtracker.utils.JwtTokenUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

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
				.authorizeRequests() //
				.antMatchers("/api/auth/**").permitAll()
				// All others authenticated
				.anyRequest().authenticated() //
				.and()
				// Add filters
				.addFilterBefore(new StaticResourcesFilter(new StaticResourcesInfo(this.getClass().getClassLoader())),
						UsernamePasswordAuthenticationFilter.class) //
				.addFilterBefore(new JwtTokenFilter(jwtTokenUtil, usersRepository),
						UsernamePasswordAuthenticationFilter.class);

	}

	/** Configure the authentication manager. */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			Optional<User> user = usersRepository.findByUsername(username);
			if (user.isEmpty()) {
				throw new UsernameNotFoundException(String.format("User: '%s' not found", username));
			}
			return UserUtils.createUserDetails(user.get());
		});
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

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(List.of("*"));
		config.setAllowedMethods(List.of("HEAD", "GET", "PUT", "POST", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("Content-Type", "Authorization", "x-requested-with"));
		config.setMaxAge(900L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}
