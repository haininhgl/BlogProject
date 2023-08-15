package com.bezkoder.spring.jwt.mongodb.controllers;

import com.bezkoder.spring.jwt.mongodb.response.APIResponse;
import com.bezkoder.spring.jwt.mongodb.service.AuthService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.jwt.mongodb.request.LoginRequest;
import com.bezkoder.spring.jwt.mongodb.request.SignupRequest;
import com.bezkoder.spring.jwt.mongodb.response.JwtResponse;


	@RestController
	@RequestMapping("/api/auth")
	public class AuthController {

		private final AuthService authService;

		public AuthController(AuthService authService) {
			this.authService = authService;
		}

		@PostMapping("/login")
		public APIResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
			JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
			return APIResponse.newSuccessResponse(jwtResponse);
		}

		@PostMapping("/signup")
		public APIResponse<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
			authService.registerUser(signUpRequest);
			return APIResponse.newSuccessResponse();
		}
	}
