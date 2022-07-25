package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface INewsService {

    NewsDto createNews(NewsDto newsDto);

    NewsDto getNewsById(Long id);

    NewsDto Update(NewsDto newsDto, Long newsId);

    boolean delete(Long id);

    Page<NewsDto> findAll(Pageable pageable);

}
