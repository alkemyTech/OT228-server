package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationPublicDto;

public interface IOrganizationService {
    OrganizationPublicDto findById(Long id);
    OrganizationDto updateOrganization(OrganizationDto organizationDto);
}
