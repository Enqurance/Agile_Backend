package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    int findMaxId();

    int setFloorNum(Integer postId, int layers);

    List<Post> getUserExaminePosts(@Param("u_id") int u_id);
}




