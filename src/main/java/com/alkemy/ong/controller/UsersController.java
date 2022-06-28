package com.alkemy.ong.controller;

import com.alkemy.ong.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(UsersController.usersEndpoint)
public class UsersController {

    protected static final String usersEndpoint= "/users";


    @Autowired
    IUsersService usersService;

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@RequestBody Map<String, Object> partialUpdate, @PathVariable("id") Long usersId) {
        if (usersService.partialUpdate(partialUpdate, usersId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
