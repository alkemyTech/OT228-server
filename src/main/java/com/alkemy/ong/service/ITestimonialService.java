package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonials;

import java.util.List;
import java.util.Optional;

public interface ITestimonialService {

    Optional<Testimonials> findById(Long id);

    List<TestimonialDto> findAll();

    boolean update(TestimonialDto testimonialDto, Long id);

    Testimonials save(TestimonialDto testimonialDto);

    boolean delete(Long id);

}
