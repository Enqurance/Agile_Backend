package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: ReleasePostSuccessMessage
 * @Description: 用户发布帖子成功消息
 * @author: WAN
 * @date: 2023/5/9 22:40
 */
public class PostReleaseSuccessMessage extends Message {
    /**
     *
     * @param post_id   帖子id
     * @param u_id      发布帖子用户id
     */
    public PostReleaseSuccessMessage(int post_id, int u_id) {
        this.setStatus(0);
        this.setPara(String.valueOf(post_id));
        this.setType(4);
        this.setUreceiveId(u_id);
    }
}
