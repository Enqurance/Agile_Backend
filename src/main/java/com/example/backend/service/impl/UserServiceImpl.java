package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.User;
import com.example.backend.service.UserService;
import com.example.backend.mapper.UserMapper;
import com.example.backend.utils.RexUtil;
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
        if (!RexUtil.usernameCheck(user.getName())) {
            throw new RuntimeException("用户名格式错误");
        }

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

    @Override
    public int updatePassword(String password, Integer id) {
        return userMapper.updatePasswordById(password, id);
    }

    @Override
    public String getPassword(Integer id) {
        List<User> users = userMapper.getPasswordById(id);
        if (users.size() != 1) {
            throw new RuntimeException("用户不存在，请联系管理员");
        }
        return users.get(0).getPassword();
    }

    @Override
    public int getType(Integer id) {
        return userMapper.getTypeById(id).get(0).getType();
    }
}




