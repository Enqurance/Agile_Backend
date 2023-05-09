package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Message;
import com.example.backend.service.MessageService;
import com.example.backend.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【message】的数据库操作Service实现
* @createDate 2023-05-09 09:43:08
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

}




