package com.alkemy.ong.dto;

import com.alkemy.ong.model.Organization;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlideDto {

	private Long id;
	private String imageUrl;
	
	@JsonInclude(Include.NON_NULL)
	private String text;
	
	private Integer order;

	@JsonInclude(Include.NON_NULL)
	private Organization organization;

}
