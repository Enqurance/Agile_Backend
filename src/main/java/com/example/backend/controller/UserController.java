package com.example.backend.controller;

import com.example.backend.pojo.User;
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
    public int login(@RequestBody User user) {
        int result = userService.insertUser(user);
        return result;
    }
}
