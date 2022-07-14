package com.alkemy.ong.dto;

import com.alkemy.ong.model.Organization;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SlideDto {

	private Long id;
	private String imageUrl;
	
	@JsonInclude(Include.NON_NULL)
	private String text;
	
	private Integer order;

	@JsonInclude(Include.NON_NULL)
	@JsonBackReference
	private Organization organization;

}
