//package com.example.spring.data.api;
//
//import com.example.spring.data.service.AWSS3Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping(value = "api/s3")
//public class AWSS3Ctrl {
//
//    @Autowired
//    private AWSS3Service service;
//
//    @PostMapping(value = "/uploadFile")
//    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") final MultipartFile multipartFile) {
//        String serviceresponse = service.uploadFile(multipartFile);
//        final String response = "[" + multipartFile.getOriginalFilename() + "] " + serviceresponse;
//        if (response.contains("] File upload is failed")) {
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        } else {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//    }
//}
