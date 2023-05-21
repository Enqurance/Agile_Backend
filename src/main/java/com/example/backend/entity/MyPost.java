package com.example.backend.entity;

import lombok.Data;

@Data
public class MyPost {
    private Integer id;
    private String title;
    private String content;
    private Integer floor_num;
    private Boolean post_state;

    public MyPost(Integer id, String title, String content, Integer floor_num, Boolean post_state) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.floor_num = floor_num;
        this.post_state = post_state;
    }
}
