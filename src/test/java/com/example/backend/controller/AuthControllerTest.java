package com.example.backend.controller;

import com.example.backend.domain.RegisterInfo;
import com.example.backend.domain.User;
import com.example.backend.entity.LoginResult;
import com.example.backend.result.CommonResult;
import com.example.backend.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {
    @Mock
    AuthService authService;
    @InjectMocks
    AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendEmailCode() {
        CommonResult result = authController.sendEmailCode("email");

    }

    @Test
    void testRegister() {
        when(authService.register(any())).thenReturn(true);

        CommonResult result = authController.register(new RegisterInfo("emailCode"));

    }

    @Test
    void testLogin() {
        when(authService.login(any())).thenReturn(new LoginResult("token", 0));

        CommonResult result = authController.login(new User());

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme