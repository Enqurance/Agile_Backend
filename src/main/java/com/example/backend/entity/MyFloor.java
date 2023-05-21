package com.example.backend.entity;

import lombok.Data;

@Data
public class MyFloor {
    private Integer id;
    private String title;
    private String content;
    private Integer floor;
    private Boolean post_state;

    public MyFloor(Integer id, String title, String content, Integer floor, Boolean post_state) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.floor = floor;
        this.post_state = post_state;
    }
}
