package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Pin;
import com.example.backend.mapper.TfeedbackMapper;
import com.example.backend.mapper.TpinMapper;
import com.example.backend.service.PinService;
import com.example.backend.mapper.PinMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private TpinMapper tpinMapper;

    @Resource
    private TfeedbackMapper tfeedbackMapper;

    @Override
    public int insertPin(Pin pin) {
        int result = pinMapper.insertAll(pin);
        return result;
    }

    @Override
    public ArrayList<Pin> searchPin(String searchContext, Integer id) {
        String sqlText = "%" + searchContext + "%";
        ArrayList<Pin> pins = pinMapper.searchAll(sqlText, id);
        return pins;
    }

    @Override
    public int updatePin(Pin pin) {
        int result = pinMapper.updateAll(pin);
        return result;
    }

    @Override
    public Pin getPinById(Integer id) {
        return pinMapper.getPinById(id);
    }

    @Override
    public int findMaxId() {
        return pinMapper.findMaxId();
    }

    @Override
    public int deletePinById(Integer id) {
        // 先删除地图钉相关任务
        tpinMapper.deleteByPId(id);
        tfeedbackMapper.deleteByPId(id);

        return pinMapper.deletePinById(id);
    }

    @Override
    public List<Pin> getUserAllBriefPin(Integer u_id, Integer visibility) {
        return pinMapper.getAllByUser_idOrVisibility(u_id, visibility);
    }

    @Override
    public int pinPublic(int p_id) {
        return pinMapper.updateVisibilityById(1, p_id);
    }

    @Override
    public int switchPos(Pin pin) {
        return pinMapper.switchPos(pin.getId(), pin.getLnglat());
    }

    @Override
    public List<Pin> getMyAllPin(Integer id) {
        return pinMapper.getMyAllPin(id);
    }

    @Override
    public List<Pin> getAllPublicPin(Integer type) {
        return pinMapper.getPublicPinByType(type);
    }

    @Override
    public String getNameStrByIdStr(String pinIdStr) {
        String pinNameStr = "";
        String[] pinIds = pinIdStr.split(";");
        StringBuilder sb = new StringBuilder();
        for (String pinId : pinIds) {
            if (pinId != null && !pinId.equals("")) {
                String name = this.getPinById(Integer.valueOf(pinId)).getName();
                sb.append(name).append(";");
            }
        }
        if (sb.length() != 0)
            pinNameStr = sb.substring(0, sb.length() - 1);
        return pinNameStr;
    }
}




