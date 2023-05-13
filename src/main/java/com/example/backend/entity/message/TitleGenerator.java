package com.example.backend.entity.message;

import com.example.backend.domain.Message;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @className: TitleGenerator
 * @Description: 根据Message的type生成不同title
 * @author: WAN
 * @date: 2023/5/9 16:16
 */
@Component
public class TitleGenerator {
    private static UserService userService;

    private static PinService pinService;

    private static PostService postService;

    private static FloorService floorService;

    private static CommentService commentService;

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
            default -> title = "错误的消息种类";
        }
        return title;
    }

    @Autowired
    public void setUserService(UserService userService) {
        TitleGenerator.userService = userService;
    }

    @Autowired
    public void setPinService(PinService pinService) {
        TitleGenerator.pinService = pinService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        TitleGenerator.postService = postService;
    }

    @Autowired
    public void setFloorService(FloorService floorService) {
        TitleGenerator.floorService = floorService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        TitleGenerator.commentService = commentService;
    }

    private static String likeTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        return MESSTYPE.NOTICE.getType() +
                userService.findUserById(paras[1]).get(0).getName() +
                "点赞了你的帖子" +
                postService.getPostById(paras[0]).getTitle();
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
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        return MESSTYPE.NOTICE.getType() +
                "您的帖子" +
                postService.getPostById(paras[0]).getTitle() +
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
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        return MESSTYPE.REPORT.getType() +
                "帖子" +
                postService.getPostById(paras[0]).getTitle() +
                "被举报";
    }

    private static String floorReportTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        return MESSTYPE.REPORT.getType() +
                "楼层" +
                floorService.getFloorById(paras[0]).getContent() +
                "被举报";
    }

    private static String commentReportTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        return MESSTYPE.REPORT.getType() +
                "评论" +
                commentService.getCommentById(paras[0]).getContent() +
                "被举报";
    }

    private static String examinePostTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        return MESSTYPE.APPLY.getType() +
                "帖子" +
                postService.getPostById(paras[0]).getTitle() +
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
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        return MESSTYPE.NOTICE.getType() +
                "您对帖子" +
                postService.getPostById(paras[0]).getTitle() +
                "的举报" +
                (paras[1] == 1 ? "成功" : "失败");
    }

    private static String floorReportResultTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        return MESSTYPE.NOTICE.getType() +
                "您对楼层" +
                "" +   // TODO 获取楼层内容
                "的举报" +
                (paras[1] == 1 ? "成功" : "失败");
    }

    private static String commentReportResultTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        return MESSTYPE.NOTICE.getType() +
                "您对评论" +
                "" +   // TODO 获取评论内容
                "的举报" +
                (paras[1] == 1 ? "成功" : "失败");
    }
}
