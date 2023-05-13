package com.example.backend.entity;

import com.example.backend.domain.Message;
import com.example.backend.entity.message.TitleGenerator;
import com.example.backend.service.CommentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: FrontendMessage
 * @Description: 返回给前端的消息类型
 * @author: WAN
 * @date: 2023/5/9 15:49
 */
@Data
@Component
public class FrontendMessage {
    private int id;
    private String title;
    private String content;
    private boolean read;
    private int post_id;
    private int floor_id;
    private int examine_id;

    private static CommentService commentService;

    public static FrontendMessage trans2FrontendMessage(Message message) {
        FrontendMessage frontendMessage = new FrontendMessage();
        frontendMessage.setId(message.getId());
        frontendMessage.setTitle(TitleGenerator.generateTitle(message));
        frontendMessage.setContent(message.getContent());
        frontendMessage.setRead(message.getStatus() == 1);
        switch (message.getType()) {
            // 帖子id
            case 1, 4, 6, 11 -> frontendMessage.setPost_id(Integer.parseInt(message.getPara().split(";")[0]));
            // 楼层id
            case 2, 7, 12 -> frontendMessage.setFloor_id(Integer.parseInt(message.getPara().split(";")[0]));
            // 审核id
            case 9 -> frontendMessage.setExamine_id(Integer.parseInt(message.getPara()));
            // 评论id->楼层id
            case 8, 13 -> frontendMessage.setFloor_id(commentService.getCommentById(
                    Integer.parseInt(message.getPara().split(";")[0])
            ).getFloorId());
            default -> {
            }
        }

        return frontendMessage;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        FrontendMessage.commentService = commentService;
    }
}
