package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.entity.FrontendMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.MessageService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/InfoPage/MyMessage/")
@PreAuthorize("hasAuthority('USER')")
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
        messageService.getAllReceive(search_id).forEach(message -> {
            FrontendMessage frontendMessage;
            try {
                frontendMessage = FrontendMessage.trans2FrontendMessage(message);
            } catch (Exception e) {
                frontendMessage = new FrontendMessage(message);
            }
            messages.add(frontendMessage);
        });
        messages.sort((m1, m2) ->
                m2.getTime().compareTo(m1.getTime())
        );
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
        messages.sort((m1, m2) ->
                m2.getTime().compareTo(m1.getTime())
        );
        return CommonResult.success(messages);
    }

    @PostMapping("/readMessageById")
    public CommonResult readMessage(@RequestBody JSONObject object) {
        int m_id = object.getInt("m_id");
        if (messageService.readMessage(m_id) != 1) {
            throw new RuntimeException("删除消息失败");
        }
        return CommonResult.success(null);
    }

    @DeleteMapping("/deleteMessage/{m_id}")
    public CommonResult deleteMessage(@PathVariable Integer m_id) {
        if (messageService.deleteMessage(m_id) != 1) {
            throw new RuntimeException("删除消息失败");
        }
        return CommonResult.success(null);
    }

    @PostMapping("/readAllReceiveMessage")
    public CommonResult readAllReceive(@RequestParam(name = "id") Integer id) {
        if (messageService.readAllReceive(id) != 1) {
            throw new RuntimeException("全部已读失败");
        }
        return CommonResult.success(null);
    }

    @PostMapping("/deleteAllReceiveMessage")
    public CommonResult deleteAllReceive(@RequestParam(name = "id") Integer id) {
        if (messageService.deleteAllReceive(id) != 1) {
            throw new RuntimeException("全部删除失败");
        }
        return CommonResult.success(null);
    }

    @PostMapping("/readAllSendMessage")
    public CommonResult readAllSend(@RequestParam(name = "id") Integer id) {
        if (messageService.readAllSend(id) != 1) {
            throw new RuntimeException("全部已读失败");
        }
        return CommonResult.success(null);
    }

    @PostMapping("/deleteAllSendMessage")
    public CommonResult deleteAllSend(@RequestParam(name = "id") Integer id) {
        if (messageService.deleteAllSend(id) != 1) {
            throw new RuntimeException("全部删除失败");
        }
        return CommonResult.success(null);
    }
}
