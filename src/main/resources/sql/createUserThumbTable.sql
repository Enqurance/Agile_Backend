# 创建userthumb表的SQL语句
CREATE TABLE `buaa_map`.`userthumb`
(
    `thumb_id`   INT NOT NULL AUTO_INCREMENT COMMENT '用户点赞id，系统自动生成',
    `post_id` INT NOT NULL COMMENT '帖子id',
    `user_id` INT NOT NULL COMMENT '用户id',
    PRIMARY KEY (`thumb_id`),
    FOREIGN KEY (`post_id`) REFERENCES post (post_id),
    FOREIGN KEY (`user_id`) REFERENCES user (u_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
