package com.alkemy.ong.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.SlideDto;
import com.alkemy.ong.service.ISlideService;
import com.alkemy.ong.util.MessageHandler;

@RestController
@RequestMapping(SlideController.SLIDES)
public class SlideController {
	
	protected static final String SLIDES = "/slides";
	protected static final String ID = "/{id}";

	@Autowired
	private ISlideService slideService;
	
	@Autowired
	private MessageHandler messageHandler;

	@GetMapping
	public ResponseEntity<?> findAll() {
		List<?> result = slideService.findAll();
		return ResponseEntity.ok().body(result.isEmpty() ?
				result : messageHandler.slidesNotFound);
	}

	@PutMapping(ID)
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody SlideDto slideDto) {
		slideDto.setId(id);
		return ResponseEntity.status(HttpStatus.OK).body(slideService.update(slideDto));
	}

	@DeleteMapping(ID)
	public ResponseEntity<?> deleteNews(@Valid @PathVariable Long slidesId){

		if(!slideService.delete(slidesId)){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

}
