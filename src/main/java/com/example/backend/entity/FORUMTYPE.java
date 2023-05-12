package com.example.backend.entity;

/**
 * @className: FORUMTYPE
 * @Description: 论坛中文本的类型，可能为帖子、楼层或评论
 * @author: WAN
 * @date: 2023/5/12 20:50
 */
public enum FORUMTYPE {
    POST(1),
    FLOOR(2),
    COMMENT(3);

    private final int type;

    FORUMTYPE(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
