package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.repository.ICommentsRepository;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.service.ICommentsService;
import com.alkemy.ong.util.MessageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class CommentsServiceImpl implements ICommentsService {

    @Autowired
    private ICommentsRepository commentsRepository;

    @Autowired
    private UsersRspository usersRepository;

    private MessageHandler messageHandler;

    @Override
    public CommentDto register(CommentDto comments) {
        return ModelMapperFacade.map(
                commentsRepository.save(ModelMapperFacade.map(
                        comments, Comment.class)),
                CommentDto.class);
    }

    @Override
    public void delete(Long id) {

        Comment comment = commentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageHandler.commentNotFound));

        if (!adminRole() && !(comment.getUser().getEmail().equals(userAuthenticated())) ) {
            throw new BadRequestException("");
        }
        commentsRepository.delete(comment);
    }

    public boolean adminRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> "ADMIN"
                        .equals(grantedAuthority.getAuthority()));
    }

    public String userAuthenticated() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (principal instanceof UserDetails)
                ? ((UserDetails) principal).getUsername() : principal.toString();
    }

    public boolean existsById(Long id){
       return commentsRepository.existsById(id);
    }

}

