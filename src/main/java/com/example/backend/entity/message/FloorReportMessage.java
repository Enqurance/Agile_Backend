package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: FloorReportMessage
 * @Description: 管理员楼层举报任务处理消息
 * @author: WAN
 * @date: 2023/5/9 23:03
 */
public class FloorReportMessage extends Message {
    public FloorReportMessage(String reason, int floor_id, int usend_id) {
        this.setContent(reason);
        this.setStatus(0);
        this.setPara(floor_id + ";" + usend_id);
        this.setType(7);
        this.setUreceiveId(0);  // 表示管理员接收
    }
}
