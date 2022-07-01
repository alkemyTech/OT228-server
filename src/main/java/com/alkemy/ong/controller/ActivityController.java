package com.alkemy.ong.controller;


import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.service.IActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private IActivityService activityService;


    @PostMapping
    public ResponseEntity<?> createActivity(@Valid @RequestBody ActivityDto activityDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityService.save(activityDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivity (@Valid  @PathVariable(name = "id") long id,
                                             @RequestBody ActivityDto activityDto){

        ActivityDto activityResponse = activityService.update(activityDto,id);
        return new ResponseEntity<>(activityResponse, HttpStatus.OK);
    }

}
