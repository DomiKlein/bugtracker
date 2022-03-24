package com.bugtracker.utils;

import java.util.Date;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenUtil {

	private final String signingKey;

	private final static Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

	@Autowired
	public JwtTokenUtil(Environment env) {
		String signingKey = System.getProperty("jwt.key");
		if (signingKey == null) {
			LOGGER.warn(
					"Using standard private key to sign JWT tokens. Consider to pass it your own one in via system property 'jwt.key'");
			signingKey = env.getProperty("klein.bugtracker.jwt.key");
		}
		this.signingKey = signingKey;
	}

	/** Generates a JWT token for the given user. */
	public String generateAuthenticationToken(UserDetails user) {
		return generateToken(user, 1, false);
	}

	/** Generates a JWT token for the given user. */
	public String generateRefreshToken(UserDetails user) {
		return generateToken(user, 1440, true); // expires after 1 day
	}

	private String generateToken(UserDetails user, int minutes, boolean isRefreshToken) {
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		claims.put("roles", user.getAuthorities());
		claims.put("refreshToken", isRefreshToken);

		return Jwts.builder() //
				.setClaims(claims) //
				.setIssuer("Dominik Klein")//
				.setIssuedAt(new Date(System.currentTimeMillis())) //
				.setExpiration(expiresAfterMinutes(minutes)) //
				.signWith(SignatureAlgorithm.HS256, signingKey) //
				.compact();
	}

	private static Date expiresAfterMinutes(long minutes) {
		long expirationDuration = minutes * 60 * 1000;
		return new Date(System.currentTimeMillis() + expirationDuration);
	}

	/** Extracts the username from a token. */
	public String getUsername(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	/** Extracts the expiration date from a token. */
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	/** Extracts the information whether the token is a refresh token or not. */
	public boolean isRefreshToken(String token) {
		return (boolean) getClaimFromToken(token, c -> c.get("refreshToken"));
	}

	/** Returns a claim from a token. */
	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	/** Extracts all claims from a token . */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser() //
				.setSigningKey(signingKey) //
				.parseClaimsJws(token) //
				.getBody();
	}

	/** Returns whether the token is expired or not. */
	public boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/** Returns whether a token is valid. */
	public boolean validate(String token, UserDetails userDetails) {
		final String username = getUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
