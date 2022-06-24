package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class AuthenticactionAuthDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;

    @NotBlank
    private String password;

    private String photo;
}
