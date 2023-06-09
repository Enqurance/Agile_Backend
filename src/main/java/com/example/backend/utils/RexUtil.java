package com.example.backend.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @className: RexUtil
 * @Description: 提供正则表达式验证方法
 * @author: WAN
 * @date: 2023/6/9 9:24
 */
@Component
public class RexUtil {
    private final static String usernameRex = "^\\S{3,20}$";

    private final static String passwordRex = "^[a-zA-Z0-9]{6,20}$";

    public static boolean usernameCheck(String username) {
        return Pattern.matches(usernameRex, username);
    }

    public static boolean passwordCheck(String password) {
        return Pattern.matches(passwordRex, password);
    }
}
