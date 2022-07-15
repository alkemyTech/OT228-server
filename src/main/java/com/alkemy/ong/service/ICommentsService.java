package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentCreatDto;
import com.alkemy.ong.dto.CommentDto;

public interface ICommentsService {

    CommentDto register(CommentDto comments);

    void addCommentToPost(CommentCreatDto commentCreatDto);
}
