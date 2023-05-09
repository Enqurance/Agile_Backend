package com.example.backend.entity.message;

import com.example.backend.domain.Message;
import com.example.backend.service.PinService;
import com.example.backend.service.UserService;
import com.example.backend.service.impl.PinServiceImpl;
import com.example.backend.service.impl.UserServiceImpl;

/**
 * @className: TitleGenerator
 * @Description: 根据Message的type生成不同title
 * @author: WAN
 * @date: 2023/5/9 16:16
 */
public class TitleGenerator {
    private final static UserService userService = new UserServiceImpl();

    private final static PinService pinService = new PinServiceImpl();

    public static String generateTitle(Message message) {
        String title;
        switch (message.getType()) {
            case 1 -> title = LikeTitle(message);
            case 2 -> title = ReplyTitle(message);
            case 3 -> title = PinApplySuccessTitle(message);
            case 4 -> title = PinTaskTitle(message);
            case 5 -> title = PostReportTitale(message);
            case 6 -> title = CommentReportTitle(message);
            case 7 -> title = ExaminePostTitle(message);
            default -> title = "";
        }
        return title;
    }

    private static String PinApplySuccessTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                "地图钉" +
                pinService.getPinById(Integer.parseInt(
                        message.getPara()
                )).getName() +
                "成功发起公开申请，请等待审核";
    }

    private static String CommentReportTitle(Message message) {
        return MESSTYPE.REPORT.getType() +
                "评论" +
                "" +      // TODO 获取楼层或评论的具体内容
                "被举报";
    }

    private static String ExaminePostTitle(Message message) {
        return MESSTYPE.APPLY.getType() +
                "帖子" +
                "" +  // TODO 获取标题
                "申请发布";
    }

    private static String LikeTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                userService.findUserById(Integer.parseInt(
                        message.getPara().split(";")[1]
                )).get(0).getName() +
                "点赞了你的帖子"; // TODO 附加帖子标题
    }

    private static String PinTaskTitle(Message message) {
        return MESSTYPE.APPLY.getType() +
                "地图钉" +
                pinService.getPinById(Integer.parseInt(
                        message.getPara()
                )).getName() +
                "申请公开";
    }

    private static String PostReportTitale(Message message) {
        return MESSTYPE.REPORT.getType() +
                "帖子" +
                "" +  // TODO 获取帖子标题
                "被举报";
    }

    private static String ReplyTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                userService.findUserById(Integer.parseInt(
                        message.getPara().split(";")[1]
                )).get(0).getName() +
                "回复了你";
    }
}
