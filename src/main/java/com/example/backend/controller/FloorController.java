package com.example.backend.controller;

import com.example.backend.domain.Comment;
import com.example.backend.domain.Floor;
import com.example.backend.domain.Post;
import com.example.backend.entity.ListFloors;
import com.example.backend.entity.message.ReplyMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.CommentService;
import com.example.backend.service.ExamineService;
import com.example.backend.service.FloorService;
import com.example.backend.service.PostService;
import com.example.backend.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/forum/floor")
public class FloorController {
    @Autowired
    ExamineService examineService;
    @Autowired
    PostService postService;
    @Autowired
    FloorService floorService;
    @Autowired
    CommentService commentService;

    @RequestMapping("/addFloor")
    public CommonResult addFloor(@RequestParam(name = "id") Integer id,
                                 @RequestParam(value = "post_id") Integer post_id,
                                 @RequestParam(value = "floor_content") String content) {
        Floor floor = new Floor();
        floor.setContent(content);
        floor.setUserId(id);
        Post post = postService.getPostById(post_id);
        if (post == null)
            return CommonResult.failed("不存在id = " + post_id + "的post");
        floor.setPostId(post_id);
        floor.setCreateTime(new Date());
        int layers = postService.getPostById(post_id).getFloorNum() + 1;
        int result = postService.setFloorNum(post_id, layers);
        if (result == 0)
            return CommonResult.failed("post楼层增加失败");

        floor.setLayers(layers);

        int ret = floorService.addFloor(floor);
        if (ret == 0) {
            return CommonResult.failed("floor数据插入失败");
        } else {
            int floorId = floorService.findMaxId();
            examineService.upload("floor", floor.getContent(), Integer.toString(floorId));

            // 给帖子用户发送被回复消息
            MessageUtil.newMessage(new ReplyMessage(content, floorId, id,
                    postService.getPostById(post_id).getUserId()));

            return CommonResult.success(floorId);
        }
    }

    @RequestMapping("/getFloors")
    public CommonResult getFloors(@RequestParam(name = "id", required = false) Integer id,
                                  @RequestParam(value = "post_id") Integer post_id,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "limit") Integer limit) {
        List<Floor> floors = floorService.getFloorsOrderTime(post_id);
        if (floors.size() == 0)
            return CommonResult.failed("不存在满足条件的floor");
        int cnt = 0;
        List<Floor> retFloors = new ArrayList<>();
        for (int index = offset; index + cnt < floors.size() && cnt < limit; cnt++) {
            Floor floor = floors.get(index + cnt);
            List<Comment> comments = commentService.getCommentsOrderTime(floor.getId());
            if (comments.size() != 0)
                floor.setComment_cases(comments.get(0));
            if (Objects.equals(floor.getUserId(), id))
                floor.setIs_auth(1);
            else
                floor.setIs_auth(0);
            retFloors.add(floor);
        }
        ListFloors listFloors = new ListFloors(retFloors, floors.size());
        return CommonResult.success(listFloors);
    }

    @DeleteMapping("/deleteFloor/{floor_id}")
    public CommonResult deleteFloor(@PathVariable(value = "floor_id", required = false) Integer floor_id) {
        if (floorService.deleteFloorById(floor_id) == 0)
            return CommonResult.failed("对应floor删除失败或floor不存在");
        else
            return CommonResult.success("floor楼层删除成功");
    }
}
