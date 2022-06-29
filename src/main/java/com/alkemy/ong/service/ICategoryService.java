package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryNameDto;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

	CategoryDto save(CategoryDto categoryDto);

	CategoryDto update(CategoryDto categoryDto);

	List<CategoryNameDto> viewAllCategoryNames();

    boolean delete(Long categoriesId);

    Optional<CategoryDto> findById(Long categoriesId);
}
