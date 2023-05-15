package com.example.backend.entity.message;

import com.example.backend.domain.Message;

/**
 * @className: PinFeedbackResultMessage
 * @Description: 反馈地图钉结果消息
 * @author: WAN
 * @date: 2023/5/15 19:21
 */
public class PinFeedbackResultMessage extends Message {
    /**
     * @param reason 若拒绝公开申请，表示失败理由；接受为空字符串
     * @param p_id   地图钉id
     * @param u_id   申请公开地图钉的用户id
     * @param accept 管理员是否接受该地图钉公开申请
     */
    public PinFeedbackResultMessage(String reason,
                                    int p_id,
                                    int u_id,
                                    boolean accept) {
        this.setContent(reason);
        this.setStatus(0);
        this.setPara(p_id + ";" + (accept ? 1 : 0));
        this.setType(15);
        this.setUreceiveId(u_id);
    }
}
