package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Post;
import com.example.backend.service.PostService;
import com.example.backend.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【post】的数据库操作Service实现
* @createDate 2023-05-11 23:35:30
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

}




