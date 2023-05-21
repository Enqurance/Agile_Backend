package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.domain.Post;
import com.example.backend.domain.Texamine;
import com.example.backend.entity.FrontendExaminePost;
import com.example.backend.entity.message.RectifyResultMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PostService;
import com.example.backend.service.TexamineService;
import com.example.backend.utils.MessageUtil;
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

    @GetMapping("/examine/rectify/get_posts")
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
                                   @RequestBody JSONObject jsonObject,
                                   @RequestParam int id) {
        int result = jsonObject.getInt("result");
        String basis = jsonObject.getStr("basis");

        Texamine texamine = texamineService.getTaskByPostId(post_id);

        switch (result) {
            case 0 -> {
                // 整改成功，修改帖子内容
                Post post = postService.getPostById(post_id);
                post.setTitle(texamine.getTitle());
                post.setContent(texamine.getContent());
                post.setVisibility(1);
                postService.updatePost(post);
                if (texamineService.finishTaskExamine(post_id) != 1) {
                    throw new RuntimeException("删除失败，请检查服务器");
                }
            }
            case 1 -> // 需要重新整改，将表里内容清空
                    texamineService.rectify(post_id, null, null);
            case 2 -> {
                // 删除该帖子
                postService.deletePostById(post_id);
                if (texamineService.finishTaskExamine(post_id) != 1) {
                    throw new RuntimeException("删除失败，请检查服务器");
                }
            }
        }

        // 给用户发送整改结果消息
        MessageUtil.newMessage(new RectifyResultMessage(post_id, result, basis, id));

        return CommonResult.success(null);
    }
}
