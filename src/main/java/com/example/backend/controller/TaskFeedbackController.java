package com.example.backend.controller;

import com.example.backend.domain.Tfeedback;
import com.example.backend.entity.FrontendFeedback;
import com.example.backend.result.CommonResult;
import com.example.backend.service.TfeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: TaskFeedbackController
 * @Description: 针对地图钉反馈提供api
 * @author: WAN
 * @date: 2023/5/15 11:45
 */
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class TaskFeedbackController {
    @Autowired
    private TfeedbackService tfeedbackService;

    @GetMapping("/examine/get_pin_state/{p_id}")
    public CommonResult getPinState(@PathVariable Integer p_id,
                                    @RequestParam(name = "id") int id) {
        return CommonResult.success(tfeedbackService.hasFeedback(p_id, id));
    }

    @GetMapping("/examine/get_feedback")
    public CommonResult getFeedback(@RequestParam(name = "pin_id") int p_id) {
        List<FrontendFeedback> frontendFeedbacks = new ArrayList<>();
        tfeedbackService.findAllPinFeedback(p_id).forEach(
                tfeedback -> frontendFeedbacks.add(
                        new FrontendFeedback(
                                tfeedback.getId(),
                                tfeedback.getTitle(),
                                tfeedback.getContent())
                ));
        return CommonResult.success(frontendFeedbacks);
    }

    @PostMapping("/examine/apply_for_feedback/{p_id}")
    public CommonResult applyFeedback(@PathVariable Integer p_id,
                                      @RequestParam(name = "id") int id,
                                      @RequestBody Tfeedback feedback) {
        if (tfeedbackService.newFeedback(feedback.getTitle(), feedback.getContent(), p_id, id) != 1) {
            throw new RuntimeException("反馈失败，请联系管理员");
        }
        return CommonResult.success(null);
    }
}
