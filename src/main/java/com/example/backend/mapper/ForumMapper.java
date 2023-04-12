package com.example.backend.mapper;

import com.example.backend.domain.Forum;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Sisyphus
* @description 针对表【forum】的数据库操作Mapper
* @createDate 2023-04-12 10:21:59
* @Entity com.example.backend.domain.Forum
*/
public interface ForumMapper extends BaseMapper<Forum> {
    int insertAll(Forum forum);
}




