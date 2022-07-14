package com.alkemy.ong.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

	private Long id;

	@NotNull(message = "Name must not be null.")
	private String name;

	private String facebookUrl;
	private String instagramUrl;
	private String linkedinUrl;

	@NotNull(message = "Image must not be null.")
	private String image;

	private String description;

}