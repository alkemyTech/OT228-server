package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;

public interface INewsService {

    NewsDto createNews(NewsDto newsDto);

    NewsDto getNewsById(Long id);

    NewsDto Update(NewsDto newsDto, Long newsId);

    boolean delete(Long id);

}
