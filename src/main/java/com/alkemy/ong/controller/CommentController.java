package com.alkemy.ong.controller;

import com.alkemy.ong.service.ICommentsService;
import com.alkemy.ong.util.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentsService commentsService;

    @Autowired
    private MessageHandler messageHandler;

    @GetMapping
    public ResponseEntity<?> listComments(){
        if(!commentsService.findAll().isEmpty()){
             return new ResponseEntity<>(commentsService.findAll(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(messageHandler.listCommentsEmpty, HttpStatus.NOT_FOUND);
        }
    }

}