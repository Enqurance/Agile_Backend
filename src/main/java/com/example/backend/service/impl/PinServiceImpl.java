package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Pin;
import com.example.backend.service.PinService;
import com.example.backend.mapper.PinMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【pin】的数据库操作Service实现
* @createDate 2023-04-09 16:23:05
*/
@Service
public class PinServiceImpl extends ServiceImpl<PinMapper, Pin>
    implements PinService{
    @Resource
    PinMapper pinMapper;

    @Override
    public int insertPin(Pin pin) {
        int result = pinMapper.insertAll(pin);
        return result;
    }
}




