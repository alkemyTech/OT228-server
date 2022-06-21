package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<UserDto> getAllUsers();
}
