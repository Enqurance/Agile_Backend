package com.example.backend.entity;

import com.example.backend.domain.Post;
import lombok.Data;

@Data
public class PostDetail {
    private Post post;
    private Integer is_auth;

    public PostDetail(Post post, Integer is_auth) {
        this.post = post;
        this.is_auth = is_auth;
    }

    public Post getPost() {
        return post;
    }

    public Integer getIs_auth() {
        return is_auth;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setIs_auth(Integer is_auth) {
        this.is_auth = is_auth;
    }
}
