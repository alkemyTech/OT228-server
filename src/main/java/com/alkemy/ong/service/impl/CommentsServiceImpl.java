package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.ICommentsRepository;
import com.alkemy.ong.service.ICommentsService;
import com.alkemy.ong.util.MessageHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements ICommentsService {

    @Autowired
    private ICommentsRepository commentsRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageHandler messageHandler;

    @Override
    public CommentDto register(CommentDto comments) {
        Comment commentSaveResponce = commentsRepository.save(mapToEntity(comments));
        return mapToDTO(commentSaveResponce);
    }

    @Override
    public List<CommentDto> findCommentsByNewsId(Long id) {
        List<CommentDto> commentDtos = new ArrayList<>();
        Optional<Comment> comment = commentsRepository.findById(id);
        if (comment.isEmpty()){
            throw new ResourceNotFoundException(messageHandler.commentNotFound);
        }
       commentsRepository.findCommentsByNewsId(id)
               .forEach(comment1 -> commentDtos.add(mapToDTO(comment1)));
        return  commentDtos;
    }

    //------ MAPPER ------
    private CommentDto mapToDTO(Comment comment) {
        return  mapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto) {
        return mapper.map(commentDto, Comment.class);

    }
}

