package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.User;
import com.example.backend.service.UserService;
import com.example.backend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-04-11 09:17:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




