package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.AuthenticactionAuthDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users userDto2UserEntity(AuthenticactionAuthDto authDto) {
        Users users = new Users();
        users.setEmail(authDto.getEmail());
        users.setFirstName(authDto.getFirstName());
        users.setLastName(authDto.getLastName());
        users.setPassword(passwordEncoder.encode(authDto.getPassword()));
        users.setPhoto(authDto.getPhoto());
        users.setRole(roleRepository.findByName("USER"));
        return users;
    }

    public UserDto userEntity2UserDto(Users users) {
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
