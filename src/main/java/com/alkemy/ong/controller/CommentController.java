package com.alkemy.ong.controller;


import com.alkemy.ong.service.ICommentsService;
import com.alkemy.ong.util.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.CommentCreatDto;
import com.alkemy.ong.dto.CommentUpdateDto;
import com.alkemy.ong.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private ICommentsService commentsService;

    @Autowired
    private MessageHandler messageHandler;

    @PostMapping
    public ResponseEntity<?> addCommentToPost(@Valid @RequestBody CommentCreatDto commentDto) {
        commentsService.addCommentToPost(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long commentId) {

        if (!commentsService.existsById(commentId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            commentsService.delete(commentId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long commentId,
                                           @RequestHeader("Bearer-Token") String bearerToken,
                                           @Valid @RequestBody CommentUpdateDto commentDto) {
        commentsService.updateComment(commentId, bearerToken, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
