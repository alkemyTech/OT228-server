package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.hateoas.IHateoas;
import com.alkemy.ong.service.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

    private static final Integer SIZE = 10;

    @Autowired
    private ITestimonialService iTestimonialService;

    @PostMapping
    public ResponseEntity<?> createNewTestimonial(@Valid @RequestBody TestimonialDto testimonialDto){
        iTestimonialService.save(testimonialDto);
        return new ResponseEntity<>(testimonialDto, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity updateTestimonial(@Valid @RequestBody TestimonialDto testimonialDto, @Valid @RequestParam Long id){
        if(iTestimonialService.update(testimonialDto,id)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteTestimonial(@Valid @RequestParam Long id){
        if(iTestimonialService.delete(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllTestimonials(@RequestParam(name = "page", required = true)String page){
        Pageable pageable = PageRequest.of(Integer.parseInt(page), SIZE);
        return ResponseEntity.ok().body(
                IHateoas.addPaginationLinks(
                        iTestimonialService.findAll(pageable)));
    }


}
