package com.example.backend.controller;

import com.example.backend.domain.User;
import com.example.backend.domain.Suggestion;
import com.example.backend.entity.Password;
import com.example.backend.entity.SuggestWrap;
import com.example.backend.result.CommonResult;
import com.example.backend.service.AuthService;
import com.example.backend.service.SuggestionService;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    private final UserService userService;

    private final SuggestionService suggestionService;

    @GetMapping("/getUserByToken")
    public CommonResult getUserById(@RequestParam(name = "id") Integer id) {
        List<User> users = userService.findUserById(id);
        if (users.size() == 0) {
            throw new RuntimeException("用户不存在");
        }
        return CommonResult.success(users.get(0));
    }

    @PostMapping("/changeUserBasicByToken")
    public CommonResult changeUserById(@RequestBody User user,
                                       @RequestParam(name = "id") Integer id) {
        user.setId(id);
        int result = userService.updateBasicInfo(user);
        if (result != 1) {
            throw new RuntimeException("用户信息修改失败");
        }
        return CommonResult.success(null);
    }

    @GetMapping("/getIcon")
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

    @PostMapping("/changePasswordByToken")
    public CommonResult changePasswordById(@RequestBody Password password, @RequestParam(name = "id") Integer id) {
        String originPassword = userService.getPassword(id);

        if (!authService.encryptCorrect(password.getPassword(), originPassword)) {
            return CommonResult.failed("密码错误");
        }

        int result = userService.updatePassword(
                authService.encryptString(password.getNewPassword()), id);
        if (result != 1) {
            return CommonResult.failed("修改密码失败");
        }
        return CommonResult.success(null);
    }

    @PostMapping("/suggestByToken")
    public CommonResult suggestByToken(@RequestParam(name = "id") Integer id,
                                       @RequestBody SuggestWrap suggestion) {
        Suggestion suggest = new Suggestion();
        suggest.setUser_id(id);
        suggest.setStr(suggestion.getSuggestion());
        int ret = suggestionService.insertSuggestion(suggest);
        if (ret == 0)
            return CommonResult.failed("suggestion数据插入失败");
        else
            return CommonResult.success(null);
    }
}
