package com.example.backend.entity;

import com.example.backend.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: FrontendReportPost
 * @Description: 返回给前端的帖子举报数据模型
 * @author: WAN
 * @date: 2023/5/18 21:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontendReportPost {
    private int id;

    private String title;

    private String content;

    List<String> reasons = new ArrayList<>();

    public FrontendReportPost(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public void addReason(String reason) {
        this.reasons.add(reason);
    }
}
