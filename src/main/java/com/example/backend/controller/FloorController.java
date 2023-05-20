package com.example.backend.controller;

import com.example.backend.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forum/floor")
public class FloorController {
    @Autowired
    FloorService floorService;

}
