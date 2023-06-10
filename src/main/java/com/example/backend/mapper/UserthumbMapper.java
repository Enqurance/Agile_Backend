package com.example.backend.mapper;

import com.example.backend.domain.Userthumb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Sisyphus
* @description 针对表【userthumb】的数据库操作Mapper
* @createDate 2023-05-25 19:59:12
* @Entity com.example.backend.domain.Userthumb
*/
public interface UserthumbMapper extends BaseMapper<Userthumb> {

    Userthumb getThumbById(Integer userId, Integer postId);

    int insertThumb(Userthumb userthumb);

    int deleteThumbById(Integer userId, Integer postId);

    int deleteThumbByPostId(Integer postId);
}




