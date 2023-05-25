package com.example.backend.entity.message;

/**
 * @className: MESSTYPE
 * @Description: 消息的类型
 * @author: WAN
 * @date: 2023/5/9 10:09
 */
public enum MESSTYPE {
    NOTICE("通知"),
    APPLY("申请"),
    REPORT("举报"),
    FEEDBACK("反馈"),
    WARING("警告");

    private final String type;

    MESSTYPE(String type) {
        this.type = type;
    }

    public String getType() {
        return "[" + type + "] ";
    }
}
