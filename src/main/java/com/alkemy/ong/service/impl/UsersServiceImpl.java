package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.security.jwt.JwtUtils;
import com.alkemy.ong.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private UsersRspository usersRepository;
    @Autowired
    private HttpServletRequest request;

    private static final String AUTHORIZATION= "Authorization";

    @Override
    public boolean partialUpdate(Map<String, Object> partialUpdate,  Long usersId) {

        return findById(usersId).map(users -> {
            partialUpdate.forEach((key,value) ->{
                Field field = ReflectionUtils.findField(users.getClass(),key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field,users,value);
            });
            usersRepository.save(users);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<Users> findById(Long usersId) {
        return usersRepository.findById(usersId);
    }


    public UserDto myInfo() {
        String token = request.getHeader(AUTHORIZATION);
        System.out.println(token);
        if (token != null && token.startsWith("Bearer ")){
            String jwt = token.substring(7);
            String username = JwtUtils.getUsername(jwt);
            System.out.println(username);
            return ModelMapperFacade.map(usersRepository.findByEmail(username), UserDto.class);
        }
        throw new BadRequestException("Error");
    }
}
