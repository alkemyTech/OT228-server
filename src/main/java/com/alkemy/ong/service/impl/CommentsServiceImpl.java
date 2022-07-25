package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentCreatDto;
import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.exception.PermissionDeniedException;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.dto.CommentUpdateDto;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.ICommentsRepository;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.service.ICommentsService;
import com.alkemy.ong.util.MessageHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.security.jwt.JwtUtils;
import com.alkemy.ong.service.ICommentsService;
import com.alkemy.ong.util.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CommentsServiceImpl implements ICommentsService {

    @Autowired
    private ICommentsRepository commentsRepository;

    @Autowired
    private UsersRspository usersRepository;

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private INewsRepository newsRepository;

    @Autowired
    private UsersRspository usersRspository;

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

        if (!adminRole() && !(comment.getUser().getEmail().equals(userAuthenticated()))) {
            throw new PermissionDeniedException();
        }
        commentsRepository.delete(comment);
    }

    public void addCommentToPost(CommentCreatDto commentCreatDto) {
        Optional<News> news = newsRepository.findById(commentCreatDto.getPost_id());
        news.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, messageHandler.newsNotFound));
        Optional<Users> users = usersRspository.findById(commentCreatDto.getUser_id());
        users.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, messageHandler.usersNotFound));

        Comment comment = ModelMapperFacade.map(commentCreatDto, Comment.class);
        comment.setNews(news.get());
        comment.setUser(users.get());
        commentsRepository.save(comment);
    }


    public boolean adminRole() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> "ADMIN"
                        .equals(grantedAuthority.getAuthority()));
    }

    @Override
    public void updateComment(Long idComment, String bearerToken, CommentUpdateDto commentUpdateDto) {
        Optional<Comment> comment = commentsRepository.findById(idComment);
        comment.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, messageHandler.commentNotFound));
        Optional<Users> users = usersRspository.findByEmail(JwtUtils.getUsername(bearerToken));
        users.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, messageHandler.usersNotFound));
        Users userByToken = users.get();
        Comment commentEntity = comment.get();
        if (userByToken.getEmail().equals(commentEntity.getUser().getEmail())
                || userByToken.getRole().getName().equals("ADMIN")) {
            commentEntity.setBody(commentUpdateDto.getBody());
            commentsRepository.save(commentEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, messageHandler.userUnauthorized);

        }
    }


    @Override
    public List<CommentDto> findCommentsByNewsId(Long id) {
        List<CommentDto> commentDtos = new ArrayList<>();
        Optional<Comment> comment = commentsRepository.findById(id);
        if (comment.isEmpty()) {
            throw new ResourceNotFoundException(messageHandler.commentNotFound);
        }
        commentsRepository.findCommentsByNewsId(id)
                .forEach(comment1 -> commentDtos.add(mapToDTO(comment1)));
        return commentDtos;
    }

    //------ MAPPER ------
    private CommentDto mapToDTO(Comment comment) {
        return ModelMapperFacade.map(comment, CommentDto.class);

    }

    public String userAuthenticated() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (principal instanceof UserDetails)
                ? ((UserDetails) principal).getUsername() : principal.toString();
    }

    public boolean existsById(Long id) {
        return commentsRepository.existsById(id);
    }

}

