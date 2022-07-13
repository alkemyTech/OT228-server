package com.alkemy.ong.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alkemy.ong.model.Users;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.service.IUsersService;
import com.alkemy.ong.util.MessageHandler;

@Controller
@RequestMapping(UsersController.usersEndpoint)
public class UsersController {

    protected static final String usersEndpoint = "/users";
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long usersId,
                                    @RequestHeader("Bearer-Token") String bearerToken) {
        usersService.deleteByTokenOrId(usersId, bearerToken);
        return ResponseEntity.ok().build();
    }
}
