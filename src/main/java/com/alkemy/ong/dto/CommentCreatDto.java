package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CommentCreatDto {

    @NotNull(message = "post id must no be nul")
    private Long post_id;

    @NotNull(message = "user id must no be nul")
    private Long user_id;

    @Length(max = 1000, message = "body must be have 1000 characters")
    private String body;
}
