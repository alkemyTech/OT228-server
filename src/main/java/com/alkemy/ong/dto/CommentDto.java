package com.alkemy.ong.dto;

import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Users;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Long id;
    private Users user;
    private String body;
    private News news;

    public CommentDto() {
    }
}
