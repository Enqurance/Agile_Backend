package com.example.backend.entity;

import com.example.backend.domain.Message;
import com.example.backend.entity.message.TitleGenerator;
import lombok.Data;

/**
 * @className: FrontendMessage
 * @Description: 返回给前端的消息类型
 * @author: WAN
 * @date: 2023/5/9 15:49
 */
@Data
public class FrontendMessage {
    private int id;
    private String title;
    private String content;
    private boolean read;
    private int post_id;
    private int floor_id;
    private int examine_id;

    public FrontendMessage(Message message) {
        this.id = message.getId();
        this.title = TitleGenerator.generateTitle(message);
        this.content = message.getContent();
        this.read = message.getStatus() == 1;
        switch (message.getType()) {
            case 1, 5 -> this.post_id = Integer.parseInt(message.getPara().split(";")[0]);
            case 2, 6 -> this.floor_id = Integer.parseInt(message.getPara().split(";")[0]);
            case 7 -> this.examine_id = Integer.parseInt(message.getPara());
            default -> {
            }
        }
    }
}
