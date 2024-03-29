package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Sisyphus
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2023-05-11 23:35:55
* @Entity com.example.backend.domain.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {
    int insertComment(Comment comment);

    Comment getCommentById(Integer id);

    int deleteById(@Param("id") Integer id);

    int findMaxId();

    List<Comment> getCommentsOrderTime(Integer floorId);

    int deleteCommentByFloorId(Integer floorId);

    List<Comment> getMyAllComment(Integer id);

    List<Comment> getAllByFloorId(@Param("floorId") Integer floorId);
}




