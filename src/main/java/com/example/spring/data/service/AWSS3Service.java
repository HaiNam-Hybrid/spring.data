package com.example.spring.data.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {

    String uploadFile(MultipartFile multipartFile);
}
