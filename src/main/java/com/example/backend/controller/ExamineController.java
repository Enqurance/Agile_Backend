package com.example.backend.controller;

import com.example.backend.domain.Pin;
import com.example.backend.entity.FrontendTpin;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PinService;
import com.example.backend.service.TfeedbackService;
import com.example.backend.service.TpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
