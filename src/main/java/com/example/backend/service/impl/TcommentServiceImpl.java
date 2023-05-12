package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Tcomment;
import com.example.backend.service.TcommentService;
import com.example.backend.mapper.TcommentMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【tcomment】的数据库操作Service实现
* @createDate 2023-05-12 10:51:19
*/
@Service
public class TcommentServiceImpl extends ServiceImpl<TcommentMapper, Tcomment>
    implements TcommentService{

}




