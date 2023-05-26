package com.example.backend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MyPost {
    private Integer id;
    private String title;
    private String content;
    private Integer floor_num;
    private Boolean post_state;
    private Integer tag;
    private Integer thumbs_up;
    private Integer visit;
    private String pin_id_str;
    private Date createTime;

    public MyPost(Integer id, String title, String content, Integer floor_num, Boolean post_state,
                  Integer tag, Integer thumbs_up, Integer visit, String pin_id_str, Date createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.floor_num = floor_num;
        this.post_state = post_state;
        this.tag = tag;
        this.thumbs_up = thumbs_up;
        this.visit = visit;
        this.pin_id_str = pin_id_str;
        this.createTime = createTime;
    }
}
