package com.example.backend.service.impl;

import com.example.backend.domain.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@SpringBootTest
class EmailServiceImplTest {
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

    @InjectMocks
    EmailServiceImpl emailServiceImpl = new EmailServiceImpl();

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(emailServiceImpl, "email", email);
        ReflectionTestUtils.setField(emailServiceImpl, "host", host);
        ReflectionTestUtils.setField(emailServiceImpl, "port", port);
        ReflectionTestUtils.setField(emailServiceImpl, "username", username);
        ReflectionTestUtils.setField(emailServiceImpl, "password", password);
    }

    @Test
    void testSend() {
        try {
            emailServiceImpl.send(new Email(List.of("String"), "subject", "content"));
        } catch (Exception e) {
            Assertions.assertEquals("Invalid Addresses: [String]", e.getMessage());
        }

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme