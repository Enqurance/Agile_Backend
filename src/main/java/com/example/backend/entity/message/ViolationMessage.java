package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: ViolationMessage
 * @Description: 用户发布文字违规消息
 * @author: WAN
 * @date: 2023/5/25 19:11
 */
public class ViolationMessage extends Message {
    /**
     *
     * @param type          文字的种类，为字符串，如"帖子"等
     * @param ureceive_id   接受者id
     */
    public ViolationMessage(String type, int ureceive_id) {
        this.setStatus(0);
        this.setPara(type);
        this.setType(20);
        this.setUreceiveId(ureceive_id);
    }
}
