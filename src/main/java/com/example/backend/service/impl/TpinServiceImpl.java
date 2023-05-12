package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Tpin;
import com.example.backend.service.TpinService;
import com.example.backend.mapper.TpinMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【tpin】的数据库操作Service实现
* @createDate 2023-05-12 10:50:37
*/
@Service
public class TpinServiceImpl extends ServiceImpl<TpinMapper, Tpin>
    implements TpinService{

}




