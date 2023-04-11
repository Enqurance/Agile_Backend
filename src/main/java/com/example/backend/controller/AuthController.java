package com.example.backend.controller;

import com.example.backend.result.CommonResult;
import com.example.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
