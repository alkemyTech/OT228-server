package com.alkemy.ong.service;

import org.springframework.web.multipart.MultipartFile;

public interface IAmazonClientFacade {

	String uploadFile(MultipartFile multipartFile);

}
