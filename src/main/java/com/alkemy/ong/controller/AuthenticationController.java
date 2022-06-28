package com.alkemy.ong.controller;

import java.util.HashMap;
import java.util.Map;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.ong.dto.AuthenticationRequestDto;
import com.alkemy.ong.security.jwt.JwtUtils;


@Controller
@RequestMapping(AuthenticationController.AUTH)
public class AuthenticationController {

	protected static final String AUTH = "/auth";
	private static final String LOGIN = "/login";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;

	
	@PostMapping(LOGIN)
	public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto request) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			User user = (User) authenticate.getPrincipal();
			return ResponseEntity.ok()
					.header(HttpHeaders.AUTHORIZATION, JwtUtils.generateAccessToken(user))
					.build();
		} catch (Exception e) {
			Map<String, Object> body = new HashMap<>();
			body.put("ok", Boolean.FALSE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		}
	}

	@GetMapping("auth/me")
	public ResponseEntity<?> myInfo(){
		try {
			UserDto userDto = userService.myInfo();
			return ResponseEntity.status(HttpStatus.OK).body(userDto);
		}catch (Exception e){
			Map<String, Object> body = new HashMap<>();
			body.put("ok", Boolean.FALSE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
		}
	}
	
}
