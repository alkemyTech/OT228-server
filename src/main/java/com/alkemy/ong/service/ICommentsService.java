package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentCreatDto;
import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentUpdateDto;

public interface ICommentsService {

    CommentDto register(CommentDto comments);

    void addCommentToPost(CommentCreatDto commentCreatDto);

    void updateComment(Long idComment, String bearerToken, CommentUpdateDto commentUpdateDto);
}
