package com.oss.app.controller;

import com.oss.app.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * LittleCadet
 */
@RequestMapping("/image")
@RestController
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    @GetMapping("/upload")
    public void upload(){
        imageUploadService.operation();
    }
}
