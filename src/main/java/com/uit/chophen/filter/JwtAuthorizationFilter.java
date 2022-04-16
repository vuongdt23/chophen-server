package com.uit.chophen.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.uit.chophen.utils.SecurityConstant.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.uit.chophen.utils.JWTTokenProvider;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
			response.setStatus(HttpStatus.OK.value());
		} else {
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

			// If the auth header is null or doesn't contain a bearer token then return
			if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
				filterChain.doFilter(request, response);
				return;
			}

			// Extracting the token
			String token = authorizationHeader.substring(TOKEN_PREFIX.length());

			// Extracting the username
			String username = jwtTokenProvider.getSubjectFromToken(token);

			if (jwtTokenProvider.isTokenValid(username, token)
					&& SecurityContextHolder.getContext().getAuthentication() == null) {

				// extract the authorities
				List<GrantedAuthority> authorities = jwtTokenProvider.getAuthoritiesFromToken(token);

				Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);

				// set the authentication
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				// clear the auth context
				SecurityContextHolder.clearContext();
			}
		}
		filterChain.doFilter(request, response);
	}

}
