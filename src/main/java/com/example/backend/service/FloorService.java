package com.example.backend.service;

import com.example.backend.domain.Floor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Sisyphus
* @description 针对表【floor】的数据库操作Service
* @createDate 2023-05-11 23:35:50
*/
public interface FloorService extends IService<Floor> {
    int addFloor(Floor floor);

    Floor getFloorById(Integer id);

    int deleteFloorById(Integer id);

    int findMaxId();

    List<Floor> getFloorsOrderTime(Integer postId);

    List<Floor> getFloorIdByPostId(Integer postId);

    List<Floor> getMyAllFloor(Integer id);
}
