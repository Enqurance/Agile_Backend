# 创建tpost表的SQL语句
CREATE TABLE `buaa_map`.`tpost`
(
    `tpost_id`     INT          NOT NULL AUTO_INCREMENT COMMENT '任务id，系统自动生成',
    `post_id`      INT          NOT NULL COMMENT '帖子id',
    `tpost_reason` VARCHAR(200) NOT NULL COMMENT '举报原因',
    `u_id`         INT          NOT NULL COMMENT '举报人id',
    PRIMARY KEY (`tpost_id`),
    FOREIGN KEY (`post_id`) REFERENCES buaa_map.post (`post_id`),
    FOREIGN KEY (`u_id`) REFERENCES buaa_map.user (`u_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;