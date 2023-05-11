package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Comment;
import com.example.backend.service.CommentService;
import com.example.backend.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2023-05-11 23:35:55
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




