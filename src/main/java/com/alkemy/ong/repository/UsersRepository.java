package com.alkemy.ong.repository;

import com.alkemy.ong.model.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

	Optional<Users> findByEmail(String email);

}
