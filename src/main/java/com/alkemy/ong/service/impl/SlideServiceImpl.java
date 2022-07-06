package com.alkemy.ong.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.ISlideService;
import com.alkemy.ong.util.MessageHandler;

@Service
public class SlideServiceImpl implements ISlideService {

	@Autowired
	private SlideRepository slideRepository;

	@Autowired
	private MessageHandler messageHandler;

	@Override
	public List<SlideDto> findAll() {
		List<String> skipFields = new ArrayList<>();
		skipFields.add("text");
		skipFields.add("organization");
		return slideRepository.findAll()
				.stream()
				.map(s -> ModelMapperFacade.map(s, SlideDto.class, skipFields))
				.collect(Collectors.toList());
	}

	@Override
	public SlideDto update(SlideDto slideDto) {
		if (slideDto.getId() == null) throw new BadRequestException(messageHandler.slideIdRequired);
		return slideRepository.findById(slideDto.getId())
				.map(s -> ModelMapperFacade.map(
						slideRepository.save(ModelMapperFacade.map(slideDto, Slide.class)),
						SlideDto.class))
				.orElseThrow(() -> new NotFoundException(messageHandler.slideNotFound));
	}

}
