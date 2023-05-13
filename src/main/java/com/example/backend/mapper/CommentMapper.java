package com.example.backend.mapper;

import com.example.backend.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.domain.Floor;

/**
* @author Sisyphus
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2023-05-11 23:35:55
* @Entity com.example.backend.domain.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {
    int insertComment(Comment comment);

    Comment getCommentById(Integer id);
}




