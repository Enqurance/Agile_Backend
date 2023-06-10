package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: CommentReportMessage
 * @Description: 评论被举报消息
 * @author: WAN
 * @date: 2023/5/9 14:47
 */
public class CommentReportMessage extends Message {
    /**
     * @param reason     举报理由
     * @param comment_id 评论id
     * @param usend_id   举报用户id
     */
    public CommentReportMessage(String reason, int comment_id, int usend_id) {
        this.setContent(reason);
        this.setStatus(0);
        this.setPara(comment_id + ";" + usend_id);
        this.setType(8);
        this.setUreceiveId(0);  // 表示管理员接收
    }
}
