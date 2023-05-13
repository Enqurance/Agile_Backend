package com.example.backend.service;

import com.example.backend.domain.Floor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Sisyphus
* @description 针对表【floor】的数据库操作Service
* @createDate 2023-05-11 23:35:50
*/
public interface FloorService extends IService<Floor> {
    int addFloor(Floor floor);

    Floor getFloorById(Integer id);
}
