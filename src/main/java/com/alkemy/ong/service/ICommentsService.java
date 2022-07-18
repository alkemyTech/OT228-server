package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentDto;

public interface ICommentsService {

    CommentDto register(CommentDto comments);
    boolean existsById(Long id);
    void delete(Long id);
}
