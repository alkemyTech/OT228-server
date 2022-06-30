package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.service.ICategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
    private ICategoryService categoryService;

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody CategoryDto categoryDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryDto));
	}

    @GetMapping
    public ResponseEntity<?> findAllCategorysName(){
        return new ResponseEntity<>(categoryService.viewAllCategoryNames(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long categoriesId){
        if(categoryService.delete(categoriesId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
