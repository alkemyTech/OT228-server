package com.alkemy.ong.controller;

import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> findAllCategorysName(){
        return new ResponseEntity<>(categoryService.viewAllCategoryNames(), HttpStatus.OK);
    }

}
