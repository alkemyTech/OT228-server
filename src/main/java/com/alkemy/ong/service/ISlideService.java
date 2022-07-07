package com.alkemy.ong.service;

import java.util.List;

import com.alkemy.ong.dto.SlideDto;

public interface ISlideService {

	List<SlideDto> findAll();
	
	SlideDto update(SlideDto slideDto);

	boolean delete(Long id);

}
