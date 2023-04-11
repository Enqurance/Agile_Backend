package com.example.backend.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.backend.domain.User;
import com.example.backend.service.AuthService;
import com.example.backend.service.UserService;
import com.example.backend.utils.EmailUtil;
import com.example.backend.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    // 验证码放入redis缓存过期时间
    @Value("${code.expiration}")
    private Long expiration;

    private final RedisUtil redisUtils;
    private final EmailUtil emailUtils;
    private final UserService userService;
    @Override
    public void sendMailCode(String email) {
        if (userService.findUserByEmail(email).size() != 0) {
            throw new RuntimeException("邮箱已被注册");
        }

        String code = redisUtils.get(email);
        if (code == null) {
            // 缓存中不存在验证码，则产生6位随机数
            code = RandomUtil.randomNumbers(6);
        }

        emailUtils.sendEmail(email, code);

        if (!redisUtils.set(email, code, expiration)) {
            throw new RuntimeException("服务器redis缓存异常");
        }

    }

    @Override
    public boolean register(User user) {
        return false;
    }
}
