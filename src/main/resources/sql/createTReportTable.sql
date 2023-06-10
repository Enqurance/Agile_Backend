# 创建report表的SQL语句
CREATE TABLE `buaa_map`.`report`
(
    `r_id`     INT          NOT NULL AUTO_INCREMENT COMMENT '举报id，系统自动生成',
    `o_id`     INT          NOT NULL COMMENT '举报内容id',
    `r_reason` VARCHAR(500) NOT NULL COMMENT '举报原因',
    `u_id`     INT          NOT NULL COMMENT '举报人id',
    `r_type`   INT          NOT NULL COMMENT '举报内容类型',
    PRIMARY KEY (`r_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;