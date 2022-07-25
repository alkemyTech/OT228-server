package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentCreatDto;
import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentUpdateDto;

import java.util.List;

import java.util.List;

public interface ICommentsService {

    CommentDto register(CommentDto comments);


    List<CommentDto> findAll();
    
    boolean existsById(Long id);
    
    void delete(Long id);
    
    void addCommentToPost(CommentCreatDto commentCreatDto);

    void updateComment(Long idComment, String bearerToken, CommentUpdateDto commentUpdateDto);

    List<CommentDto> findCommentsByNewsId(Long id);

}
