package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Floor;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.mapper.FloorMapper;
import com.example.backend.service.CommentService;
import com.example.backend.service.ExamineService;
import com.example.backend.service.FloorService;
import com.example.backend.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Sisyphus
* @description 针对表【floor】的数据库操作Service实现
* @createDate 2023-05-13 15:50:34
*/
@Service
public class FloorServiceImpl extends ServiceImpl<FloorMapper, Floor>
    implements FloorService{
    @Resource
    FloorMapper floorMapper;

    @Resource
    private CommentService commentService;

    @Resource
    private ReportService reportService;

    @Resource
    private ExamineService examineService;

    @Override
    public int addFloor(Floor floor) {
        return floorMapper.insertFloor(floor);
    }

    @Override
    public Floor getFloorById(Integer id) {
        return floorMapper.getFloorById(id);
    }

    @Override
    public int deleteFloorById(Integer id) {
        examineService.delete("floor", id.toString());
        // 删除所属评论
        commentService.getAllCommentByFloorId(id).forEach(
                comment -> commentService.deleteCommentById(comment.getId())
        );
        // 删除所属举报内容
        reportService.finishReport(id, FORUMTYPE.FLOOR);
        return floorMapper.deleteById(id);
    }

    @Override
    public int findMaxId() {
        return floorMapper.findMaxId();
    }

    @Override
    public List<Floor> getFloorsOrderTime(Integer postId) {
        return floorMapper.getFloorsOrderTime(postId);
    }

    @Override
    public List<Floor> getFloorIdByPostId(Integer postId) {
        return floorMapper.getFloorIdByPostId(postId);
    }

    @Override
    public List<Floor> getMyAllFloor(Integer id) {
        return floorMapper.getMyAllFloor(id);
    }
}




