package com.example.backend.service;

import com.example.backend.domain.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Sisyphus
* @description 针对表【post】的数据库操作Service
* @createDate 2023-05-11 23:35:30
*/
public interface PostService extends IService<Post> {
    int addPost(Post post);

    Post getPostById(Integer id);

    int updatePost(Post post);

    int deletePostById(Integer id);

    int setPostVisById(Integer id, Integer visibility);
}
