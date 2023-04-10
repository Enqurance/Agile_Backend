package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.User;
import com.example.backend.service.UserService;
import com.example.backend.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-06 08:40:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    UserMapper userMapper;

    @Value("${code.expiration}")
    private Long expiration;

    @Override
    public void sendEmailCode(String email) {
        // TODO email exists


    }

    @Override
    public int insertUser(User user) {
        int result = userMapper.insertAll(user);
        return result;
    }
}




