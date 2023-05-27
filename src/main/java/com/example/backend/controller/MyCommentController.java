package com.example.backend.controller;

import com.example.backend.domain.Comment;
import com.example.backend.domain.Floor;
import com.example.backend.domain.Post;
import com.example.backend.entity.MyComment;
import com.example.backend.entity.MyFloor;
import com.example.backend.result.CommonResult;
import com.example.backend.service.CommentService;
import com.example.backend.service.FloorService;
import com.example.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/InfoPage/MyComment")
public class MyCommentController {
    @Autowired
    PostService postService;
    @Autowired
    FloorService floorService;
    @Autowired
    CommentService commentService;

    @GetMapping("/getMyAllComment")
    public CommonResult getMyAllComment(@RequestParam(name = "id") Integer id) {
        List<Comment> comments = commentService.getMyAllComment(id);
        List<MyComment> myComments = new ArrayList<>();
        if (comments.size() == 0)
            return CommonResult.success(myComments);
        for (Comment comment : comments) {
            boolean state = false;
            Floor floor = floorService.getFloorById(comment.getFloorId());
            Post post = postService.getPostById(floor.getPostId());
            if (post.getVisibility() == 1)
                state = true;

            MyComment myComment = new MyComment(comment.getId(), post.getTitle(), comment.getContent(),
                    floor.getLayers(), state, comment.getCreateTime(), post.getId());
            myComments.add(myComment);
        }
        return CommonResult.success(myComments);
    }
}
