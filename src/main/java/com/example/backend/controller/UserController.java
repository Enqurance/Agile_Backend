package com.example.backend.controller;

import com.example.backend.domain.User;
import com.example.backend.result.CommonResult;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping("getUserByToken")
    public CommonResult getUserById(@RequestParam(name = "id") Integer id) {
        List<User> users = userService.findUserById(id);
        if (users.size() == 0) {
            throw new RuntimeException("用户不存在");
        }
        return CommonResult.success(users.get(0));
    }

    @ResponseBody
    @PostMapping("changeUserBasicByToken")
    public CommonResult changeUserById(@RequestBody User user,
                                       @RequestParam(name = "id") Integer id) {
        user.setId(id);
        int result = userService.updateBasicInfo(user);
        if (result != 1) {
            throw new RuntimeException("用户信息修改失败");
        }
        return CommonResult.success(null);
    }
}
