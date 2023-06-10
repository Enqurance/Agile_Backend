package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: PostReportResultMessage
 * @Description: 帖子举报结果反馈消息
 * @author: WAN
 * @date: 2023/5/9 22:41
 */
public class PostReportResultMessage extends Message {
    /**
     *
     * @param post_id   帖子id
     * @param u_id      举报该帖子的用户的id
     * @param accept    管理员是否接受了该举报
     */
    public PostReportResultMessage(int post_id,
                                   int u_id,
                                   boolean accept) {
        this.setStatus(0);
        this.setPara(post_id + ";" + (accept ? 1 : 0));
        this.setType(11);
        this.setUreceiveId(u_id);
    }
}
