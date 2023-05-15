package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: ApplySuccessMessage
 * @Description: 用户成功发起地图钉公开申请消息
 * @author: WAN
 * @date: 2023/5/9 14:34
 */
public class PinApplySuccessMessage extends Message {
    /**
     *
     * @param p_id          申请为公开的地图钉id
     * @param ureceive_id   消息接收者的id
     */
    public PinApplySuccessMessage(int p_id, int ureceive_id) {
        this.setStatus(0);
        this.setPara(String.valueOf(p_id));
        this.setType(3);
        this.setUreceiveId(ureceive_id);
    }
}
