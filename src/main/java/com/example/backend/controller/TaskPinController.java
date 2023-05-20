package com.example.backend.controller;

import com.example.backend.entity.message.PinApplySuccessMessage;
import com.example.backend.entity.message.PinTaskMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.TpinService;
import com.example.backend.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TaskPinController
 * @Description: 针对地图钉申请公开任务提供api
 * @author: WAN
 * @date: 2023/5/13 11:02
 */
@RestController
@PreAuthorize("hasAuthority('USER')")
public class TaskPinController {
    @Autowired
    private TpinService tpinService;

    @GetMapping("/examine/get_pin_state/{p_id}")
    public CommonResult getPinState(@PathVariable Integer p_id) {
        return CommonResult.success(tpinService.pinState(p_id));
    }

    @GetMapping("/examine/apply_for_public/{p_id}")
    public CommonResult applyPublic(@PathVariable Integer p_id,
                                    @RequestParam Integer id) {
        int result = tpinService.insertTask(p_id);
        if (result != 1) {
            return CommonResult.failed("地图钉申请公开失败，请联系管理员");
        }

        // 给用户发送地图钉成功发出申请消息
        MessageUtil.newMessage(new PinApplySuccessMessage(p_id, id));

        // 给管理员发送地图钉公开处理消息
        MessageUtil.newMessage(new PinTaskMessage(p_id));
        return CommonResult.success(null);
    }
}
