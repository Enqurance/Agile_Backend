# 创建message表的SQL语句
CREATE TABLE `buaa_map`.`tfloor`
(
    `tfloor_id`     INT          NOT NULL AUTO_INCREMENT COMMENT '任务id，系统自动生成',
    `floor_id`      INT          NOT NULL COMMENT '楼层id',
    `tfloor_reason` VARCHAR(200) NOT NULL COMMENT '举报原因',
    `u_id`          INT          NOT NULL COMMENT '举报人id',
    PRIMARY KEY (`tfloor_id`),
    FOREIGN KEY (`floor_id`) REFERENCES buaa_map.floor (`floor_id`),
    FOREIGN KEY (`u_id`) REFERENCES buaa_map.user (`u_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;