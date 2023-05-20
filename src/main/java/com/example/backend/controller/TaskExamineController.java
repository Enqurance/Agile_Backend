package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.domain.Post;
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

    @PostMapping("/examine/rectify/result_of_rectify_post/{p_id}")
    public CommonResult finishTask(@PathVariable Integer p_id,
                                   @RequestBody JSONObject jsonObject) {
        // TODO
        return CommonResult.success(null);
    }
}
