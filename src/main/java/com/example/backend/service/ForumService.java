package com.example.backend.service;

import com.example.backend.domain.Forum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Sisyphus
* @description 针对表【forum】的数据库操作Service
* @createDate 2023-04-12 10:21:59
*/
public interface ForumService extends IService<Forum> {
    int insertForum(Forum forum);
}
