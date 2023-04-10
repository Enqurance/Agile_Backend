package com.example.backend.controller;

import com.example.backend.domain.Pin;
import com.example.backend.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PinController {
    @Autowired
    PinService pinService;

    @RequestMapping("/insert")
    public int insertPinTest() {
        Pin pin = new Pin();
        int ret = pinService.insertPin(pin);
        return ret;
    }
}
