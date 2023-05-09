package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: LikeMessage
 * @Description: 用户点赞消息
 * @author: WAN
 * @date: 2023/5/9 10:06
 */
public class LikeMessage extends Message {

    /**
     *
     * @param post_id       被点赞帖子的id
     * @param usend_id      点赞用户的id
     * @param ureceive_id   接受该消息的用户id
     */
    public LikeMessage(int post_id, int usend_id, int ureceive_id) {
        this.setStatus(0);
        this.setPara(post_id + ";" + usend_id);
        this.setType(1);
        this.setUreceiveId(ureceive_id);
    }
}
