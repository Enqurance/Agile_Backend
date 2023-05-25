package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.entity.message.ViolationMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.CommentService;
import com.example.backend.service.ExamineService;
import com.example.backend.service.FloorService;
import com.example.backend.service.PostService;
import com.example.backend.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/TextExamine")
public class TextExamineController {
    @Autowired
    ExamineService examineService;
    @Autowired
    PostService postService;
    @Autowired
    FloorService floorService;
    @Autowired
    CommentService commentService;

    @PostMapping("/getExamineReturn")
    public CommonResult getExamineReturn(@RequestBody JSONObject object) {
        JSONObject data = object.getJSONObject("data");
        String result = data.getStr("result");
        if (result.equals("1")) {
            String url = data.getStr("url");
            String[] prefix = url.replace("https://agile-pic-1313874439.cos.ap-beijing.myqcloud.com/forumText/",
                    "").replace(".txt", "").split("/");
            examineService.delete(prefix[0], prefix[1]);
            switch (prefix[0]) {
                case "post" -> {
                    MessageUtil.newMessage(new ViolationMessage(
                            "帖子",
                            postService.getPostById(Integer.valueOf(prefix[1])).getUserId()
                    ));
                    postService.deletePostById(Integer.valueOf(prefix[1]));
                }
                case "floor" -> {
                    MessageUtil.newMessage(new ViolationMessage(
                            "楼层",
                            floorService.getFloorById(Integer.valueOf(prefix[1])).getUserId()
                    ));
                    floorService.deleteFloorById(Integer.valueOf(prefix[1]));
                }
                case "comment" -> {
                    MessageUtil.newMessage(new ViolationMessage(
                            "评论",
                            commentService.getCommentById(Integer.valueOf(prefix[1])).getCuserId()
                    ));
                    commentService.deleteCommentById(Integer.valueOf(prefix[1]));
                }
            }
            return CommonResult.success("id = " + prefix[1] + "的" + prefix[0] + "包含敏感信息");
        }
        return CommonResult.success("此文本不包含敏感信息");
    }

//    @PostMapping("/upload")
//    public CommonResult upload(@RequestBody JSONObject object) {
//        String prefix = object.getStr("prefix");
//        String context = object.getStr("context");
//        String id = object.getStr("id");
//        examineService.Upload(prefix, context, id);
//        return CommonResult.success("success");
//    }
//
//    @PostMapping("/delete")
//    public CommonResult delete(@RequestBody JSONObject object) {
//        String prefix = object.getStr("prefix");
//        String id = object.getStr("id");
//        examineService.delete(prefix, id);
//        return CommonResult.success("success");
//    }
}
