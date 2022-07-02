package com.alkemy.ong.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alkemy.ong.service.IUsersService;
import com.alkemy.ong.util.MessageHandler;

@Controller
@RequestMapping(UsersController.usersEndpoint)
public class UsersController {

    protected static final String usersEndpoint= "/users";

    @Autowired
    private IUsersService usersService;

	@Autowired
	private MessageHandler messageHandler;
    
    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdate(@RequestBody Map<String, Object> partialUpdate, @PathVariable("id") Long usersId) {
        if (usersService.partialUpdate(partialUpdate, usersId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<?> result = usersService.findAll();
		return ResponseEntity.ok().body(result.isEmpty() ?
				result : messageHandler.usersNotFound);
    }

}
