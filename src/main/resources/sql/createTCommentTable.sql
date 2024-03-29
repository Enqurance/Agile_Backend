# 创建tcomment表的SQL语句
CREATE TABLE `buaa_map`.`tcomment`
(
    `tcomment_id`     INT           NOT NULL AUTO_INCREMENT COMMENT '任务id，系统自动生成',
    `comment_id`      INT           NOT NULL COMMENT '评论id',
    `tcomment_reason` VARCHAR(2000) NOT NULL COMMENT '举报原因',
    `u_id`            VARCHAR(100)  NOT NULL COMMENT '举报人id',
    PRIMARY KEY (`tcomment_id`),
    FOREIGN KEY (`comment_id`) REFERENCES buaa_map.comment (`comment_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;