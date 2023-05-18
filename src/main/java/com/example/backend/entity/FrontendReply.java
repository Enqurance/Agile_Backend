package com.example.backend.entity;

import com.example.backend.domain.Comment;
import com.example.backend.domain.Floor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: FrontendReply
 * @Description: 返回给前端的楼层和评论的统一举报数据模型
 * @author: WAN
 * @date: 2023/5/18 21:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontendReply {
    private int id;

    private String content;

    private int type;

    private int layer;

    private int post_id;

    private List<String> reasons = new ArrayList<>();

    public FrontendReply(Floor floor) {
        this.id = floor.getId();
        this.content = floor.getContent();
        this.type = 0;
        this.layer = floor.getLayers();
        this.post_id = floor.getPostId();
    }

    public FrontendReply(Comment comment, Floor floor) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.type = 1;
        this.layer = floor.getLayers();
        this.post_id = floor.getPostId();
    }

    public void addReason(String reason) {
        this.reasons.add(reason);
    }
}
