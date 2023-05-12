package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Tpost;
import com.example.backend.service.TpostService;
import com.example.backend.mapper.TpostMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【tpost】的数据库操作Service实现
* @createDate 2023-05-12 10:50:55
*/
@Service
public class TpostServiceImpl extends ServiceImpl<TpostMapper, Tpost>
    implements TpostService{

}




