package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: FrontendFeedback
 * @Description: 返回给前端的反馈数据模型
 * @author: WAN
 * @date: 2023/5/15 12:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontendFeedback {
    private Integer feedback_id;
    private String title;
    private String feedback;
}
