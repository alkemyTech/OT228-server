package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.IOrganizationService;

import com.alkemy.ong.util.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

   
   @Autowired
   private OrganizationRepository organizationRepository;

   @Autowired
   private SlideRepository slideRepository;

   private MessageHandler messageHandler;


    @Override
    public OrganizationPublicDto findById(Long id) {
        Organization organization = organizationRepository.getById(id);

        List<SlideDto> slides = slideRepository.findByOrganizationIdOrderByOrderAsc(organization.getId()).stream()
               .map(ent -> ModelMapperFacade.map(
                       ent, SlideDto.class))
                .collect(Collectors.toList());

        OrganizationPublicDto organizationPublicDto= ModelMapperFacade.map(organization,OrganizationPublicDto.class);
        organizationPublicDto.setSlides(slides);

        return organizationPublicDto;
    }

    @Override
    public OrganizationDto updateOrganization(OrganizationDto organizationDto) {

        if(!organizationRepository.existsById(organizationDto.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                      messageHandler.organizationNotFound);
        }

        Organization organizationUpdate = organizationRepository.getById(organizationDto.getId());

        organizationUpdate.setName(organizationDto.getName());
        organizationUpdate.setImage(organizationDto.getImage());
        organizationUpdate.setAddress(organizationDto.getAddress());
        organizationUpdate.setPhone(organizationDto.getPhone());
        organizationUpdate.setFacebookUrl(organizationDto.getFacebookUrl());
        organizationUpdate.setInstragramUrl(organizationDto.getInstagramUrl());
        organizationUpdate.setLinkedinUrl(organizationDto.getLinkedinUrl());

        organizationRepository.save(organizationUpdate);

        return ModelMapperFacade.map(
                organizationUpdate, OrganizationDto.class);
    }


}
