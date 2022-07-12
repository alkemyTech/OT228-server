package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonials;

import java.util.List;

public interface ITestimonialService {

    List<TestimonialDto> findAll();

    TestimonialDto update(TestimonialDto testimonialDto);

    Testimonials save(TestimonialDto testimonialDto);

    boolean delete(Long id);

}
