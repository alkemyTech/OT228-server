package com.alkemy.ong.controller;

import javax.validation.Valid;

import com.alkemy.ong.model.Category;
import com.alkemy.ong.util.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.service.ICategoryService;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

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

    @GetMapping
    public ResponseEntity<?> findAllCategorysName() {
        return new ResponseEntity<>(categoryService.viewAllCategoryNames(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long categoriesId) {
        if (categoryService.delete(categoriesId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoriesId) {
//        Optional<CategoryDto> optionalCategory = categoryService.findById(categoriesId);
//        if (optionalCategory.isEmpty())
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, messageHandler.categoryNotFound);
//        return ResponseEntity.status(HttpStatus.FOUND).body(optionalCategory.get());
//
//    }

}
