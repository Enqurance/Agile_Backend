package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PostService;
import com.example.backend.service.TexamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/InfoPage/MyPost")
public class MyPostController {
    @Autowired
    PostService postService;

    @Autowired
    private TexamineService texamineService;

    @GetMapping("/getMyAllPost")
    public CommonResult getMyAllPost(@RequestParam(name = "id") Integer id) {
        return null;
    }

    @RequestMapping("/deletePostById")
    public CommonResult deletePostById(@RequestParam(name = "id") Integer id) {
        return null;
    }

    @PostMapping("/changePostById")
    public CommonResult submitRectify(@RequestBody JSONObject jsonObject) {
        int post_id = jsonObject.getInt("post_id");
        String title = jsonObject.getStr("new_title");
        String content = jsonObject.getStr("new_content");

        if (texamineService.rectify(post_id, title, content) != 1) {
            throw new RuntimeException("整改帖子失败，请检查服务器");
        }

        return CommonResult.success(null);
    }

    @GetMapping("/getMyBadPost")
    public CommonResult getMyBadPost(@RequestParam(name = "id") Integer id) {
        List<JSONObject> results = new ArrayList<>();
        texamineService.getUserExaminePosts(id).forEach(post -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.putOpt("id", post.getId());
            jsonObject.putOpt("title", post.getTitle());
            jsonObject.putOpt("content", post.getContent());

            jsonObject.putOpt("reason", texamineService.getTaskByPostId(post.getId()).getBasis());

            results.add(jsonObject);
        });
        return CommonResult.success(results);
    }
}
