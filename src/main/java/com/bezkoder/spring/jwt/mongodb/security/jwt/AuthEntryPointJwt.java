package com.bezkoder.spring.jwt.mongodb.security.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import com.bezkoder.spring.jwt.mongodb.response.APIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		logger.error("Unauthorized error: {}", authException.getMessage());
		APIResponse responseBody = APIResponse.newFailureResponse(HttpStatus.UNAUTHORIZED.value(), "Invalid request");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(responseBody);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(json);
		out.flush();
	}
}
