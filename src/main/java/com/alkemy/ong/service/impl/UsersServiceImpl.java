package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private UsersRspository usersRepository;

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

}
