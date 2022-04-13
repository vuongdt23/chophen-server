package com.uit.chophen.filter;

import static com.uit.chophen.utils.SecurityConstant.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uit.chophen.utils.HttpResponse;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

	public JwtAuthenticationEntryPoint() {
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
			throws IOException {
		HttpResponse httpResponse = new HttpResponse(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN,
				HttpStatus.FORBIDDEN.getReasonPhrase().toUpperCase(), FORBIDDEN_MESSAGE);

		response.setContentType(APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.FORBIDDEN.value());
		OutputStream outputStream = response.getOutputStream();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, httpResponse);
		outputStream.flush();
	}
}
