package com.example.backend.entity;

import com.example.backend.domain.Post;
import lombok.Data;

import java.util.List;

@Data
public class ListPosts {
    private List<Post> retPosts;
    private Integer length;

    public ListPosts(List<Post> retPosts, Integer length) {
        this.retPosts = retPosts;
        this.length = length;
    }
}
