package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryNameDto;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
	private ICategoryRepository categoryRepository;
  
  @Autowired
  private ModelMapper mapper;
	
	@Override
	public CategoryDto save(CategoryDto categoryDto) {
        if (!categoryDto.getName().matches("^[a-zA-Z]*$")) throw new BadRequestException("The name only supports alphabetic characters.");
		return ModelMapperFacade.map(
				categoryRepository.save(ModelMapperFacade.map(
						categoryDto, Category.class)),
				CategoryDto.class);
	}
  
    @Override
    public List<CategoryNameDto> viewAllCategoryNames() {
        List<CategoryNameDto> categoryNameDtos = new ArrayList<>();
        categoryRepository.findAll()
                .stream()
                .forEach(category -> categoryNameDtos.add(mapper.map(category, CategoryNameDto.class)));
        return categoryNameDtos;
    }

	@Override
	public CategoryDto update(CategoryDto categoryDto) {
		if (categoryDto.getId() == null) throw new BadRequestException("Category id must not be null.");
		return categoryRepository.findById(categoryDto.getId())
				.map(c -> ModelMapperFacade.map(
						categoryRepository.save(ModelMapperFacade.patchObject(categoryDto, c)),
						CategoryDto.class))
				.orElseThrow(() -> new NotFoundException("Category not found."));
	}

}
