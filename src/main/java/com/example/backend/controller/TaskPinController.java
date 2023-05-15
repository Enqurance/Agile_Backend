package com.example.backend.controller;

import com.example.backend.result.CommonResult;
import com.example.backend.service.TpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TaskPinController
 * @Description: 针对地图钉申请公开任务提供api
 * @author: WAN
 * @date: 2023/5/13 11:02
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class TaskPinController {
    @Autowired
    private TpinService tpinService;

    @GetMapping("/examine/get_public_pin_state/{p_id}")
    public CommonResult getPinState(@PathVariable Integer p_id) {
        return CommonResult.success(tpinService.pinState(p_id));
    }

    @GetMapping("/examine/apply_for_public/{p_id}")
    public CommonResult applyPublic(@PathVariable Integer p_id) {
        int result = tpinService.insertTask(p_id);
        if (result != 1) {
            return CommonResult.failed("地图钉申请公开失败，请联系管理员");
        }
        return CommonResult.success(null);
    }
}
