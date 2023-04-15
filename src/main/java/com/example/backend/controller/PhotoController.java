package com.example.backend.controller;

import com.example.backend.domain.Photo;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @RequestMapping("/photo/insert")
    public CommonResult insertPhoto(@RequestBody Photo photo) {
        int ret = photoService.insertPhoto(photo);
        if (ret == 0)
            return CommonResult.failed("photo数据插入失败");
        else
            return CommonResult.success(null);
    }

    @RequestMapping("/photo/upload")
    public CommonResult uploadPhoto(MultipartFile pic) {
        String picPath = System.getProperty("user.dir") + "/pic/";
        return photoService.uploadPhoto(pic, picPath);
    }
}
