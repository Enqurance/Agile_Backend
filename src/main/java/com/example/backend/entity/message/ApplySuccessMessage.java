package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: ApplySuccessMessage
 * @Description: 用户申请成功消息
 * @author: WAN
 * @date: 2023/5/9 14:34
 */
public class ApplySuccessMessage extends Message {
    /**
     *
     * @param p_id          申请为公开的地图钉id
     * @param ureceive_id   消息接受者的id
     */
    public ApplySuccessMessage(int p_id, int ureceive_id) {
        this.setStatus(0);
        this.setPara(String.valueOf(p_id));
        this.setType(3);
        this.setUreceiveId(ureceive_id);
    }
}
