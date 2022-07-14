package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDto;

public interface IOrganizationService {
    OrganizationDto findById(Long id);
    OrganizationDto updateOrganization(OrganizationDto organizationDto);
}
