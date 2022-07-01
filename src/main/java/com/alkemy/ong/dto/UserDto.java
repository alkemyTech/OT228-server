package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private String photo;

    private String role;

    private Timestamp createdAt;

}
