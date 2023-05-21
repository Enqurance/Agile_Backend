package com.example.backend.controller;

import com.example.backend.domain.Comment;
import com.example.backend.domain.Floor;
import com.example.backend.domain.Post;
import com.example.backend.result.CommonResult;
import com.example.backend.service.CommentService;
import com.example.backend.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/forum/comment")
public class CommentController {
    @Autowired
    FloorService floorService;
    @Autowired
    CommentService commentService;

    @RequestMapping("/addComment")
    public CommonResult addComment(@RequestParam(name = "id") Integer id,
                                   @RequestParam(value = "rcomment_id") Integer rcomment_id,
                                   @RequestParam(value = "floor_id") Integer floor_id,
                                   @RequestParam(value = "c_content") String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCuserId(id);
        comment.setRuserId(rcomment_id);
        Floor floor = floorService.getFloorById(floor_id);
        if (floor == null)
            return CommonResult.failed("不存在id = " + floor_id + "的floor");
        comment.setFloorId(floor_id);
        comment.setCreateTime(new Date());

        int ret = commentService.addComment(comment);
        if (ret == 0)
            return CommonResult.failed("comment数据插入失败");
        else
            return CommonResult.success(commentService.findMaxId());
    }

    @RequestMapping("/getComments")
    public CommonResult getComments(@RequestParam(value = "floor_id") Integer floor_id,
                                    @RequestParam(value = "offset") Integer offset,
                                    @RequestParam(value = "limit") Integer limit) {
        List<Comment> comments = commentService.getCommentsOrderTime(floor_id);
        if (comments.size() == 0)
            return CommonResult.failed("不存在满足条件的comment");
        int cnt = 0;
        List<Comment> retComments = new ArrayList<>();
        for (int index = offset; index + cnt < comments.size() && cnt < limit; cnt++) {
            retComments.add(comments.get(index + cnt));
        }
        return CommonResult.success(retComments);
    }

    @DeleteMapping("/deleteComment/{comment_id}")
    public CommonResult deleteComment(@PathVariable(value = "comment_id", required = false) Integer comment_id) {
        int ret = commentService.deleteCommentById(comment_id);
        if (ret == 0)
            return CommonResult.failed("对应comment删除失败或comment不存在");
        else
            return CommonResult.success("对应comment删除成功");
    }
}