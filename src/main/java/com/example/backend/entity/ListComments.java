package com.example.backend.entity;

import com.example.backend.domain.Comment;
import lombok.Data;

import java.util.List;

@Data
public class ListComments {
    private List<Comment> retComments;
    private Integer length;

    public ListComments(List<Comment> retComments, Integer length) {
        this.retComments = retComments;
        this.length = length;
    }
}
