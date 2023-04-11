package com.example.backend.mapper;

import com.example.backend.domain.Photo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Sisyphus
* @description 针对表【photo】的数据库操作Mapper
* @createDate 2023-04-11 16:27:14
* @Entity com.example.backend.domain.Photo
*/
public interface PhotoMapper extends BaseMapper<Photo> {
    int insertAll(Photo photo);

    String getPhotoUrlById(Integer id);
}




