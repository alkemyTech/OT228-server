package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
   private ObjectMapper objectMapper;
   
   @Autowired
   private OrganizationRepository organizationRepository;


    @Override
    public OrganizationDto findById(Long id) {
        return objectMapper.convertValue(organizationRepository.findById(id), OrganizationDto.class);
    }

    @Override
    public OrganizationDto updateOrganization(OrganizationDto organizationDto) {

        if(!organizationRepository.existsById(organizationDto.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Organization with the id: " + organizationDto.getId()+ " ,was not found");
        }

        Organization organizationUpdate = organizationRepository.getById(organizationDto.getId());

        organizationUpdate.setName(organizationDto.getName());
        organizationUpdate.setImage(organizationDto.getImage());
        organizationUpdate.setAddress(organizationDto.getAddress());
        organizationUpdate.setPhone(organizationDto.getPhone());

        organizationRepository.save(organizationUpdate);

        return objectMapper.convertValue(organizationUpdate, OrganizationDto.class);
    }


}
