package com.alkemy.ong.security.jwt;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
@PropertySource("classpath:application.properties")
public class JwtUtils {

	public static final String TOKEN_PREFIX = "Bearer ";
	private static String SECRET_KEY;
	private static int EXPIRATION_TIME;
	
	@Value("${jwt-secret}")
	public void setSecretKey(String secretKey) {
	    JwtUtils.SECRET_KEY = secretKey;
	}
	
	@Value("${jwt-expiration}")
	public void setExpirationTime(String expirationTime) {
		JwtUtils.EXPIRATION_TIME = Integer.parseInt(expirationTime);
	}
	
	public static void validate(String token) throws JWTVerificationException {
		JWT.require(Algorithm.HMAC512(SECRET_KEY)).build()
				.verify(token);
	}
	
	public static String generateAccessToken(UserDetails userDetails) {
		return TOKEN_PREFIX +
				JWT.create()
						.withSubject(userDetails.getUsername())
						.withClaim("role", userDetails.getAuthorities().stream()
								.map(authority -> authority.getAuthority())
								.toList())
						.withIssuedAt(Date.from(Instant.now()))
						.withExpiresAt(Date.from(Instant.now().plus(Duration.ofMinutes(EXPIRATION_TIME))))
						.sign(Algorithm.HMAC512(SECRET_KEY));
	}

	public static String getUsername(String token) {
		return JWT.decode(token).getSubject();
	}

	public static String getRole(String token) {
		return JWT.decode(token).getClaim("role").asString();
	}

}
