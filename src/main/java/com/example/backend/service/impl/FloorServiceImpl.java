package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Floor;
import com.example.backend.service.FloorService;
import com.example.backend.mapper.FloorMapper;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【floor】的数据库操作Service实现
* @createDate 2023-05-11 23:35:50
*/
@Service
public class FloorServiceImpl extends ServiceImpl<FloorMapper, Floor>
    implements FloorService{

}




