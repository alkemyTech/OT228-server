package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;



@Getter
@Setter
public class ActivityDto {

    private Long id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Content can not be empty")
    private String content;
    private String image;
}
