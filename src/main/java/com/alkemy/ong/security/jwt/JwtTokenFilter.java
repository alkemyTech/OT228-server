package com.alkemy.ong.security.jwt;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Get authorization header and validate
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null || !header.startsWith(JwtUtils.TOKEN_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}
		// Get JWT token and validate
		final String token = header.replace(JwtUtils.TOKEN_PREFIX, "").trim();
		try {
			JwtUtils.validate(token);
		} catch (JWTVerificationException e) {
			filterChain.doFilter(request, response);
			return;
		}
        // Get user identity and set it on the spring security context
		UserDetails userDetails = User.builder()
				.username(JwtUtils.getUsername(token))
				.authorities(JwtUtils.getRole(token))
				.build();
		UsernamePasswordAuthenticationToken authentication =
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails == null ?
						List.of() : userDetails.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
		}

}
