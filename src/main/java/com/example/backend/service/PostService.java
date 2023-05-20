package com.example.backend.service;

import com.example.backend.domain.Post;
import com.baomidou.mybatisplus.extension.service.IService;
import com.google.gson.JsonObject;

import java.util.List;

/**
* @author Sisyphus
* @description 针对表【post】的数据库操作Service
* @createDate 2023-05-11 23:35:30
*/
public interface PostService extends IService<Post> {
    int addPost(Post post);

    Post getPostById(Integer id);

    List<Post> getPostsOrderTime(Integer type);

    List<Post> searchPosts(String context);

    int updatePost(Post post);

    int deletePostById(Integer id);

    int setPostVisById(Integer id, Integer visibility);
}
