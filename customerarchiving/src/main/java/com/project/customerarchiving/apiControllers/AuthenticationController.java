package com.project.customerarchiving.apiControllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.customerarchiving.requests.UserRequest;
import com.project.customerarchiving.security.JwtTokenGenerator;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenGenerator jwtTokenGenerator;
	
	//token for login
	@PostMapping("/login")
	public String login(@RequestBody UserRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = jwtTokenGenerator.generateJwtToken(auth);
		return jwtToken;
	}
	
}
