package com.example.backend.controller;

import com.example.backend.domain.Post;
import com.example.backend.entity.FrontendMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/InfoPage/MyPost")
public class MyPostController {
    @Autowired
    PostService postService;

    @GetMapping("/getMyAllPost")
    public CommonResult getMyAllPost(@RequestParam(name = "id") Integer id) {
        return null;
    }

    @RequestMapping("/deletePostById")
    public CommonResult deletePostById(@RequestParam(name = "id") Integer id) {
        return null;
    }
    @RequestMapping("/changePostById")
    public CommonResult changePostById() {
        return null;
    }

    @GetMapping("/getMyBadPost")
    public CommonResult getMyBadPost(@RequestParam(name = "id") Integer id) {
        return null;
    }
}
