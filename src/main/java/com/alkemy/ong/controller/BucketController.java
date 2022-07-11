package com.alkemy.ong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alkemy.ong.service.IAmazonClientFacade;

@RestController
@RequestMapping(BucketController.STORAGE)
public class BucketController {

	protected static final String STORAGE = "/storage";
	private static final String UPLOAD = "/upload";

	@Autowired
	private IAmazonClientFacade amazonClientFacade;

	@PostMapping(UPLOAD)
	public ResponseEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile multipartFile) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(amazonClientFacade.uploadFile(multipartFile));
	}

}
