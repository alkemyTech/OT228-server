package com.alkemy.ong.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.alkemy.ong.dto.AuthenticactionAuthDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.service.impl.UserDetailsServiceImpl;
import com.alkemy.ong.util.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.ong.dto.AuthenticationRequestDto;
import com.alkemy.ong.security.jwt.JwtUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Controller
@RequestMapping(AuthenticationController.AUTH)
public class AuthenticationController {

    protected static final String AUTH = "/auth";
    private static final String LOGIN = "/login";

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UsersRspository usersRspository;
    private final MessageHandler header;

    @Autowired
    public AuthenticationController(MessageHandler header, UserDetailsServiceImpl userDetailsService,
                                    UsersRspository usersRspository, AuthenticationManager authenticationManager) {
        this.header = header;
        this.userDetailsService = userDetailsService;
        this.usersRspository = usersRspository;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping()
    public ResponseEntity<UserDto> auth(@Valid @RequestBody AuthenticactionAuthDto newUser) {
        Optional<Users> optionalUser = usersRspository.findByEmail(newUser.getEmail());
        if (optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, header.userFound);
        }
        UserDto users = userDetailsService.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(users);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequestDto request) {
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
