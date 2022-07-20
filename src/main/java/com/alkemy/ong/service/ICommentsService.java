package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentCreatDto;
import com.alkemy.ong.dto.CommentDto;

public interface ICommentsService {

    CommentDto register(CommentDto comments);
    
    boolean existsById(Long id);
    
    void delete(Long id);
    
    void addCommentToPost(CommentCreatDto commentCreatDto);

}
