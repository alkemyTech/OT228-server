package com.alkemy.ong.dto;

import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Users;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class NewsDto {

    private Long id;
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Content can not be empty")
    private String content;
    @NotBlank(message = "Image can not be empty")
    private String image;
    @NotBlank(message = "Category can not be empty")
    private Category category;

}
