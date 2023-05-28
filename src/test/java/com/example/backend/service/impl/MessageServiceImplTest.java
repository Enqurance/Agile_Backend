package com.example.backend.service.impl;

import com.example.backend.domain.Message;
import com.example.backend.entity.message.LikeMessage;
import com.example.backend.entity.message.PinApplySuccessMessage;
import com.example.backend.entity.message.ReplyMessage;
import com.example.backend.mapper.MessageMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class MessageServiceImplTest {
    @Mock
    MessageMapper messageMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    MessageServiceImpl messageServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertMessage() {
        when(messageMapper.insertAll(any())).thenReturn(1);

        int result = messageServiceImpl.insertMessage(new Message());
        Assertions.assertEquals(1, result);
    }

    @Test
    void testDeleteMessage() {
        when(messageMapper.deleteById(anyInt())).thenReturn(1);

        int result = messageServiceImpl.deleteMessage(0);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testReadMessage() {
        when(messageMapper.updateStatusById(anyInt(), anyInt())).thenReturn(1);

        int result = messageServiceImpl.readMessage(1);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testGetAllReceive() {
        Message m1 = new LikeMessage(1, 7, 0);
        Message m2 = new ReplyMessage("test", 1, 7, 0);
        when(messageMapper.getAllReceiveByUreceiveId(0)).thenReturn(List.of(m1, m2));

        List<Message> result = messageServiceImpl.getAllReceive(0);
        Assertions.assertEquals(List.of(m1, m2), result);
    }

    @Test
    void testGetAllSend() {
        Message m3 = new PinApplySuccessMessage(90, 0);
        when(messageMapper.getAllSendByUreceiveId(0)).thenReturn(List.of(m3));

        List<Message> result = messageServiceImpl.getAllSend(0);
        Assertions.assertEquals(List.of(m3), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme