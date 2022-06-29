package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.AuthenticactionAuthDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UsersRspository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRspository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users u = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found."));
        return User.builder()
                .username(u.getEmail())
                .password(u.getPassword())
                .authorities(u.getRole().getName())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public UserDto save(AuthenticactionAuthDto newUser) {
        Users user = userMapper.userDto2UserEntity(newUser);
        userRepository.save(user);
        return userMapper.userEntity2UserDto(user);
    }


}
