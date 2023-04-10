package com.example.backend.service;

import com.example.backend.domain.Pin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Sisyphus
* @description 针对表【pin】的数据库操作Service
* @createDate 2023-04-09 16:23:05
*/
public interface PinService extends IService<Pin> {

    int insertPin(Pin pin);
}
