package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityDto;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.service.IActivityService;

import com.alkemy.ong.util.MessageHandler;

import org.springframework.beans.factory.annotation.Autowired;

public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    private MessageHandler messageHandler;

    @Override
    public ActivityDto save(ActivityDto activityDto) {

        return ModelMapperFacade.map(
                activityRepository.save(ModelMapperFacade.map(
                        activityDto, Activity.class)),
                ActivityDto.class);
    }

    @Override
    public ActivityDto update(ActivityDto activityDto, Long id){

        return activityRepository.findById(id)
                .map(c -> ModelMapperFacade.map(
                        activityRepository.save(ModelMapperFacade.map(activityDto, Activity.class)),
                        ActivityDto.class))
                .orElseThrow(() -> new ResourceNotFoundException(messageHandler.activityNotFound));

    }
}
