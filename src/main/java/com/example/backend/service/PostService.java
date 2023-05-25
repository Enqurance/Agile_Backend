package com.example.backend.service;

import com.example.backend.domain.Post;
import com.baomidou.mybatisplus.extension.service.IService;

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

    void setPostVisById(Integer id, Integer visibility);

    int findMaxId();

    int setFloorNum(Integer postId, int layers);

    List<Post> getMyAllPost(Integer id);

    int setPostThumb(Integer post_id, Integer thumbNum);

    int addLike(Integer user_id, Integer post_id);

    int addVisit(int post_id);
}
