package com.example.backend.service.impl;

import com.example.backend.domain.RegisterInfo;
import com.example.backend.domain.User;
import com.example.backend.entity.LoginResult;
import com.example.backend.service.UserService;
import com.example.backend.utils.EmailUtil;
import com.example.backend.utils.RedisUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.mockito.Mockito.*;

class AuthServiceImplTest {
    @Mock
    RedisUtil redisUtil;
    @Mock
    EmailUtil emailUtil;
    @Mock
    UserService userService;
    @Mock
    BCryptPasswordEncoder encoder;
    @InjectMocks
    AuthServiceImpl authServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMailCode() {
        when(redisUtil.get(anyString())).thenReturn("getResponse");
        when(redisUtil.set(anyString(), anyString(), anyLong())).thenReturn(true);
        when(userService.findUserByEmail(anyString())).thenReturn(List.of(new User()));

        authServiceImpl.sendMailCode("email");
    }

    @Test
    void testRegister() {
        when(redisUtil.get(anyString())).thenReturn("getResponse");
        when(redisUtil.delete(anyString())).thenReturn(true);
        when(userService.insertUser(any())).thenReturn(0);
        when(userService.findUserByEmail(anyString())).thenReturn(List.of(new User()));

        boolean result = authServiceImpl.register(new RegisterInfo("emailCode"));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testLogin() {
        when(userService.findUserByEmail(anyString())).thenReturn(List.of(new User()));

        LoginResult result = authServiceImpl.login(new User());
        Assertions.assertEquals(new LoginResult("token", Integer.valueOf(0)), result);
    }

    @Test
    void testEncryptCorrect() {
        boolean result = authServiceImpl.encryptCorrect("origin", "encrypt");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testEncryptString() {
        String result = authServiceImpl.encryptString("str");
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme