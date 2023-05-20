package com.example.backend.mapper;

import com.example.backend.domain.Pin;
import com.example.backend.domain.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

/**
* @author Sisyphus
* @description 针对表【post】的数据库操作Mapper
* @createDate 2023-05-11 23:35:30
* @Entity com.example.backend.domain.Post
*/
public interface PostMapper extends BaseMapper<Post> {
    int insertPost(Post post);

    Post getPostById(Integer id);

    List<Post> getPostsOrderTime(Integer type);

    List<Post> searchPosts(String context);

    int updatePost(Post post);

    int deletePostById(Integer id);

    int setPostVisById(Integer id, Integer visibility);
}




