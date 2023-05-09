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
            case 1 -> title = likeTitle(message);
            case 2 -> title = replyTitle(message);
            case 3 -> title = pinApplySuccessTitle(message);
            case 4 -> title = postReleaseSuccessTitle(message);
            case 5 -> title = pinTaskTitle(message);
            case 6 -> title = postReportTitle(message);
            case 7 -> title = floorReportTitle(message);
            case 8 -> title = commentReportTitle(message);
            case 9 -> title = examinePostTitle(message);
            case 10 -> title = pinApplyResultTitle(message);
            case 11 -> title = postReportResultTitle(message);
            case 12 -> title = floorReportResultTitle(message);
            case 13 -> title = commentReportResultTitle(message);
            default -> title = "";
        }
        return title;
    }

    private static String likeTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                userService.findUserById(Integer.parseInt(
                        message.getPara().split(";")[1]
                )).get(0).getName() +
                "点赞了你的帖子"; // TODO 附加帖子标题
    }

    private static String replyTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                userService.findUserById(Integer.parseInt(
                        message.getPara().split(";")[1]
                )).get(0).getName() +
                "回复了你";
    }

    private static String pinApplySuccessTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                "地图钉" +
                pinService.getPinById(Integer.parseInt(
                        message.getPara()
                )).getName() +
                "成功发起公开申请，请等待审核";
    }

    private static String postReleaseSuccessTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                "您的帖子" +
                "" +     // TODO 获取帖子标题
                "发布成功";
    }

    private static String pinTaskTitle(Message message) {
        return MESSTYPE.APPLY.getType() +
                "地图钉" +
                pinService.getPinById(Integer.parseInt(
                        message.getPara()
                )).getName() +
                "申请公开";
    }

    private static String postReportTitle(Message message) {
        return MESSTYPE.REPORT.getType() +
                "帖子" +
                "" +  // TODO 获取帖子标题
                "被举报";
    }

    private static String floorReportTitle(Message message) {
        return MESSTYPE.REPORT.getType() +
                "楼层" +
                "" +  // TODO 获取楼层内容
                "被举报";
    }

    private static String commentReportTitle(Message message) {
        return MESSTYPE.REPORT.getType() +
                "评论" +
                "" +      // TODO 获取评论的具体内容
                "被举报";
    }

    private static String examinePostTitle(Message message) {
        return MESSTYPE.APPLY.getType() +
                "帖子" +
                "" +  // TODO 获取标题
                "申请发布";
    }

    private static String pinApplyResultTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                "您的地图钉" +
                pinService.getPinById(Integer.parseInt(
                        message.getPara()
                )).getName() +
                "申请" +
                message.getPara().split(";")[1];
    }

    private static String postReportResultTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                "您对帖子" +
                "" +   // TODO 获取帖子标题
                "的举报" +
                message.getPara().split(";")[1];
    }

    private static String floorReportResultTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                "您对楼层" +
                "" +   // TODO 获取楼层内容
                "的举报" +
                message.getPara().split(";")[1];
    }

    private static String commentReportResultTitle(Message message) {
        return MESSTYPE.NOTICE.getType() +
                "您对评论" +
                "" +   // TODO 获取评论内容
                "的举报" +
                message.getPara().split(";")[1];
    }
}
