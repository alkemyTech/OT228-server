package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentUpdateDto {

    @NotNull
    @Length(max = 1000, message = "body must be have 1000 characters")
    private String body;
}
