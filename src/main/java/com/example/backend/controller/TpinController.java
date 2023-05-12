package com.example.backend.controller;

import com.example.backend.service.TpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TpinController
 * @Description: 对于Tpin提供api
 * @author: WAN
 * @date: 2023/5/12 11:12
 */
@RestController
@RequestMapping()
public class TpinController {
    @Autowired
    private TpinService tpinService;
}
