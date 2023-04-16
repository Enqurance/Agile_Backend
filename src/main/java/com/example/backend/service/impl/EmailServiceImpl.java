package com.example.backend.service.impl;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.example.backend.domain.Email;
import com.example.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${spring.mail.email}")
    private String email;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    public void send(Email email) {
        if (this.email == null
                || this.host == null
                || this.port == null
                || this.username == null
                || this.password == null) {
            throw new RuntimeException("邮箱配置异常");
        }

        // 设置
        MailAccount account = new MailAccount();
        account.setHost(this.host);
        account.setPort(Integer.parseInt(this.port));
        // 设置发送人邮箱
        account.setFrom(this.email);
        // 设置发送人名称
        account.setUser(this.username);
        // 设置发送授权码
        account.setPass(this.password);
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
