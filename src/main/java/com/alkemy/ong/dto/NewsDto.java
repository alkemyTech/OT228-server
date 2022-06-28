package com.alkemy.ong.dto;

import com.alkemy.ong.model.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class NewsDto {


    private Long id;
    private String name;
    private String content;
    private String image;
    private Category category;

}
