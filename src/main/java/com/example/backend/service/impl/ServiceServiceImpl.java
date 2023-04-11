package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.BuaaService;
import com.example.backend.service.ServiceService;
import com.example.backend.mapper.ServiceMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【service】的数据库操作Service实现
* @createDate 2023-04-11 16:29:28
*/
@Service
public class ServiceServiceImpl extends ServiceImpl<ServiceMapper, BuaaService>
    implements ServiceService{
    @Resource
    ServiceMapper serviceMapper;

    @Override
    public int insertService(BuaaService buaaService) {
        int ret = serviceMapper.insertAll(buaaService);
        return ret;
    }

    @Override
    public BuaaService getServiceById(Integer id) {
        return serviceMapper.getServiceById(id);
    }
}




