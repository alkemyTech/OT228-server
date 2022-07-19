package com.alkemy.ong.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.hateoas.IHateoas;
import com.alkemy.ong.service.ICategoryService;
import com.alkemy.ong.util.MessageHandler;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private static final Integer SIZE = 10;

    private final ICategoryService categoryService;
    private final MessageHandler messageHandler;

    @Autowired
    public CategoryController(ICategoryService categoryService, MessageHandler messageHandler) {
        this.categoryService = categoryService;
        this.messageHandler = messageHandler;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(categoryDto));
    }

    @GetMapping
    public ResponseEntity<?> findAllCategoriesName(@RequestParam(name = "page", required = true) String page) {
    	Pageable pageable = PageRequest.of(Integer.parseInt(page), SIZE);
        return ResponseEntity.ok().body(
        		IHateoas.addPaginationLinks(
        				categoryService.viewAllCategoriesName(pageable)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long categoriesId) {
        if (categoryService.delete(categoriesId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoriesId) {
        Optional<CategoryDto> optionalCategory = categoryService.findById(categoriesId);
        if (optionalCategory.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageHandler.categoryNotFound);
        return ResponseEntity.status(HttpStatus.FOUND).body(optionalCategory.get());

    }

}
