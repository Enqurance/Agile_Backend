package com.example.backend.service;

import com.example.backend.domain.PinServiceRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
* @author Sisyphus
* @description 针对表【pin_service】的数据库操作Service
* @createDate 2023-04-11 21:34:22
*/
public interface PinServiceRelService extends IService<PinServiceRel> {
    int insertPinServiceRel(PinServiceRel pinServiceRel);

    ArrayList<Integer> getServiceIdByPinId(Integer pinId);
}
