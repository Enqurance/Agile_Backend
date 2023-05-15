package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Tpin;
import com.example.backend.mapper.TpinMapper;
import com.example.backend.service.TpinService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DELL
 * @description 针对表【tpin】的数据库操作Service实现
 * @createDate 2023-05-12 10:50:37
 */
@Service
public class TpinServiceImpl extends ServiceImpl<TpinMapper, Tpin>
        implements TpinService {
    @Resource
    private TpinMapper tpinMapper;

    @Override
    public int insertTask(int p_id) {
        if (tpinMapper.findAllByPId(p_id).size() != 0) {
            throw new RuntimeException("地图钉已提交过公开申请，请等待审核");
        }

        Tpin tpin = new Tpin();
        tpin.setPId(p_id);
        return tpinMapper.insertAll(tpin);
    }

    @Override
    public int deleteTask(int id) {
        return tpinMapper.deleteById(id);
    }

    @Override
    public List<Tpin> findAllTasks() {
        return tpinMapper.findAll();
    }

    @Override
    public int pinState(int p_id) {
        return tpinMapper.findAllByPId(p_id).size();
    }
}




