package com.example.backend.service;

import com.example.backend.domain.RegisterInfo;
import com.example.backend.domain.User;
import com.example.backend.entity.LoginResult;

public interface AuthService {
    /**
     * 向指定邮箱发送验证码
     *
     * @param email 邮箱号
     */
    void sendMailCode(String email);

    /**
     * 注册
     *
     * @param info 认证用户请求信息
     * @return 是否成功
     */
    boolean register(RegisterInfo info);

    /**
     * 登录
     * @param user
     * @return token
     */
    LoginResult login(User user);

    boolean encryptCorrect(String origin, String encrypt);
}
