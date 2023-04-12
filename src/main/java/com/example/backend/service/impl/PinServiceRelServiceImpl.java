package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.PinServiceRel;
import com.example.backend.service.PinServiceRelService;
import com.example.backend.mapper.PinServiceRelMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
* @author Sisyphus
* @description 针对表【pin_service】的数据库操作Service实现
* @createDate 2023-04-11 21:34:22
*/
@Service
public class PinServiceRelServiceImpl extends ServiceImpl<PinServiceRelMapper, PinServiceRel>
    implements PinServiceRelService {
    @Resource
    PinServiceRelMapper pinServiceRelMapper;

    @Override
    public int insertPinServiceRel(PinServiceRel pinServiceRel) {
        int ret = pinServiceRelMapper.insertAll(pinServiceRel);
        return ret;
    }

    @Override
    public ArrayList<Integer> getServiceIdByPinId(Integer pinId) {
        return pinServiceRelMapper.getServiceIdByPinId(pinId);
    }
}




