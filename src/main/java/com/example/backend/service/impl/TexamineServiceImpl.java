package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Texamine;
import com.example.backend.service.TexamineService;
import com.example.backend.mapper.TexamineMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【texamine】的数据库操作Service实现
* @createDate 2023-05-12 10:51:30
*/
@Service
public class TexamineServiceImpl extends ServiceImpl<TexamineMapper, Texamine>
    implements TexamineService{

}




