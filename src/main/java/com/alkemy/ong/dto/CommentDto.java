package com.alkemy.ong.dto;

import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private Users user;
    private String body;
    private News news;

}
