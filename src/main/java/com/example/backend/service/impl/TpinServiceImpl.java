package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Tpin;
import com.example.backend.mapper.TpinMapper;
import com.example.backend.service.TpinService;
import com.example.backend.utils.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
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

    @Resource
    private RedisUtil redisUtil;

    @Value("${times.apply}")
    private int maxTimes;

    private static String applyKey(int id) {
        return "apply: " + id;
    }

    @Override
    public int insertTask(int p_id, int u_id) {
        if (tpinMapper.findAllByPId(p_id).size() != 0) {
            throw new RuntimeException("地图钉已提交过公开申请，请等待审核");
        }

        String times;
        if ((times = redisUtil.get(applyKey(u_id))) == null) {
            // 为该用户添加最大举报次数，设置为第二天过期
            redisUtil.set(applyKey(u_id), String.valueOf(maxTimes - 1), RedisUtil.getNowToNextDaySeconds());
        } else {
            int t = Integer.parseInt(times);
            if (t > 0) {
                redisUtil.getAndSet(applyKey(u_id), String.valueOf(t - 1));
            } else {
                throw new RuntimeException("今日申请地图钉公开次数已用完");
            }
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
    public int deletePin(int p_id) {
        return tpinMapper.deleteByPId(p_id);
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




