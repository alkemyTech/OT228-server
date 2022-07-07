package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    @GetMapping("/public/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        OrganizationDto organizationResponse = organizationService.findById(id);
        if (organizationResponse!=null){
            return ResponseEntity.ok(organizationResponse);
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("The organization with id: " + id + " was not found in the database. Please enter a valid id");
        }
    }

    @PostMapping("/public")
    public ResponseEntity<?> updateOrganization(@Valid @RequestBody OrganizationDto organizationDto) {

        OrganizationDto organizationResponse = organizationService.updateOrganization(organizationDto);

        return new ResponseEntity<>(organizationResponse, HttpStatus.OK);
    }
}
