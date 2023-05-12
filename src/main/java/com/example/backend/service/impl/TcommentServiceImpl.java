package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Tcomment;
import com.example.backend.mapper.TcommentMapper;
import com.example.backend.service.TcommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author DELL
* @description 针对表【tcomment】的数据库操作Service实现
* @createDate 2023-05-12 10:51:19
*/
@Service
public class TcommentServiceImpl extends ServiceImpl<TcommentMapper, Tcomment>
    implements TcommentService{
    @Autowired
    private TcommentMapper tcommentMapper;

    @Override
    public int newReport(String reason, int comment_id, int u_id) {
        List<Tcomment> Tcomments = tcommentMapper.findAllByCommentId(comment_id);
        if (Tcomments.size() == 0) {
            Tcomment Tcomment = new Tcomment();
            Tcomment.setReason(reason);
            Tcomment.setCommentId(comment_id);
            Tcomment.setUId(String.valueOf(u_id));
            return tcommentMapper.insertAll(Tcomment);
        } else {
            Tcomment Tcomment = Tcomments.get(0);

            // 判断该用户是否举报同一内容
            for (String preUId : Tcomment.getUId().split(";")) {
                if (preUId.equals(String.valueOf(u_id))) {
                    throw new RuntimeException("您已举报过该帖子");
                }
            }

            String allReasons = Tcomment.getReason() + "\n" + reason;
            String allUsers = Tcomment.getUId() + ";" + u_id;

            // 判断对同一信息的举报是否太多，若太多则没必要再加入数据库表
            if (allReasons.length() >= 2000 ||
                    allUsers.length() >= 100) {
                throw new RuntimeException("其他用户对该评论的举报次数过多");
            }

            return tcommentMapper.updateReasonAndUIdByCommentId(allReasons, allUsers, comment_id);
        }
    }

    @Override
    public int finishReport(int tcomment_id) {
        return tcommentMapper.deleteById(tcomment_id);
    }
}




