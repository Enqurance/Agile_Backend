package com.example.backend.controller;

import com.example.backend.domain.Post;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forum/post")
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping("/addPost")
    public CommonResult addPost(@RequestParam(name = "id") Integer id,
                                @RequestParam(value = "tag") Integer tag,
                                @RequestParam(value = "pinIdStr") String pinIdStr,
                                @RequestParam(value = "title") String title,
                                @RequestParam(value = "content") String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setVisibility(1);
        post.setTag(tag);
        post.setThumbsUp(0);
        post.setVisit(0);
        post.setFloorNum(0);
        post.setPinIdStr(pinIdStr);
        post.setUserId(id);
        int ret = postService.addPost(post);
        if (ret == 0)
            return CommonResult.failed("post数据插入失败");
        else
            return CommonResult.success("post数据插入成功");
    }
}
