package com.example.backend.utils;

import com.example.backend.domain.Message;
import com.example.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: MessageUtil
 * @Description: 在项目里提供全局新增消息的方法
 * @author: WAN
 * @date: 2023/5/20 14:41
 */
@Component
public class MessageUtil {
    private static MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        MessageUtil.messageService = messageService;
    }

    public static void newMessage(Message message) {
        messageService.insertMessage(message);
    }
}
