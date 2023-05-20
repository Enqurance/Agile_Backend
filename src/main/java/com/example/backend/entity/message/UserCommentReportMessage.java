package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: UserCommentReportMessage
 * @Description: 用户评论被举报消息
 * @author: WAN
 * @date: 2023/5/20 17:24
 */
public class UserCommentReportMessage extends Message {
    public UserCommentReportMessage(int comment_id, String reason, int ureceive_id) {
        this.setContent(reason);
        this.setStatus(0);
        this.setPara(String.valueOf(comment_id));
        this.setType(18);
        this.setUreceiveId(ureceive_id);
    }
}
