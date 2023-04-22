package com.example.backend.controller;

import com.example.backend.domain.RegisterInfo;
import com.example.backend.domain.User;
import com.example.backend.result.CommonResult;
import com.example.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/email")
    public CommonResult sendEmailCode(@RequestParam(name = "email") String email) {
//        try {
//            authService.sendMailCode(email);
//        } catch (RuntimeException e) {
//            return CommonResult.failed(e.getMessage());
//        }
        authService.sendMailCode(email);
        return CommonResult.success(null);
    }

    @PostMapping("/register")
    public CommonResult register(@RequestBody RegisterInfo info) {
//        boolean result;
//        try {
//            result = authService.register(info);
//        } catch (RuntimeException e) {
//            return CommonResult.failed(e.getMessage());
//        }
//        if (result) {
//            return CommonResult.success(null);
//        } else {
//            return CommonResult.failed("用户注册失败，请联系管理员");
//        }

        if (authService.register(info)) {
            return CommonResult.success(null);
        } else {
            return CommonResult.failed("用户注册失败，请联系管理员");
        }
    }

    @PostMapping("/login")
    public CommonResult login(@RequestBody User user) {
//        String token;
//        try {
//            token = authService.login(user);
//        } catch (RuntimeException e) {
//            return CommonResult.failed(e.getMessage());
//        }
//        return CommonResult.success(token);
        return CommonResult.success(authService.login(user));
    }

    @GetMapping("/encrypt")
    public CommonResult encrypt(@RequestParam(name = "str") String str) {
        return CommonResult.success(authService.encryptString(str));
    }
}
