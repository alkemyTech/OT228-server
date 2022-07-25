package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUsersService {

	boolean partialUpdate(Map<String, Object> partialUpdate, Long usersId);

	Optional<Users> findById(Long usersId);

	List<UserDto> findAll();

	void deleteByTokenOrId(Long usersId, String bearerToken);
}
