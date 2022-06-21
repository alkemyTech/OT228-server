package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor

public class UserServiceImpl implements IUserService {

    private UsersRspository usersRspository;
    private ObjectMapper objectMapper;


    @Override
    public List<UserDto> getAllUsers() {
        List<User>userList=usersRspository.findAll();
        List<UserDto>userDtoList=entityListToDtoList(userList);
        return userDtoList;
    }

    public User dtoToEntity(UserDto userDto){
        User user= new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoto(userDto.getPhoto());
        user.setRole(userDto.getRole());
        return user;
    }
    public UserDto entityToDto(User user){
        UserDto userDto =new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setPhoto(user.getPhoto());
        userDto.setRole(user.getRole());
        return userDto;
    }
    public List<UserDto>entityListToDtoList(List<User>userList){
        List<UserDto>userDtoList= new ArrayList<>();
        for (User entity:userList) {
            userDtoList.add(entityToDto(entity));
        }
        return userDtoList;
    }
}
