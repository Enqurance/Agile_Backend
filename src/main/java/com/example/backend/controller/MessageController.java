package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.entity.FrontendMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.MessageService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: MessageController
 * @Description: 针对message提供相应api
 * @author: WAN
 * @date: 2023/5/9 15:29
 */
@RestController()
@RequestMapping("InfoPage/MyMessage/")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    @GetMapping("/getReceiveMessage")
    public CommonResult getReceiveMessage(@RequestParam(name = "id") Integer id) {
        List<FrontendMessage> messages = new ArrayList<>();
        int search_id;
        if (userService.getType(id) == 1) {
            search_id = 0;
        } else {
            search_id = id;
        }
        messageService.getAllReceive(search_id).forEach(message -> messages.add(
                FrontendMessage.trans2FrontendMessage(message)
        ));
        return CommonResult.success(messages);
    }

    @GetMapping("/getSendMessage")
    public CommonResult getSendMessage(@RequestParam(name = "id") Integer id) {
        List<FrontendMessage> messages = new ArrayList<>();
        int search_id;
        if (userService.getType(id) == 1) {
            search_id = 0;
        } else {
            search_id = id;
        }
        messageService.getAllSend(search_id).forEach(message -> messages.add(
                FrontendMessage.trans2FrontendMessage(message)
        ));
        return CommonResult.success(messages);
    }

    @PostMapping("/readMessageById")
    public CommonResult readMessage(@RequestBody JSONObject object) {
        int m_id = object.getInt("m_id");
        int result = messageService.readMessage(m_id);
        if (result != 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
}
