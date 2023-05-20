package com.example.backend.controller;

import com.example.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forum/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

}
