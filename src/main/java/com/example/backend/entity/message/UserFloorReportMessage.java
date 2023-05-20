package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: UserFloorReportMessage
 * @Description: 用户楼层被举报消息
 * @author: WAN
 * @date: 2023/5/20 17:23
 */
public class UserFloorReportMessage extends Message {
    public UserFloorReportMessage(int floor_id, String reason, int ureceive_id) {
        this.setContent(reason);
        this.setStatus(0);
        this.setPara(String.valueOf(floor_id));
        this.setType(17);
        this.setUreceiveId(ureceive_id);
    }
}
