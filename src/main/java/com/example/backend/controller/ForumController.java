package com.example.backend.controller;

import com.example.backend.domain.Forum;
import com.example.backend.result.CommonResult;
import com.example.backend.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumController {
    @Autowired
    ForumService forumService;

    @RequestMapping("/forum/insert")
    public CommonResult insertForum(@RequestBody Forum forum) {
        int ret = forumService.insertForum(forum);
        if (ret == 0)
            return CommonResult.failed("forum数据插入失败");
        else
            return CommonResult.success(null);
    }
}
