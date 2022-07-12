package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.model.Testimonials;
import com.alkemy.ong.repository.TestimonialsRepository;
import com.alkemy.ong.service.ITestimonialService;
import com.alkemy.ong.util.MessageHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class TestimonialServiceImpl implements ITestimonialService {

    @Autowired
    private TestimonialsRepository testimonialsRepository;

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Optional<Testimonials> findById(Long id) {
        return testimonialsRepository.findById(id);
    }

    @Override
    public List<TestimonialDto> findAll() {
        return null;
    }

    @Override
    public TestimonialDto update(TestimonialDto testimonialDto, Long id) {
        Optional<Testimonials> testimonials = findById(id);

        if(testimonials.isPresent()){
            Testimonials update = modelMapper.map(testimonialDto, Testimonials.class);
            testimonialsRepository.save(update);
        }else{
           throw new NotFoundException(messageHandler.testimoniaNotFound);
        }
        return modelMapper.map(testimonialsRepository.findById(id), TestimonialDto.class);
    }

    @Override
    public Testimonials save(TestimonialDto testimonialDto) {
        Testimonials testimonials = modelMapper.map(testimonialDto,Testimonials.class);
        return testimonialsRepository.save(testimonials);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

}
