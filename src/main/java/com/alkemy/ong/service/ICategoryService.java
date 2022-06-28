package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryNameDto;

import java.util.List;

public interface ICategoryService {

	CategoryDto save(CategoryDto categoryDto);

  List<CategoryNameDto> viewAllCategoryNames();

}
