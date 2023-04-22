package com.example.backend.service;

import com.example.backend.domain.Photo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.result.CommonResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

/**
* @author Sisyphus
* @description 针对表【photo】的数据库操作Service
* @createDate 2023-04-11 16:27:14
*/
public interface PhotoService extends IService<Photo> {
    Integer insertPhoto(Photo photo);

    ArrayList<String> getPhotoUrlById(Integer id);

    String getUrlStrById(Integer id);

    String Upload(String prefix, MultipartFile file);

    void delete(String path);
}
