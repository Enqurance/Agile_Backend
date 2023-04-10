package com.example.backend.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.example.backend.domain.Email;
import org.springframework.beans.factory.annotation.Value;

public class EmailServiceImpl {
    @Value("${spring.mail.email}")
    private static String email;
    @Value("${spring.mail.host}")
    private static String host;
    @Value("${spring.mail.port}")
    private static String port;
    @Value("${spring.mail.username}")
    private static String username;
    @Value("${spring.mail.password}")
    private static String password;

    public void send(Email email) {
        if (email == null || host == null || port == null || username == null || password == null) {
            throw new RuntimeException("邮箱配置异常");
        }

        // 设置
        MailAccount account = new MailAccount();
        account.setHost(host);
        account.setPort(Integer.parseInt(port));
        // 设置发送人邮箱
        account.setFrom(username + "<" + email + ">");
        // 设置发送人名称
        account.setUser(username);
        // 设置发送授权码
        account.setPass(password);
        account.setAuth(true);
        // ssl方式发送
        account.setSslEnable(true);
        // 使用安全连接
        account.setStarttlsEnable(true);

        try {
            int size = email.getTos().size();
            Mail.create(account)
                    .setTos(email.getTos().toArray(new String[size]))
                    .setTitle(email.getSubject())
                    .setContent(email.getContent())
                    .setHtml(true)
                    //关闭session
                    .setUseGlobalSession(false)
                    .send();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
