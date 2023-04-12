package com.example.backend.controller;

import com.example.backend.domain.User;
import com.example.backend.result.CommonResult;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public String hello() {
        return "test";
    }

    @PostMapping("/login")
    public CommonResult login(@RequestBody User user) {
        return CommonResult.success(0);
    }
}