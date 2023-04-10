package com.example.backend.service;

import com.example.backend.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author DELL
* @description 针对表【user】的数据库操作Service
* @createDate 2023-04-06 08:40:06
*/
public interface UserService extends IService<User> {
    int insertUser(User user);

    List<User> findUserByEmail(String email);
}
