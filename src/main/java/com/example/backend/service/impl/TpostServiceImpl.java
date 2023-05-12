package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Tpost;
import com.example.backend.mapper.TpostMapper;
import com.example.backend.service.TpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DELL
 * @description 针对表【tpost】的数据库操作Service实现
 * @createDate 2023-05-12 10:50:55
 */
@Service
public class TpostServiceImpl extends ServiceImpl<TpostMapper, Tpost>
        implements TpostService {
    @Autowired
    private TpostMapper tpostMapper;

    /**
     * 插入新的举报任务。若当前举报已经存在，则将举报理由和举报用户附在后面。
     *
     * @param reason  新举报原因
     * @param post_id 举报帖子id
     * @param u_id    举报用户id
     * @return
     */
    @Override
    public int newReport(String reason, int post_id, int u_id) {
        List<Tpost> tposts = tpostMapper.findAllByPostId(post_id);
        if (tposts.size() == 0) {
            Tpost tpost = new Tpost();
            tpost.setReason(reason);
            tpost.setPostId(post_id);
            tpost.setUId(String.valueOf(u_id));
            return tpostMapper.insertAll(tpost);
        } else {
            Tpost tpost = tposts.get(0);

            // 判断该用户是否举报同一内容
            for (String preUId : tpost.getUId().split(";")) {
                if (preUId.equals(String.valueOf(u_id))) {
                    throw new RuntimeException("您已举报过该帖子");
                }
            }

            String allReasons = tpost.getReason() + "\n" + reason;
            String allUsers = tpost.getUId() + ";" + u_id;

            // 判断对同一信息的举报是否太多，若太多则没必要再加入数据库表
            if (allReasons.length() >= 2000 ||
                    allUsers.length() >= 100) {
                throw new RuntimeException("其他用户对该帖子的举报次数过多");
            }

            return tpostMapper.updateReasonAndUIdByPostId(allReasons, allUsers, post_id);
        }
    }

    @Override
    public int finishReport(int tpost_id) {
        return tpostMapper.deleteById(tpost_id);
    }
}




