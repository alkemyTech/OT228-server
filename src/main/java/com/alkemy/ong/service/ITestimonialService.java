package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonials;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface ITestimonialService {

    Optional<Testimonials> findById(Long id);

    Page<TestimonialDto> findAll(Pageable pageable);

    boolean update(TestimonialDto testimonialDto, Long id);

    Testimonials save(TestimonialDto testimonialDto);

    boolean delete(Long id);

}
