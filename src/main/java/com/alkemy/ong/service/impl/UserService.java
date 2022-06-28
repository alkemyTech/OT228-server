package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


public class UserService {

    @Autowired
    private UsersRspository usersRspository;
    @Autowired
    private HttpServletRequest httpRequest;


    public UserDto myInfo(){
        String token = httpRequest.getHeader("Authorization");
        String userName = JwtUtils.getUsername(token);
        return ModelMapperFacade.map(usersRspository.findByEmail(userName), UserDto.class);
    }

}
