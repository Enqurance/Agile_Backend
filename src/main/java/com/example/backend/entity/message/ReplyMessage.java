package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: ReplyMessage
 * @Description: 帖子/楼层/评论被回复消息
 * @author: WAN
 * @date: 2023/5/9 10:27
 */
public class ReplyMessage extends Message {
    /**
     * @param content     回复内容
     * @param floor_id    楼层id
     * @param usend_id    回复用户id
     * @param ureceive_id 被回复用户id
     */
    public ReplyMessage(String content,
                        int floor_id,
                        int usend_id,
                        int ureceive_id) {
        this.setContent(content);
        this.setStatus(0);
        this.setPara(floor_id + ";" + usend_id);
        this.setType(2);
        this.setUreceiveId(ureceive_id);
    }
}
