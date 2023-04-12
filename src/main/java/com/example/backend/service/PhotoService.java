package com.example.backend.service;

import com.example.backend.domain.Photo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
* @author Sisyphus
* @description 针对表【photo】的数据库操作Service
* @createDate 2023-04-11 16:27:14
*/
public interface PhotoService extends IService<Photo> {
    int insertPhoto(Photo photo);

    ArrayList<String> getPhotoUrlById(Integer id);

    String getUrlStrById(Integer id);
}
