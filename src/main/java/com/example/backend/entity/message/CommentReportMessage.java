package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: CommentReportMessage
 * @Description: 楼层或评论被举报消息
 * @author: WAN
 * @date: 2023/5/9 14:47
 */
public class CommentReportMessage extends Message {
    /**
     *
     * @param reason    举报理由
     * @param floor_id  楼层id
     * @param usend_id  举报用户id
     */
    public CommentReportMessage(String reason, int floor_id, int usend_id) {
        this.setContent(reason);
        this.setStatus(0);
        this.setPara(floor_id + ";" + usend_id);
        this.setType(6);
        this.setUreceiveId(0);  // 表示管理员接收
    }
}
