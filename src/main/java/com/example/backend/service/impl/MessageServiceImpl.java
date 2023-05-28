package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Message;
import com.example.backend.mapper.MessageMapper;
import com.example.backend.service.MessageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @description 针对表【message】的数据库操作Service实现
 * @createDate 2023-05-09 15:00:28
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    private static final List<Integer> sendType =
            Arrays.asList(3, 14);

    private static final List<Integer> receiveType =
            Arrays.asList(1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20);

    @Override
    public int insertMessage(Message message) {
        if (message.getTime() == null) {
            message.setTime(new Date());
        }
        return messageMapper.insertAll(message);
    }

    @Override
    public int deleteMessage(int m_id) {
        return messageMapper.deleteById(m_id);
    }

    @Override
    public int readMessage(int m_id) {
        return messageMapper.updateStatusById(m_id, 1);
    }

    @Override
    public List<Message> getAllReceive(int u_id) {
        return messageMapper.getAllByUreceiveIdAndTypeIn(u_id, receiveType);
    }

    @Override
    public List<Message> getAllSend(int u_id) {
        return messageMapper.getAllByUreceiveIdAndTypeIn(u_id, sendType);
    }

    @Override
    public int readAllReceive(int u_id) {
        return messageMapper.updateStatusByUreceiveIdAndTypeIn(1, u_id, receiveType) == 0 ? 0 : 1;
    }

    @Override
    public int readAllSend(int u_id) {
        return messageMapper.updateStatusByUreceiveIdAndTypeIn(1, u_id, sendType) == 0 ? 0 : 1;
    }

    @Override
    public int deleteAllReceive(int u_id) {
        return messageMapper.deleteByUreceiveIdAndTypeIn(u_id, receiveType);
    }

    @Override
    public int deleteAllSend(int u_id) {
        return messageMapper.deleteByUreceiveIdAndTypeIn(u_id, sendType);
    }

}




