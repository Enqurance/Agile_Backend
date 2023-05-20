package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: RectifyResultMessage
 * @Description: 用户整改结果消息
 * @author: WAN
 * @date: 2023/5/20 17:24
 */
public class RectifyResultMessage extends Message {
    public RectifyResultMessage(int post_id, int type, String reason, int ureceive_id) {
        this.setContent(reason);
        this.setStatus(0);
        this.setPara(post_id + ";" + type);
        this.setType(19);
        this.setUreceiveId(ureceive_id);
    }
}
