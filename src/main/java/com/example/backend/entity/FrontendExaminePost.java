package com.example.backend.entity;

import com.example.backend.domain.Post;
import com.example.backend.domain.Texamine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: FrontendExaminePost
 * @Description: 返回给前端的整改帖子数据模型
 * @author: WAN
 * @date: 2023/5/20 11:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontendExaminePost {
    private int id;

    private String old_title;

    private String old_content;

    private String basis;

    private String new_title;

    private String new_content;

    public FrontendExaminePost(Post post, Texamine texamine) {
        this.id = post.getId();
        this.old_title = post.getTitle();
        this.old_content = post.getContent();
        this.basis = texamine.getBasis();
        this.new_title = texamine.getTitle();
        this.new_content = texamine.getContent();
    }
}
