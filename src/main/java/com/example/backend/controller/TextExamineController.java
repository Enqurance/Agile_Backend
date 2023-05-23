package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.entity.message.PinApplyResultMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.ExamineService;
import com.example.backend.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/TextExamine")
public class TextExamineController {
    @Autowired
    ExamineService examineService;

    @PostMapping("/getExamineReturn")
    public CommonResult getExamineReturn(@RequestBody JSONObject object) {
        log.info(String.valueOf(object));
        return CommonResult.success("success");
    }
}
