package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;

import com.alkemy.ong.hateoas.IHateoas;
import com.alkemy.ong.service.INewsService;

import com.alkemy.ong.service.impl.CommentsServiceImpl;
import com.alkemy.ong.util.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private CommentsServiceImpl commentsService;

    @Autowired
    private MessageHandler messageHandler;

    private static final Integer SIZE = 10;

    @PostMapping
    public ResponseEntity<?> createNews(@Valid @RequestBody NewsDto newsDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsService.createNews(newsDto));
    }


    @GetMapping("/id/{newsId}")
    public ResponseEntity<?> findByID(@PathVariable Long newsId) {

        return new ResponseEntity<>(newsService.getNewsById(newsId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNews(@RequestBody NewsDto newsDto, @Valid @PathVariable Long newsId) {

        return new ResponseEntity<>(newsService.Update(newsDto, newsId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@Valid @PathVariable Long newsId) {

        if (!newsService.delete(newsId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllTestimonials(@RequestParam(name = "page", required = true) String page) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page), SIZE);
        Page<NewsDto> pages = newsService.findAll(pageable);
        return ResponseEntity.ok().body(
                !pages.isEmpty() ?
                        IHateoas.addPaginationLinks(newsService.findAll(pageable)) :
                        messageHandler.newsNotFound);
    }

    @GetMapping("/{newsId}/comments")
    public ResponseEntity<?> findCommentsByNewsId(@PathVariable Long newsId){
        return new ResponseEntity<>(commentsService.findCommentsByNewsId(newsId), HttpStatus.OK);
    }

}
