package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl  implements IOrganizationService {

    @Autowired
   private ObjectMapper objectMapper;
    @Autowired
   private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto findById(Long id) {
        OrganizationDto organizationFound = objectMapper.convertValue(organizationRepository.findById(id), OrganizationDto.class);
        return organizationFound;
    }

}
