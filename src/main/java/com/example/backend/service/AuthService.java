package com.example.backend.service;

import com.example.backend.domain.User;

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
     * @param user 认证用户请求信息
     * @return 是否成功
     */
    boolean register(User user);
}
