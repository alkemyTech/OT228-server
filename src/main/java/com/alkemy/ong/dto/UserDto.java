package com.alkemy.ong.dto;

import lombok.Data;

import javax.management.relation.Role;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private Role role;

}
