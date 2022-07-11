package com.alkemy.ong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.service.IMemberService;
import com.alkemy.ong.util.MessageHandler;

@RestController
@RequestMapping(MemberController.MEMBERS)
public class MemberController {

	protected static final String MEMBERS = "/members";

	@Autowired
	private IMemberService memberService;

	@Autowired
	private MessageHandler messageHandler;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<?> result = memberService.findAll();
		return ResponseEntity.ok().body(!result.isEmpty() ?
				result : messageHandler.membersNotFound);
	}

}