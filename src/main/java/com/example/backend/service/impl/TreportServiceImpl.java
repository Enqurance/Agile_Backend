package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Treport;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.mapper.TreportMapper;
import com.example.backend.service.TreportService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author DELL
 * @description 针对表【treport】的数据库操作Service实现
 * @createDate 2023-05-12 20:48:30
 */
@Service
public class TreportServiceImpl extends ServiceImpl<TreportMapper, Treport>
        implements TreportService {
    @Resource
    private TreportMapper treportMapper;

    /**
     * @param o_id
     * @param type
     * @return 1表示成功
     */
    @Override
    public int newReport(int o_id, FORUMTYPE type) {
        if (treportMapper.findAllByOIdAndType(o_id, type.getType()).size() != 0) {
            Treport treport = new Treport();
            treport.setOId(o_id);
            treport.setType(type.getType());
            return treportMapper.insertAll(treport);
        }
        return 1;
    }

    @Override
    public int finishReport(int treport_id) {
        return 0;
    }
}
