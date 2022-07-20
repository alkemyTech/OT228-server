package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.ModelMapperFacade;
import com.alkemy.ong.model.Testimonials;
import com.alkemy.ong.repository.TestimonialsRepository;
import com.alkemy.ong.service.ITestimonialService;
import com.alkemy.ong.util.MessageHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<TestimonialDto> findAll(Pageable pageable) {
            return testimonialsRepository.findAll(pageable).map(
                    testimonials -> ModelMapperFacade.map(testimonials, TestimonialDto.class));
    }

    @Override
    public boolean update(TestimonialDto testimonialDto, Long id) {
        Optional<Testimonials> testimonials = findById(id);

        if(testimonials.isPresent()){
            Testimonials update = modelMapper.map(testimonialDto, Testimonials.class);
            testimonialsRepository.save(update);
            return true;
        }else{
           throw new NotFoundException(messageHandler.testimoniaNotFound);
        }
    }

    @Override
    public Testimonials save(TestimonialDto testimonialDto) {
        Testimonials testimonials = modelMapper.map(testimonialDto,Testimonials.class);
        return testimonialsRepository.save(testimonials);
    }

    @Override
    public boolean delete(Long id) {

        Optional<Testimonials> testimonials = findById(id);
        if(testimonials.isPresent()){
            testimonialsRepository.delete(testimonials.get());
            return true;
        }else{
            throw new NotFoundException(messageHandler.testimoniaNotFound);
        }
    }

}
