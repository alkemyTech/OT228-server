package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.security.jwt.JwtUtils;
import com.alkemy.ong.service.IUsersService;
import com.alkemy.ong.util.MessageHandler;
import com.amazonaws.services.macie2.model.transform.SimpleScopeTermMarshaller;
import com.auth0.jwt.JWT;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private UsersRspository usersRepository;
    @Autowired
    private MessageHandler messageHandler;

    @Override
    public boolean partialUpdate(Map<String, Object> partialUpdate, Long usersId) {

        return findById(usersId).map(users -> {
            partialUpdate.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(users.getClass(), key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, users, value);
            });
            usersRepository.save(users);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<Users> findById(Long usersId) {
        return usersRepository.findById(usersId);
    }

    @Override
    public List<UserDto> findAll() {
        return usersRepository.findAll()
                .stream()
                .map(u -> ModelMapperFacade.map(u, UserDto.class)).collect(Collectors.toList());
    }

    public void deleteByTokenOrId(Long id, String token) {
        Optional<Users> usersOptional = usersRepository.findById(id);
        usersOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, messageHandler.usersNotFound));
        Users usersById = usersRepository.getById(id);

        if (
                usersById.getEmail().equals(JwtUtils.getUsername(token)) ||
                        usersById.getRole().getName().equals("ADMIN")) {

            usersRepository.delete(usersById);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, messageHandler.userUnauthorized);
        }
    }

}
