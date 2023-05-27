package com.example.backend.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MyComment {
    private Integer id;
    private String title;
    private String content;
    private Integer floor;
    private Boolean post_state;
    private Date createTime;
    private Integer post_id;

    public MyComment(Integer id, String title, String content, Integer floor, Boolean post_state, Date createTime, Integer post_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.floor = floor;
        this.post_state = post_state;
        this.createTime = createTime;
        this.post_id = post_id;
    }
}
