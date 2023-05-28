package com.example.backend.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.example.backend.domain.RegisterInfo;
import com.example.backend.domain.User;
import com.example.backend.entity.LoginResult;
import com.example.backend.service.AuthService;
import com.example.backend.service.UserService;
import com.example.backend.utils.EmailUtil;
import com.example.backend.utils.JwtUtil;
import com.example.backend.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    // 验证码放入redis缓存过期时间
    @Value("${code.expiration}")
    private Long expiration;

    private final RedisUtil redisUtil;
    private final EmailUtil emailUtil;
    private final UserService userService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    @Value("${info.default_description}")
    private String description;
    @Override
        public void sendMailCode(String email) {
            if (userService.findUserByEmail(email).size() != 0) {
                throw new RuntimeException("邮箱已被注册");
            }

            String code = redisUtil.get(email);
            if (code == null) {
                // 缓存中不存在验证码，则产生6位随机数
                code = RandomUtil.randomNumbers(6);
            }

            emailUtil.sendEmail(email, code);

            if (!redisUtil.set(email, code, expiration)) {
            throw new RuntimeException("服务器redis缓存异常");
        }

    }

    @Override
    public boolean register(RegisterInfo info) {
        if (userService.findUserByEmail(info.getEmail()).size() != 0) {
            throw new RuntimeException("邮箱已被注册");
        }
        String actualCode = redisUtil.get(info.getEmail());
        if (actualCode == null) {
            throw new RuntimeException("验证码不存在");
        }
        if (!actualCode.equals(info.getEmailCode())) {
            throw new RuntimeException("邮箱验证码错误");
        }

        String encodedPass = encryptString(info.getPassword());
        info.setPassword(encodedPass);

        info.setType(0);
        info.setIcon("https://agile-pic-1313874439.cos.ap-beijing.myqcloud.com/agile-pic/win.jpg");
        info.setDescription(description);
        boolean result = userService.insertUser(info) == 1;
        redisUtil.delete(info.getEmail());
        return result;
    }

    @Override
    public LoginResult login(User user) {
        List<User> userWithEmail = userService.findUserByEmail(user.getEmail());
        if (userWithEmail.size() == 0) {
            throw new RuntimeException("邮箱未注册");
        }
        String dbPassword = userWithEmail.get(0).getPassword();
        if (!encryptCorrect(user.getPassword(), dbPassword)) {
            throw new RuntimeException("输入密码错误");
        }

        String token = JwtUtil.createToken(userWithEmail.get(0));
        return new LoginResult(token, userWithEmail.get(0).getType());
    }

    @Override
    public boolean encryptCorrect(String origin, String encrypt) {
        return encoder.matches(origin, encrypt);
    }

    @Override
    public String encryptString(String str) {
        return encoder.encode(str);
    }
}
