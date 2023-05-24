package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.result.CommonResult;
import com.example.backend.service.CommentService;
import com.example.backend.service.ExamineService;
import com.example.backend.service.FloorService;
import com.example.backend.service.PostService;
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
                case "post" -> postService.deletePostById(Integer.valueOf(prefix[1]));
                case "floor" -> floorService.deleteFloorById(Integer.valueOf(prefix[1]));
                case "comment" -> commentService.deleteCommentById(Integer.valueOf(prefix[1]));
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
