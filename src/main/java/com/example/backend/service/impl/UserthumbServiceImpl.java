package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Userthumb;
import com.example.backend.service.UserthumbService;
import com.example.backend.mapper.UserthumbMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【userthumb】的数据库操作Service实现
* @createDate 2023-05-25 19:59:12
*/
@Service
public class UserthumbServiceImpl extends ServiceImpl<UserthumbMapper, Userthumb>
    implements UserthumbService{
    @Resource
    UserthumbMapper userthumbMapper;

    @Override
    public Userthumb getThumbById(Integer user_id, Integer post_id) {
        return userthumbMapper.getThumbById(user_id, post_id);
    }

    @Override
    public int insertThumb(Userthumb userthumb) {
        return userthumbMapper.insertThumb(userthumb);
    }

    @Override
    public int deleteThumbById(Integer user_id, Integer post_id) {
        return userthumbMapper.deleteThumbById(user_id, post_id);
    }

    @Override
    public int deleteThumbByPostId(Integer post_id) {
        return userthumbMapper.deleteThumbByPostId(post_id);
    }
}




