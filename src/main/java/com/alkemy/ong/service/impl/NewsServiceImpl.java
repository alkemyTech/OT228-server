package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.ICategoryRepository;
import com.alkemy.ong.repository.INewsRepository;
import com.alkemy.ong.service.INewsService;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.mappers.ModelMapperFacade;

import com.alkemy.ong.util.MessageHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private INewsRepository newsRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    private MessageHandler messageHandler;

    @Override
    public NewsDto createNews(NewsDto newsDto) {
        News news = toEntity(newsDto);
        Category category = categoryRepository.findById(newsDto.getCategory().getId()).orElseThrow(() -> new ResourceNotFoundException(messageHandler.categoryNotFound));
        news.setCategory(category);
        news = newsRepository.save(news);

        return toDto(news);
    }

    @Override
    public NewsDto getNewsById(Long id) throws ResourceNotFoundException {
        Optional<News> news = newsRepository.findById(id);
        if (news.isEmpty()) {
            throw new ResourceNotFoundException(messageHandler.newsNotFound);
        }
        return ModelMapperFacade.map(news, NewsDto.class);

    }

    @Override
    public NewsDto Update(NewsDto newsDto, Long newsId) throws ResourceNotFoundException {
        Optional<News> news = newsRepository.findById(newsId);

        if (news.isEmpty()) {
            throw new ResourceNotFoundException(messageHandler.newsNotFound);
        } else {
            News newsToUpdate = toEntity(newsDto);
            newsRepository.save(newsToUpdate);
        }
        return getNewsById(newsId);
    }

    @Override
    public boolean delete(Long id) {
        Optional<News> news = newsRepository.findById(id);

        if (news.isEmpty()) {
            throw new ResourceNotFoundException((messageHandler.newsNotFound));
        } else {
            newsRepository.delete(ModelMapperFacade.map(news, News.class));
            return true;
        }

    }

    @Override
    public Page<NewsDto> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable).map(
                news -> ModelMapperFacade.map(news, NewsDto.class));
    }

    private NewsDto toDto(News news) {
        return mapper.map(news, NewsDto.class);
    }

    private News toEntity(NewsDto newsDto) {
        return mapper.map(newsDto, News.class);
    }

}
