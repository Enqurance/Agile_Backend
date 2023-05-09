package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: ExaminePostMessage
 * @Description: 审核帖子消息
 * @author: WAN
 * @date: 2023/5/9 14:49
 */
public class ExaminePostMessage extends Message {
    /**
     *
     * @param texamine_id   审核帖子任务表中任务id
     */
    public ExaminePostMessage(int texamine_id) {
        this.setStatus(0);
        this.setPara(String.valueOf(texamine_id));
        this.setType(7);
        this.setUreceiveId(0);  // 表示管理员接收
    }
}
