package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.service.ICategoryService;

@RestController
@RequestMapping(CategoryController.CATEGORIES)
public class CategoryController {
	
	protected static final String CATEGORIES = "/categories";
	
	@Autowired
	private ICategoryService categoryService;

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CategoryDto categoryDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryDto));
	}

}
