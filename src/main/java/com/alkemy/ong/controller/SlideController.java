package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.service.ISlideService;

@RestController
@RequestMapping(SlideController.SLIDES)
public class SlideController {
	
	protected static final String SLIDES = "/slides";

	@Autowired
	private ISlideService slideService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(slideService.findAll());
	}

}
