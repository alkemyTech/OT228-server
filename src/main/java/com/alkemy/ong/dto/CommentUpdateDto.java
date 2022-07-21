package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentUpdateDto {

    @NotNull
    @Length(max = 1000, message = "The maximum number of characters is 1000")
    private String body;
}
