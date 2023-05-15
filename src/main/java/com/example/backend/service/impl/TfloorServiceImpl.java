package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Tfloor;
import com.example.backend.mapper.TfloorMapper;
import com.example.backend.service.TfloorService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author DELL
* @description 针对表【tfloor】的数据库操作Service实现
* @createDate 2023-05-12 10:51:09
*/
@Service
public class TfloorServiceImpl extends ServiceImpl<TfloorMapper, Tfloor>
    implements TfloorService{
    @Resource
    private TfloorMapper tfloorMapper;

    @Override
    public int newReport(String reason, int floor_id, int u_id) {
        List<Tfloor> Tfloors = tfloorMapper.findAllByFloorId(floor_id);
        if (Tfloors.size() == 0) {
            Tfloor Tfloor = new Tfloor();
            Tfloor.setReason(reason);
            Tfloor.setFloorId(floor_id);
            Tfloor.setUId(String.valueOf(u_id));
            return tfloorMapper.insertAll(Tfloor);
        } else {
            Tfloor Tfloor = Tfloors.get(0);

            // 判断该用户是否举报同一内容
            for (String preUId : Tfloor.getUId().split(";")) {
                if (preUId.equals(String.valueOf(u_id))) {
                    throw new RuntimeException("您已举报过该帖子");
                }
            }

            String allReasons = Tfloor.getReason() + "\n" + reason;
            String allUsers = Tfloor.getUId() + ";" + u_id;

            // 判断对同一信息的举报是否太多，若太多则没必要再加入数据库表
            if (allReasons.length() >= 2000 ||
                    allUsers.length() >= 100) {
                throw new RuntimeException("其他用户对该楼层的举报次数过多");
            }

            return tfloorMapper.updateReasonAndUIdByFloorId(allReasons, allUsers, floor_id);
        }
    }

    @Override
    public int finishReport(int tfloor_id) {
        return tfloorMapper.deleteById(tfloor_id);
    }
}




