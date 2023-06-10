package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: CommentReportResultMessage
 * @Description: 评论举报结果反馈消息
 * @author: WAN
 * @date: 2023/5/9 22:42
 */
public class CommentReportResultMessage extends Message {
    /**
     *
     * @param comment_id    评论id
     * @param u_id          举报该评论的用户的id
     * @param accept        管理员是否接受该举报
     */
    public CommentReportResultMessage(int comment_id,
                                      int u_id,
                                      boolean accept) {
        this.setStatus(0);
        this.setPara(comment_id + ";" + (accept ? 1 : 0));
        this.setType(13);
        this.setUreceiveId(u_id);
    }
}
