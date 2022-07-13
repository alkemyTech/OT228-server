package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrganizationPublicDto {

    private Long id;
    private String name;
    private String image;
    private String phone;
    private String address;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    @JsonManagedReference
    private List<SlideDto> slides;

}


