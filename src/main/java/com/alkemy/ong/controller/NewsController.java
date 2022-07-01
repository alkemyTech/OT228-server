package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;

import com.alkemy.ong.service.INewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/news")

public class NewsController {

    @Autowired
    private INewsService newsService;

    @PostMapping
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsDto newsDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.createNews(newsDto));
    }


    @GetMapping("/id/{newsId}")
    public ResponseEntity<?> findByID(@PathVariable Long newsId){

        return new ResponseEntity<>(newsService.getNewsById(newsId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNews(@RequestBody NewsDto newsDto, @Valid @PathVariable Long newsId){

        return new ResponseEntity<>(newsService.Update(newsDto,newsId),HttpStatus.OK);
    }


}
