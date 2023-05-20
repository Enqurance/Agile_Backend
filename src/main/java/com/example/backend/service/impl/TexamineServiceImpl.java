package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Texamine;
import com.example.backend.service.TexamineService;
import com.example.backend.mapper.TexamineMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DELL
 * @description 针对表【texamine】的数据库操作Service实现
 * @createDate 2023-05-20 10:52:21
 */
@Service
public class TexamineServiceImpl extends ServiceImpl<TexamineMapper, Texamine>
        implements TexamineService {
    @Resource
    private TexamineMapper texamineMapper;

    @Override
    public int newTaskExamine(int post_id, String basis) {
        Texamine texamine = new Texamine();
        texamine.setPostId(post_id);
        texamine.setBasis(basis);
        return texamineMapper.insertAll(texamine);
    }

    @Override
    public int finishTaskExamine(int post_id) {
        return texamineMapper.deleteByPostId(post_id);
    }

    @Override
    public List<Texamine> getAllTasks() {
        return texamineMapper.findAllReadyTasks();
    }

    @Override
    public int rectify(int post_id, String title, String content) {
        return texamineMapper.updateTitleAndContentByPostId(title, content, post_id);
    }

    @Override
    public Texamine getTaskByPostId(int post_id) {
        return texamineMapper.getAllByPostId(post_id).get(0);
    }
}




