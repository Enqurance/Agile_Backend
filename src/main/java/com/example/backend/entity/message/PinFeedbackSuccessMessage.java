package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: PinFeedbackSuccessMessage
 * @Description: 用户反馈地图钉消息
 * @author: WAN
 * @date: 2023/5/15 19:21
 */
public class PinFeedbackSuccessMessage extends Message {
    /**
     * @param p_id        反馈的地图钉的id
     * @param ureceive_id 消息接收者的id
     */
    public PinFeedbackSuccessMessage(int p_id, int ureceive_id) {
        this.setStatus(0);
        this.setPara(String.valueOf(p_id));
        this.setType(14);
        this.setUreceiveId(ureceive_id);
    }
}
