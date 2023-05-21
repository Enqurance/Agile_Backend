package com.example.backend.controller;

import com.example.backend.domain.Floor;
import com.example.backend.domain.Post;
import com.example.backend.entity.MyFloor;
import com.example.backend.result.CommonResult;
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
@RequestMapping("/InfoPage/MyFloor")
public class MyFloorController {
    @Autowired
    PostService postService;
    @Autowired
    FloorService floorService;

    @GetMapping("/getMyAllFloor")
    public CommonResult getMyAllFloor(@RequestParam(name = "id") Integer id) {
        List<Floor> floors = floorService.getMyAllFloor(id);
        if (floors.size() == 0)
            return CommonResult.failed("此用户没有创建楼层");
        List<MyFloor> myFloors = new ArrayList<>();
        for (Floor floor : floors) {
            boolean state = false;
            Post post = postService.getPostById(floor.getPostId());
            if (post.getVisibility() == 1)
                state = true;

            MyFloor myFloor = new MyFloor(id, post.getTitle(), floor.getContent(), floor.getLayers(), state);
            myFloors.add(myFloor);
        }
        return CommonResult.success(myFloors);
    }
}
