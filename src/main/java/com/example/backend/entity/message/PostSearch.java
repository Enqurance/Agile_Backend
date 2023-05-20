package com.example.backend.entity.message;

import lombok.Data;

@Data
public class PostSearch {
    private Integer post_id;
    private String post_title;

    public PostSearch(Integer post_id, String post_title) {
        this.post_id = post_id;
        this.post_title = post_title;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }
}
