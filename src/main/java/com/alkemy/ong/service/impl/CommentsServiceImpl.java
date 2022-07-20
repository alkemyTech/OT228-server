package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentCreatDto;
import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.dto.CommentUpdateDto;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.ICommentsRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.repository.UsersRspository;
import com.alkemy.ong.security.jwt.JwtUtils;
import com.alkemy.ong.service.ICommentsService;
import com.alkemy.ong.util.MessageHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CommentsServiceImpl implements ICommentsService {

    @Autowired
    private ICommentsRepository commentsRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MessageHandler messageHandler;
    @Autowired
    private INewsRepository newsRepository;

    @Autowired
    private UsersRspository usersRspository;

    @Override

    public CommentDto register(CommentDto comments) {
        Comment commentSaveResponce = commentsRepository.save(mapToEntity(comments));
        return mapToDTO(commentSaveResponce);
    }

    @Override
    public void addCommentToPost(CommentCreatDto commentCreatDto) {
        Optional<News> news = newsRepository.findById(commentCreatDto.getPost_id());
        news.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, messageHandler.newsNotFound));
        Optional<Users> users = usersRspository.findById(commentCreatDto.getUser_id());
        users.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, messageHandler.usersNotFound));

        Comment comment = mapper.map(commentCreatDto, Comment.class);
        comment.setNews(news.get());
        comment.setUser(users.get());
        commentsRepository.save(comment);
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

    //------ MAPPER ------
    private CommentDto mapToDTO(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto) {
        return mapper.map(commentDto, Comment.class);

    }
}

