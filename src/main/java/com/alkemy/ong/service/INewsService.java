package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;

import java.util.Map;


public interface INewsService {

    NewsDto getNewsById(Long id);
    NewsDto Update(NewsDto newsDto, Long newsId);
}
