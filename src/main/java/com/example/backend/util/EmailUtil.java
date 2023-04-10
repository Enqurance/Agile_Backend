package com.example.backend.util;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.example.backend.domain.Email;
import com.example.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Collections;

public class EmailUtil {
    @Autowired
    private EmailService emailService;
    public void sendEmail(String email, String code) {
        // 获取发送邮箱验证码的HTML模板
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("../static/emailTemplate.ftl");

        // 发送验证码
        emailService.send(new Email(Collections.singletonList(email),
                "邮箱验证码", template.render(Dict.create().set("code", code))));
    }
}
