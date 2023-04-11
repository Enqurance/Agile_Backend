package com.example.backend.controller;

import com.example.backend.domain.BuaaService;
import com.example.backend.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuaaServiceController {
    @Autowired
    ServiceService serviceService;

    @RequestMapping("/service/insert")
    public int insertService(BuaaService buaaService) {
        int ret = serviceService.insertService(buaaService);
        return ret;
    }
}
