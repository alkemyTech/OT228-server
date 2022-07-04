package com.alkemy.ong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.service.ISlideService;
import com.alkemy.ong.util.MessageHandler;

@RestController
@RequestMapping(SlideController.SLIDES)
public class SlideController {
	
	protected static final String SLIDES = "/slides";

	@Autowired
	private ISlideService slideService;
	
	@Autowired
	private MessageHandler messageHandler;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<?> result = slideService.findAll();
		return ResponseEntity.ok().body(result.isEmpty() ?
				result : messageHandler.slidesNotFound);
	}

}
