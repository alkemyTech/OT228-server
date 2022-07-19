package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.ICommentsRepository;
import com.alkemy.ong.service.ICommentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsServiceImpl implements ICommentsService {

    @Autowired
    private ICommentsRepository commentsRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CommentDto register(CommentDto comments) {
        Comment commentSaveResponce = commentsRepository.save(mapToEntity(comments));
        return mapToDTO(commentSaveResponce);
    }

    @Override
    public List<CommentDto> findAll() {
        return commentsRepository.findByOrderByCreatedAtAsc()
                .stream().map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //------ MAPPER ------
    private CommentDto mapToDTO(Comment comment) {
        return  mapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto) {
        return mapper.map(commentDto, Comment.class);

    }
}

