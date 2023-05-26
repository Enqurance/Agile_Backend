package com.example.backend.controller;

import com.example.backend.domain.Pin;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/InfoPage/MyPin")
public class MyPinController {
    @Autowired
    PinService pinService;

    @GetMapping("/getMyAllPin")
    public CommonResult getMyAllPin(@RequestParam(name = "id") Integer id) {
        List<Pin> pins = pinService.getMyAllPin(id);
        if (pins.size() == 0)
            return CommonResult.success("此用户没有创建私有pin");
        return CommonResult.success(pins);
    }
}
