package com.alkemy.ong.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryNameDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import com.alkemy.ong.util.MessageHandler;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private MessageHandler messageHandler;
  
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
    public Page<CategoryNameDto> viewAllCategoryNames(Pageable pageable) {
		return categoryRepository.findAll(pageable).map(
				c -> ModelMapperFacade.map(c, CategoryNameDto.class));
    }

	@Override
	public CategoryDto update(CategoryDto categoryDto) {
		if (categoryDto.getId() == null) throw new BadRequestException(messageHandler.categoryIdRequired);
		return categoryRepository.findById(categoryDto.getId())
				.map(c -> ModelMapperFacade.map(
						categoryRepository.save(ModelMapperFacade.map(categoryDto, Category.class)),
						CategoryDto.class))
				.orElseThrow(() -> new NotFoundException(messageHandler.categoryNotFound));
	}

    @Override
    public boolean delete(Long categoriesId) {
        return findById(categoriesId).map(categoryDto -> {
            categoryRepository.delete(mapper.map(categoryDto, Category.class));
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<CategoryDto> findById(Long categoriesId) {
        return Optional.of(mapper.map(categoryRepository.findById(categoriesId), CategoryDto.class));
    }

}
