package com.example.backend.service;

import com.example.backend.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author DELL
* @description 针对表【message】的数据库操作Service
* @createDate 2023-05-09 15:00:28
*/
public interface MessageService extends IService<Message> {
    int insertMessage(Message message);

    int deleteMessage(int m_id);

    int readMessage(int m_id);

    List<Message> getAllReceive(int u_id);

    List<Message> getAllSend(int u_id);

    int readAllReceive(int u_id);

    int readAllSend(int u_id);

    int deleteAllReceive(int u_id);

    int deleteAllSend(int u_id);
}
