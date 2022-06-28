package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.INewsService;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

public class NewsServiceImpl implements INewsService {

    @Autowired
    private INewsRepository newsRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public NewsDto createNews(NewsDto newsDto) {
        News news = toEntity(newsDto);
        Category category = categoryRepository.findById(newsDto.getCategory().getId()).orElseThrow(() -> new ResourceNotFoundException("That category does not exists"));
        news.setCategory(category);
        news = newsRepository.save(news);

        return toDto(news);
    }

    private NewsDto toDto(News news) {
        return mapper.map(news, NewsDto.class);
    }

    private News toEntity(NewsDto newsDto) {
        return mapper.map(newsDto, News.class);
    }
}
