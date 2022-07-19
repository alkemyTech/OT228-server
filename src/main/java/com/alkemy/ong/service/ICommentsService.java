package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

import java.util.List;

public interface ICommentsService {

    CommentDto register(CommentDto comments);

    List<CommentDto> findAll();

}
