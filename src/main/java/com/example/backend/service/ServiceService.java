package com.example.backend.service;

import com.example.backend.domain.BuaaService;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Sisyphus
* @description 针对表【service】的数据库操作Service
* @createDate 2023-04-11 16:29:28
*/
public interface ServiceService extends IService<BuaaService> {
    int insertService(BuaaService buaaService);

    BuaaService getServiceById(Integer id);
}
