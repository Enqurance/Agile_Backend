package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: UserPostReportMessage
 * @Description: 用户帖子被举报消息
 * @author: WAN
 * @date: 2023/5/20 17:23
 */
public class UserPostReportMessage extends Message {
    public UserPostReportMessage(int post_id, int type, String reason, int ureceive_id) {
        this.setContent(reason);
        this.setStatus(0);
        this.setPara(post_id + ";" + type);
        this.setType(16);
        this.setUreceiveId(ureceive_id);
    }
}
