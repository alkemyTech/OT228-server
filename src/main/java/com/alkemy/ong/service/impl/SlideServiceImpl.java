package com.alkemy.ong.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IAmazonClientFacade;
import com.alkemy.ong.util.DecodedMultipartFile;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.model.News;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.ISlideService;
import com.alkemy.ong.util.MessageHandler;

@Service
public class SlideServiceImpl implements ISlideService {

	@Autowired
	private SlideRepository slideRepository;

	@Autowired
	private MessageHandler messageHandler;

	@Autowired
	private IAmazonClientFacade amazonClientFacade;

	private OrganizationRepository organizationRepository;


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

	@Override

	public SlideDto save(SlideDto slideDto){

			DecodedMultipartFile multiPartFile = new DecodedMultipartFile(slideDto.getImageUrl());
			String fileUrl = amazonClientFacade.uploadFile(multiPartFile);
			slideDto.setImageUrl(fileUrl);

			if(slideDto.getOrder() == null){
				slideDto.setOrder(slideRepository.getMax()+1);
			}

		return ModelMapperFacade.map(
				slideRepository.save(ModelMapperFacade.map(
						slideDto, Slide.class)),
				SlideDto.class);
      }

	public boolean delete(Long id) {
		Optional<Slide> slide = slideRepository.findById(id);

		if(slide.isPresent()){
			slideRepository.delete(ModelMapperFacade.map(slide, Slide.class));
			return true;
		}else{
			throw new ResourceNotFoundException((messageHandler.newsNotFound));
		}

	}

}
