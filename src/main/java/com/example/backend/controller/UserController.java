package com.example.backend.controller;

import com.example.backend.domain.Photo;
import com.example.backend.domain.User;
import com.example.backend.result.CommonResult;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

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

    @ResponseBody
    @PostMapping("/uploadIcon")
    public CommonResult uploadIcon(MultipartFile pic, @RequestParam(name = "id") Integer id) {
        String picName = pic.getOriginalFilename();
        String picPath = System.getProperty("user.dir") + "/pic/";
        File file = new File(picPath);
        if (!file.exists())
            file.mkdir();
        UUID uuid = UUID.randomUUID();
        String url = picPath + picName.replace(".", "_" + uuid + ".");
        User user = userService.findUserById(id).get(0);
        user.setIcon(url);
        try {
            pic.transferTo(new File(url));
            userService.updateIcon(user);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("pic储存出现错误");
        }
        return CommonResult.success(null);
    }

    @ResponseBody
    @PostMapping("/getIcon")
    public CommonResult getIcon(@RequestParam(name = "id") Integer id) {
        List<User> users = userService.findUserById(id);
        if (users.size() == 0)
            return CommonResult.failed("id = " + id + " 的user不存在");
        User user = users.get(0);
        String iconUrl = user.getIcon();
        if (iconUrl == null)
            return CommonResult.failed("icon 为 null");
        else
            return CommonResult.success(iconUrl);
    }
}
