package com.alkemy.ong.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto {
	
	private Long id;

	@NotNull(message = "Name must not be null")
	private String name;

	@NotNull(message = "Phone must not be null")
	private String phone;

	@Email
	@NotNull(message = "Email must not be null")
	private String email;

	private String message;

}
