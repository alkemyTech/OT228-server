package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.ContactsService;

@RestController
@RequestMapping(ContactController.CONTACTS)
public class ContactController {

	protected static final String CONTACTS = "/contacts";

	@Autowired
	private ContactsService contactService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody ContactDto contactDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contactDto));
	}

}
