package com.example.backend.mapper;

import com.example.backend.domain.Photo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.List;

/**
* @author Sisyphus
* @description 针对表【photo】的数据库操作Mapper
* @createDate 2023-04-11 16:27:14
* @Entity com.example.backend.domain.Photo
*/
public interface PhotoMapper extends BaseMapper<Photo> {
    int insertAll(Photo photo);

    ArrayList<String> getPhotoUrlByPinId(Integer id);

    int deletePhotoByPinId(Integer pin_id);

    int deletePhotoByUrl(String url, Integer pin_id);
}




