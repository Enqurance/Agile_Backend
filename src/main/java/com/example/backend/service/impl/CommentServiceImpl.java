package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Comment;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.mapper.CommentMapper;
import com.example.backend.service.CommentService;
import com.example.backend.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Sisyphus
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-05-11 23:35:55
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{
    @Resource
    CommentMapper commentMapper;

    @Autowired
    private ReportService reportService;

    @Override
    public int addComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    public int deleteCommentById(Integer id) {
        reportService.finishReport(id, FORUMTYPE.COMMENT);
        return commentMapper.deleteById(id);
    }

//    @Override
//    public int deleteCommentByFloorId(Integer floorId) {
//        return commentMapper.deleteCommentByFloorId(floorId);
//    }

    @Override
    public int findMaxId() {
        return commentMapper.findMaxId();
    }

    @Override
    public List<Comment> getCommentsOrderTime(Integer floorId) {
        return commentMapper.getCommentsOrderTime(floorId);
    }

    @Override
    public List<Comment> getMyAllComment(Integer id) {
        return commentMapper.getMyAllComment(id);
    }

    @Override
    public List<Comment> getAllCommentByFloorId(int floor_id) {
        return commentMapper.getAllByFloorId(floor_id);
    }

}




