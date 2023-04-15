package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.User;
import com.example.backend.service.UserService;
import com.example.backend.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DELL
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-04-11 09:17:21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insertAll(user);
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return userMapper.findAllByEmail(email);
    }

    @Override
    public List<User> findUserById(Integer id) {
        return userMapper.findAllById(id);
    }

    @Override
    public int updateBasicInfo(User user) {
        return userMapper.updateNameAndCampusAndGradeAndGenderAndDescriptionById(
                user.getName(),
                user.getCampus(),
                user.getGrade(),
                user.getGender(),
                user.getDescription(),
                user.getId()
        );
    }

    @Override
    public int updateIcon(User user) {
        return userMapper.updateIcon(user.getIcon(), user.getId());
    }
}




