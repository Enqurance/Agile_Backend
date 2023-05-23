package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.result.CommonResult;
import com.example.backend.service.ExamineService;
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

    @PostMapping("/getExamineReturn")
    public CommonResult getExamineReturn(@RequestBody JSONObject object) {
        log.info(object.toString());
        return CommonResult.success("success");
    }
}
