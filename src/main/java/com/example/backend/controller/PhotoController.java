package com.example.backend.controller;

import com.example.backend.domain.Photo;
import com.example.backend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @RequestMapping("/photo/insert")
    public int insertPhoto(Photo photo) {
        int ret = photoService.insertPhoto(photo);
        return ret;
    }
}
