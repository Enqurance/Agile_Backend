package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Post;
import com.example.backend.service.PostService;
import com.example.backend.mapper.PostMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【post】的数据库操作Service实现
* @createDate 2023-05-11 23:35:30
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{
    @Resource
    PostMapper postMapper;

    @Override
    public int addPost(Post post) {
        return postMapper.insertPost(post);
    }

    @Override
    public Post getPostById(Integer id) {
        return postMapper.getPostById(id);
    }

    @Override
    public int updatePost(Post post) {
        return postMapper.updatePost(post);
    }

    @Override
    public int deletePostById(Integer id) {
        return postMapper.deletePostById(id);
    }

    @Override
    public int setPostVisById(Integer id, Integer visibility) {
        return postMapper.setPostVisById(id, visibility);
    }
}




