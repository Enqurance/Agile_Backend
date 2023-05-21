package com.example.backend.controller;

import com.example.backend.domain.Floor;
import com.example.backend.domain.Post;
import com.example.backend.result.CommonResult;
import com.example.backend.service.CommentService;
import com.example.backend.service.FloorService;
import com.example.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/forum/floor")
public class FloorController {
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
        if (ret == 0)
            return CommonResult.failed("floor数据插入失败");
        else
            return CommonResult.success(floorService.findMaxId());
    }

    @RequestMapping("/getFloors")
    public CommonResult getFloors(@RequestParam(value = "post_id") Integer post_id,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "limit") Integer limit) {
        List<Floor> floors = floorService.getFloorsOrderTime(post_id);
        if (floors.size() == 0)
            return CommonResult.failed("不存在满足条件的floor");
        int cnt = 0;
        List<Floor> retFloors = new ArrayList<>();
        for (int index = offset; index + cnt < floors.size() && cnt < limit; cnt++) {
            retFloors.add(floors.get(index + cnt));
        }
        return CommonResult.success(retFloors);
    }

    @DeleteMapping("/deleteFloor/{floor_id}")
    public CommonResult deleteFloor(@PathVariable(value = "floor_id", required = false) Integer floor_id) {
        int retComment = commentService.deleteCommentByFloorId(floor_id);
        if (retComment == 0)
            return CommonResult.failed("对应floor关联comment删除失败");
        int retFloor = floorService.deleteFloorById(floor_id);
        if (retFloor == 0)
            return CommonResult.failed("对应floor删除失败或floor不存在");
        else
            return CommonResult.success("floor楼层删除成功");
    }
}
