package com.example.backend.controller;

import com.example.backend.domain.Comment;
import com.example.backend.domain.Floor;
import com.example.backend.domain.User;
import com.example.backend.entity.ListComments;
import com.example.backend.entity.message.ReplyMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.CommentService;
import com.example.backend.service.ExamineService;
import com.example.backend.service.FloorService;
import com.example.backend.service.UserService;
import com.example.backend.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/forum/comment")
public class CommentController {
    @Autowired
    ExamineService examineService;
    @Autowired
    FloorService floorService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

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
        if (ret == 0) {
            return CommonResult.failed("comment数据插入失败");
        } else {
            Integer commentId = commentService.findMaxId();
            examineService.upload("comment", comment.getContent(), commentId.toString());

            MessageUtil.newMessage(new ReplyMessage(content, floor_id, id,
                    rcomment_id == 0 ? floorService.getFloorById(floor_id).getUserId() : rcomment_id));

            return CommonResult.success(commentId);
        }
    }

    @RequestMapping("/getComments")
    public CommonResult getComments(@RequestParam(name = "id", required = false) Integer id,
                                    @RequestParam(value = "floor_id") Integer floor_id,
                                    @RequestParam(value = "offset") Integer offset,
                                    @RequestParam(value = "limit") Integer limit) {
        List<Comment> comments = commentService.getCommentsOrderTime(floor_id);
        if (comments.size() == 0)
            return CommonResult.failed("不存在满足条件的comment");
        int cnt = 0;
        List<Comment> retComments = new ArrayList<>();
        for (int index = offset; index + cnt < comments.size() && cnt < limit; cnt++) {
            Comment comment = comments.get(index + cnt);
            if (Objects.equals(comment.getCuserId(), id))
                comment.setIs_auth(1);
            else
                comment.setIs_auth(0);
            List<User> cusers = userService.findUserById(comment.getCuserId());
            if (cusers.size() != 0)
                comment.setCuserName(cusers.get(0).getName());
            if (comment.getRuserId() != null && comment.getRuserId() != 0) {
                List<User> rusers = userService.findUserById(comment.getRuserId());
                if (rusers.size() != 0)
                    comment.setRuserName(rusers.get(0).getName());
            }
            retComments.add(comment);
        }
        ListComments listComments = new ListComments(retComments, comments.size());
        return CommonResult.success(listComments);
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
