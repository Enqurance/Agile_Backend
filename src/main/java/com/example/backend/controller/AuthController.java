package com.example.backend.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.example.backend.result.CommonResult;
import com.example.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/email")
    public CommonResult sendEmailCode(@RequestParam(name = "email") String email) {
        try {
            authService.sendMailCode(email);
        } catch (RuntimeException e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(null);
    }

    @PostMapping("/test")
    public CommonResult<Object> testEmail() {
        MailAccount account = new MailAccount();
        account.setHost("smtp.163.com");
        account.setPort(25);
        // 设置发送人邮箱
        account.setFrom("buaamap@163.com");
        // 设置发送人名称
        account.setUser("buaamap@163.com");
        // 设置发送授权码
        account.setPass("DYZOTKPKUSBXOCVN");
        account.setAuth(true);
//        // ssl方式发送
//        account.setSslEnable(true);
//        // 使用安全连接
//        account.setStarttlsEnable(true);
        try {
            MailUtil.send(account, CollUtil.newArrayList("1437207980@qq.com"), "测试", "邮件来自Hutool测试", false);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }

        return CommonResult.success(null);
//        String text = MailUtil.send("1437207980@qq.com", "title.test", "mail.send.test", false, null);
//        System.out.println(text);
    }
}
