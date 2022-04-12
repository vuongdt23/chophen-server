package com.uit.chophen.utils;


import com.uit.chophen.security.UserPrincipal;
import static com.uit.chophen.utils.SecurityConstant.*;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;

class JWTTokenProvider {

	@Value("jwt.secret")
	private String secret;

	public String generateJwtToken(UserPrincipal userPrincipal) {
		String[] claims = getClaimsFromUser(userPrincipal);
		return JWT.create().withIssuer(GET_ARRAYS_LLC).withAudience(GET_ARRAYS_ADMINISTRATION).withIssuedAt(new Date())
				.withSubject(userPrincipal.getUsername()).withArrayClaim(AUTHORITIES, claims)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(secret.getBytes()));
	}

	private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
		return null;
	}
}