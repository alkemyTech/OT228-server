package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryNameDto;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
   private ICategoryRepository categoryRepository;
    @Autowired
   private ModelMapper mapper;

    @Override
    public List<CategoryNameDto> viewAllCategoryNames() {
        List<CategoryNameDto> categoryNameDtos = new ArrayList<>();
        categoryRepository.findAll()
                .stream()
                .forEach(category -> categoryNameDtos.add(mapper.map(category, CategoryNameDto.class)));
        return categoryNameDtos;
    }
}
