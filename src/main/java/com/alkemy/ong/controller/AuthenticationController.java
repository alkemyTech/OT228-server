package com.alkemy.ong.controller;

import java.util.HashMap;
import java.util.Map;

import com.alkemy.ong.dto.AuthenticactionAuthDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.ong.dto.AuthenticationRequestDto;
import com.alkemy.ong.security.jwt.JwtUtils;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(AuthenticationController.AUTH)
public class AuthenticationController {

    protected static final String AUTH = "/auth";
    private static final String LOGIN = "/login";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(AUTH)
    public ResponseEntity<?> auth(@RequestBody AuthenticactionAuthDto newUser) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A user with this email already exists");
        } catch (UsernameNotFoundException e) {
            Users user = new Users(); //Mapper with newUser
            userDetailsService.save(user);
            return new ResponseEntity<>("Usuario creado", HttpStatus.CREATED);
        }
    }

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


}
