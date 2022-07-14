package com.alkemy.ong.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryNameDto;

public interface ICategoryService {

	CategoryDto save(CategoryDto categoryDto);

	CategoryDto update(CategoryDto categoryDto);

	Page<CategoryNameDto> viewAllCategoryNames(Pageable pageable);

    boolean delete(Long categoriesId);

    Optional<CategoryDto> findById(Long categoriesId);

}
