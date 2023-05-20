package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.domain.Post;
import com.example.backend.domain.Texamine;
import com.example.backend.entity.FrontendExaminePost;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PostService;
import com.example.backend.service.TexamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: TaskExamineController
 * @Description: 针对需要重新审核的帖子提供api
 * @author: WAN
 * @date: 2023/5/20 10:56
 */
@RestController
public class TaskExamineController {
    @Autowired
    private TexamineService texamineService;

    @Autowired
    private PostService postService;

    @GetMapping("/examine/rectify/get_posts/")
    public CommonResult getAllTasks() {
        List<FrontendExaminePost> tasks = new ArrayList<>();
        texamineService.getAllTasks().forEach(texamine -> {
            Post post = postService.getPostById(texamine.getPostId());
            tasks.add(new FrontendExaminePost(post, texamine));
        });

        return CommonResult.success(tasks);
    }

    @PostMapping("/examine/rectify/result_of_rectify_post/{post_id}")
    public CommonResult finishTask(@PathVariable Integer post_id,
                                   @RequestBody JSONObject jsonObject) {
        int result = jsonObject.getInt("result");
        String basis = jsonObject.getStr("basis");

        Texamine texamine = texamineService.getTaskByPostId(post_id);

        boolean accept = true;
        switch (result) {
            case 0 -> {
                // 整改成功，修改帖子内容
                Post post = postService.getPostById(post_id);
                post.setTitle(texamine.getTitle());
                post.setContent(texamine.getContent());
                post.setVisibility(1);
                postService.updatePost(post);

                accept = false;
            }
            case 1 -> {
                // 需要重新整改，将表里内容清空
                texamineService.rectify(post_id, null, null);
            }
            case 2 -> {
                // 删除该帖子
                postService.deletePostById(post_id);
            }
        }

        // TODO 给用户发送消息

        return CommonResult.success(null);
    }
}
