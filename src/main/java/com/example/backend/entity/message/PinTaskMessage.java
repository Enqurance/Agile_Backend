package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: PinTaskMessage
 * @Description: 地图钉申请为公开任务消息
 * @author: WAN
 * @date: 2023/5/9 14:37
 */
public class PinTaskMessage extends Message {
    /**
     *
     * @param p_id  地图钉id
     *
     * 接收者统一设置为0，表示管理员消息
     */
    public PinTaskMessage(int p_id) {
        this.setStatus(0);
        this.setPara(String.valueOf(p_id));
        this.setType(4);
        this.setUreceiveId(0);  // 表示管理员接收
    }
}
