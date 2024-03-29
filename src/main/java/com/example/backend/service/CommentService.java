package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.domain.Comment;

import java.util.List;

/**
* @author Sisyphus
* @description 针对表【comment】的数据库操作Service
* @createDate 2023-05-11 23:35:55
*/
public interface CommentService extends IService<Comment> {
    int addComment(Comment comment);

    Comment getCommentById(Integer id);

    int deleteCommentById(Integer id);

//    int deleteCommentByFloorId(Integer floorId);

    int findMaxId();

    List<Comment> getCommentsOrderTime(Integer floorId);

    List<Comment> getMyAllComment(Integer id);

    List<Comment> getAllCommentByFloorId(int floor_id);
}
