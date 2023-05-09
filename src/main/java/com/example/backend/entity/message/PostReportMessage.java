package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: PostReplyMessage
 * @Description: 举报帖子消息
 * @author: WAN
 * @date: 2023/5/9 14:39
 */
public class PostReportMessage extends Message {
    /**
     *
     * @param reason    举报理由
     * @param post_id   被举报帖子id
     * @param usend_id  举报用户id
     */
    public PostReportMessage(String reason, int post_id, int usend_id) {
        this.setContent(reason);
        this.setStatus(0);
        this.setPara(post_id + ";" + usend_id);
        this.setType(5);
        this.setUreceiveId(0);  // 表示管理员接收
    }
}
