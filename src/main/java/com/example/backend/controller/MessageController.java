package com.example.backend.controller;

import com.example.backend.entity.FrontendMessage;
import com.example.backend.result.CommonResult;
import com.example.backend.service.MessageService;
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


    @GetMapping("/getReceiveMessage")
    public CommonResult getReceiveMessage(@RequestParam(name = "id") Integer id) {
        List<FrontendMessage> messages = new ArrayList<>();
        messageService.getAllReceive(id).forEach(message -> messages.add(new FrontendMessage(message)));
        return CommonResult.success(messages);
    }

    @GetMapping("/getSendMessage")
    public CommonResult getSendMessage(@RequestParam(name = "id") Integer id) {
        List<FrontendMessage> messages = new ArrayList<>();
        messageService.getAllSend(id).forEach(message -> messages.add(new FrontendMessage(message)));
        return CommonResult.success(messages);
    }

    @PostMapping("/readMessageById")
    public CommonResult readMessage(@RequestParam(name = "m_id") Integer m_id) {
        int result = messageService.readMessage(m_id);
        if (result != 1) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
}
