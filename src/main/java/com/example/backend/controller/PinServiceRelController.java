package com.example.backend.controller;

import com.example.backend.domain.PinServiceRel;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PinServiceRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PinServiceRelController {
    @Autowired
    PinServiceRelService pinServiceRelService;

    @RequestMapping("ps-rel/insert")
    public CommonResult insertPinServiceRel(@RequestBody PinServiceRel pinServiceRel) {
        int ret = pinServiceRelService.insertPinServiceRel(pinServiceRel);
        if (ret == 0)
            return CommonResult.failed("pin_service数据插入失败");
        else
            return CommonResult.success(null);
    }
}
