package com.alkemy.ong.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticationRequestDto {

	@Email
	@NotNull(message = "Email must not be null.")
	private String email;

	@NotNull(message = "Password must not be null.")
	private String password;

}
