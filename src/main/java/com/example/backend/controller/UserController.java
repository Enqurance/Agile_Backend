package com.example.backend.controller;

import com.example.backend.result.CommonResult;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping("getUserByToken")
    @PreAuthorize("hasAuthority('USER')")
    public CommonResult getUserById(@RequestParam(name = "id") String id) {
        return CommonResult.success("connected");
    }
}
