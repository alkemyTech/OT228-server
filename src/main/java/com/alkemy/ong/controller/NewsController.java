package com.alkemy.ong.controller;

import com.alkemy.ong.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsServiceImpl newsService;


    @GetMapping("/id/{newsId}")
    public ResponseEntity<?> findByID(@PathVariable Long newsId){

        return new ResponseEntity<>(newsService.getNewsById(newsId), HttpStatus.OK);
    }

    @PutMapping("/id/{newsId}")
    public ResponseEntity<?> updateNews(@RequestBody Map<String, Object> update, @PathVariable Long newsId){

        return new ResponseEntity<>(newsService.Update(update,newsId),HttpStatus.OK);
    }


}
