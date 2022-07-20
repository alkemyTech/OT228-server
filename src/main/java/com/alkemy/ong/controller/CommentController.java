package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentCreatDto;
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

    @PostMapping
    public ResponseEntity<?> addCommentToPost(@Valid @RequestBody CommentCreatDto commentDto) {
        commentsService.addCommentToPost(commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
