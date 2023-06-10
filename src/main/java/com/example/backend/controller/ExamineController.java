package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.domain.Pin;
import com.example.backend.entity.FrontendTpin;
import com.example.backend.entity.message.PinApplyResultMessage;
import com.example.backend.entity.message.PinFeedbackResultMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PinService;
import com.example.backend.service.TfeedbackService;
import com.example.backend.service.TpinService;
import com.example.backend.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className: ExamineController
 * @Description: 提供检查所有地图钉的api
 * @author: WAN
 * @date: 2023/5/15 11:28
 */
@RestController
@RequestMapping("/examine")
@PreAuthorize("hasAuthority('ADMIN')")
public class ExamineController {
    @Autowired
    private TpinService tpinService;

    @Autowired
    private TfeedbackService tfeedbackService;

    @Autowired
    private PinService pinService;

    @GetMapping("/get_pin")
    public CommonResult getAllTaskPin() {
        List<FrontendTpin> tpins = new ArrayList<>();

        // 加入所有公开申请的地图钉
        tpinService.findAllTasks().forEach(
                tpin -> {
                    Pin pin = pinService.getPinById(tpin.getPId());
                    tpins.add(new FrontendTpin(
                            pin.getId(),
                            pin.getName(),
                            pin.getType(),
                            pin.getVisibility(),
                            Arrays.stream(pin.getLnglat().split(";"))
                                    .map(Double::parseDouble)
                                    .toArray(Double[]::new)
                    ));
                });

        // 加入所有反馈申请的地图钉
        tfeedbackService.findAllPin().forEach(
                tfeedback -> {
                    Pin pin = pinService.getPinById(tfeedback.getPId());
                    tpins.add(new FrontendTpin(
                            pin.getId(),
                            pin.getName(),
                            pin.getType(),
                            pin.getVisibility(),
                            Arrays.stream(pin.getLnglat().split(";"))
                                    .map(Double::parseDouble)
                                    .toArray(Double[]::new)
                    ));
                }
        );

        return CommonResult.success(tpins);
    }

    @PostMapping("/result_of_pin/{p_id}")
    public CommonResult finishTaskPin(@PathVariable Integer p_id,
                                      @RequestBody JSONObject object) {
        boolean accept = object.getBool("result");
        String info = object.getStr("info");

        if (accept) {
            // 将地图钉修改为公开
            pinService.pinPublic(p_id);
        }

        MessageUtil.newMessage(new PinApplyResultMessage(info, p_id,
                pinService.getPinById(p_id).getUser_id(), accept));

        if (tpinService.deletePin(p_id) != 1) {
            throw new RuntimeException("完成地图钉公开任务失败");
        }
        return CommonResult.success(null);
    }

    @PostMapping("/result_of_feedback/{p_id}")
    public CommonResult finishFeedback(@PathVariable Integer p_id,
                                       @RequestBody JSONObject object) {
        List<Integer> feedback_id_list = object.getJSONArray("feedback_id_list").toList(Integer.class);
        String info = object.getStr("info");

        // 给发起反馈的用户发送消息
        for (Integer feedback_id : feedback_id_list) {
            MessageUtil.newMessage(new PinFeedbackResultMessage(info, p_id,
                    tfeedbackService.findFeedbackById(feedback_id).getUId()));
        }

        if (tfeedbackService.finishFeedbacks(feedback_id_list, info) != 1) {
            throw new RuntimeException("完成反馈失败");
        }

        return CommonResult.success(null);
    }
}
