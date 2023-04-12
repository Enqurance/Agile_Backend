package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Forum;
import com.example.backend.service.ForumService;
import com.example.backend.mapper.ForumMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【forum】的数据库操作Service实现
* @createDate 2023-04-12 10:21:59
*/
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum>
    implements ForumService{
    @Resource
    ForumMapper forumMapper;

    @Override
    public int insertForum(Forum forum) {
        int ret = forumMapper.insertAll(forum);
        return ret;
    }
}




