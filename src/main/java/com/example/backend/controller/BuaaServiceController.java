package com.example.backend.controller;

import com.example.backend.domain.BuaaService;
import com.example.backend.result.CommonResult;
import com.example.backend.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuaaServiceController {
    @Autowired
    ServiceService serviceService;

    @RequestMapping("/service/insert")
    public CommonResult insertService(@RequestBody BuaaService buaaService) {
        int ret = serviceService.insertService(buaaService);
        if (ret == 0)
            return CommonResult.failed("service数据插入失败");
        else
            return CommonResult.success(null);
    }
}
