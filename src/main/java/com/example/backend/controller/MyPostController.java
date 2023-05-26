package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.domain.Post;
import com.example.backend.entity.MyPost;
import com.example.backend.entity.message.ExaminePostMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PinService;
import com.example.backend.service.PostService;
import com.example.backend.service.TexamineService;
import com.example.backend.utils.MessageUtil;
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
    PinService pinService;

    @Autowired
    private TexamineService texamineService;

    @GetMapping("/getMyAllPost")
    public CommonResult getMyAllPost(@RequestParam(name = "id") Integer id) {
        List<Post> posts = postService.getMyAllPost(id);
        List<MyPost> myPosts = new ArrayList<>();
        if (posts.size() == 0)
            return CommonResult.success(myPosts);
        for (Post post : posts) {
            boolean state = post.getVisibility() == 1;
            String pinNameStr = pinService.getNameStrByIdStr(post.getPinIdStr());
            MyPost myPost = new MyPost(post.getId(), post.getTitle(), post.getContent(), post.getFloorNum(), state,
                    post.getTag(), post.getThumbsUp(), post.getVisit(), post.getPinIdStr(), pinNameStr, post.getCreateTime());
            myPosts.add(myPost);
        }
        return CommonResult.success(myPosts);
    }

    @PostMapping("/changePostById")
    public CommonResult submitRectify(@RequestBody JSONObject jsonObject) {
        int post_id = jsonObject.getInt("post_id");
        String title = jsonObject.getStr("new_title");
        String content = jsonObject.getStr("new_content");

        if (texamineService.rectify(post_id, title, content) != 1) {
            throw new RuntimeException("整改帖子失败，请检查服务器");
        }

        // 给管理员发送整改帖子任务消息
        MessageUtil.newMessage(new ExaminePostMessage(texamineService.getTaskByPostId(post_id).getId()));

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
            jsonObject.putOpt("tag", post.getTag());
            jsonObject.putOpt("pin_id_str", post.getPinIdStr());
            jsonObject.putOpt("pin_name_str", pinService.getNameStrByIdStr(post.getPinIdStr()));
            jsonObject.putOpt("createTime", post.getCreateTime());
            results.add(jsonObject);
        });
        return CommonResult.success(results);
    }
}
