package com.alkemy.ong.repository;

import com.alkemy.ong.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRspository extends JpaRepository<Users,Long> {
}
