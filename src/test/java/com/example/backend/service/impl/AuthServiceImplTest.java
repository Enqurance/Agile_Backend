package com.example.backend.service.impl;

import com.example.backend.domain.RegisterInfo;
import com.example.backend.domain.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.RedisUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class AuthServiceImplTest {
    @Value("${code.expiration}")
    private Long expiration;
    @Mock
    UserService userService;
//    @MockBean
//    RedisTemplate<String, String> redisTemplate;
//    @Mock
//    ValueOperations<String, String> valueOperations;

    @Mock
    RedisUtil redisUtil;
    @InjectMocks
    AuthServiceImpl authServiceImpl;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(authServiceImpl, "expiration", expiration);
    }

    @Test
    void testSendMailCode() {
        when(redisUtil.get(anyString())).thenReturn(null);
        when(redisUtil.set(anyString(), anyString(), anyLong())).thenReturn(true);
        when(userService.findUserByEmail(anyString())).thenReturn(List.of());

        authServiceImpl.sendMailCode("email");
    }

    @Test
    void testSendMailCodeException1() {
        when(redisUtil.get(anyString())).thenReturn(null);
        when(redisUtil.set(anyString(), anyString(), anyLong())).thenReturn(true);
        when(userService.findUserByEmail(anyString())).thenReturn(List.of(new User()));

        try {
            authServiceImpl.sendMailCode("email");
        } catch (Exception e) {
            Assertions.assertEquals("邮箱已被注册", e.getMessage());
        }
    }

    @Test
    void testSendMailCodeException2() {
        when(redisUtil.get(anyString())).thenReturn(null);
        when(redisUtil.set(anyString(), anyString(), anyLong())).thenReturn(false);
        when(userService.findUserByEmail(anyString())).thenReturn(List.of());

        try {
            authServiceImpl.sendMailCode("email");
        } catch (Exception e) {
            Assertions.assertEquals("服务器redis缓存异常", e.getMessage());
        }
    }

    @Test
    void testRegister() {
        when(redisUtil.get(anyString())).thenReturn("123");
        when(redisUtil.delete(anyString())).thenReturn(true);
        when(userService.insertUser(any())).thenReturn(1);
        when(userService.findUserByEmail(anyString())).thenReturn(List.of());

        RegisterInfo info = new RegisterInfo("123");
        info.setEmail("email");
        info.setPassword("password");
        boolean result = authServiceImpl.register(info);
        Assertions.assertTrue(result);
    }

    @Test
    void testRegisterException1() {
        when(redisUtil.get(anyString())).thenReturn("123");
        when(redisUtil.delete(anyString())).thenReturn(true);
        when(userService.insertUser(any())).thenReturn(1);
        when(userService.findUserByEmail(anyString())).thenReturn(List.of(new User()));

        RegisterInfo info = new RegisterInfo("123");
        info.setEmail("email");
        info.setPassword("password");
        try {
            authServiceImpl.register(info);
        } catch (Exception e) {
            Assertions.assertEquals("邮箱已被注册", e.getMessage());
        }
    }

    @Test
    void testRegisterException2() {
        when(redisUtil.get(anyString())).thenReturn(null);
        when(redisUtil.delete(anyString())).thenReturn(true);
        when(userService.insertUser(any())).thenReturn(1);
        when(userService.findUserByEmail(anyString())).thenReturn(List.of());

        RegisterInfo info = new RegisterInfo("123");
        info.setEmail("email");
        info.setPassword("password");
        try {
            authServiceImpl.register(info);
        } catch (Exception e) {
            Assertions.assertEquals("验证码不存在", e.getMessage());
        }
    }

    @Test
    void testRegisterException3() {
        when(redisUtil.get(anyString())).thenReturn("123");
        when(redisUtil.delete(anyString())).thenReturn(true);
        when(userService.insertUser(any())).thenReturn(1);
        when(userService.findUserByEmail(anyString())).thenReturn(List.of());

        RegisterInfo info = new RegisterInfo("1234");
        info.setEmail("email");
        info.setPassword("password");
        try {
            authServiceImpl.register(info);
        } catch (Exception e) {
            Assertions.assertEquals("邮箱验证码错误", e.getMessage());
        }
    }

    @Test
    void testLogin() {
        when(userService.findUserByEmail(anyString())).thenReturn(List.of(new User()));

        try {
            User user = new User();
            user.setEmail("email");
            user.setPassword("password");
            authServiceImpl.login(user);
        } catch (Exception e) {
            Assertions.assertEquals("输入密码错误", e.getMessage());
        }
    }

    @Test
    void testEncryptCorrect() {
        boolean result = authServiceImpl.encryptCorrect("origin", "encrypt");
        Assertions.assertFalse(result);
    }

    @Test
    void testEncryptString() {
        String result = authServiceImpl.encryptString("str");
        Assertions.assertNotEquals("replaceMeWithExpectedResult", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme