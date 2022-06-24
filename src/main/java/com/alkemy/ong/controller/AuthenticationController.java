package com.alkemy.ong.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.alkemy.ong.dto.AuthenticactionAuthDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UsersRspository;
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

import javax.validation.Valid;

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

    @Autowired
    private UsersRspository usersRspository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping()
    public ResponseEntity<UserDto> auth(@Valid @RequestBody AuthenticactionAuthDto newUser) {
        Optional<Users> optionalUser = usersRspository.findByEmail(newUser.getEmail());
        if (optionalUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ese email ya existe");
        }
        Users users = convert(newUser);
        usersRspository.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertoDto(users));
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

    private Users convert(AuthenticactionAuthDto authDto) {
        Users users = new Users();
        users.setEmail(authDto.getEmail());
        users.setFirstName(authDto.getFirstName());
        users.setLastName(authDto.getLastName());
        users.setPassword(passwordEncoder.encode(authDto.getPassword()));
        users.setPhoto(authDto.getPhoto());
        users.setRole(roleRepository.getById(1L));
        return users;
    }

    private UserDto convertoDto(Users users) {
        UserDto userDto = new UserDto();
        userDto.setEmail(users.getEmail());
        userDto.setFirstName(users.getFirstName());
        userDto.setLastName(users.getLastName());
        userDto.setPhoto(users.getPhoto());
        userDto.setRole(users.getRole().getName());
        userDto.setCreatedAt(users.getCreatedAt());
        return userDto;
    }

}
