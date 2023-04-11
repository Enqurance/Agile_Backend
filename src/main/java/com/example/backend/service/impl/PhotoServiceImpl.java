package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Photo;
import com.example.backend.service.PhotoService;
import com.example.backend.mapper.PhotoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Sisyphus
* @description 针对表【photo】的数据库操作Service实现
* @createDate 2023-04-11 16:27:14
*/
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo>
    implements PhotoService{
    @Resource
    PhotoMapper photoMapper;

    @Override
    public int insertPhoto(Photo photo) {
        int ret = photoMapper.insertAll(photo);
        return ret;
    }

    @Override
    public ArrayList<String> getPhotoUrlById(Integer id) {
        String urls = photoMapper.getPhotoUrlById(id);
        String[] arrayUrl = urls.trim().split(";");
        return new ArrayList<>(List.of(arrayUrl));
    }

    @Override
    public String getUrlStrById(Integer id) {
        return photoMapper.getPhotoUrlById(id).trim();
    }
}




