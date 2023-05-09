package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: FloorReportResultMessage
 * @Description: 楼层举报结果反馈消息
 * @author: WAN
 * @date: 2023/5/9 23:03
 */
public class FloorReportResultMessage extends Message {
    public FloorReportResultMessage(int floor_id,
                                    int u_id,
                                    boolean accept) {
        this.setStatus(0);
        this.setPara(floor_id + ";" + (accept ? "成功" : "失败"));
        this.setType(12);
        this.setUreceiveId(u_id);
    }
}
