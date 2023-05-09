# 创建tpin表的SQL语句
CREATE TABLE `buaa_map`.`tpin`
(
    `tpin_id` INT NOT NULL AUTO_INCREMENT COMMENT '任务id，系统自动生成',
    `p_id`    INT NOT NULL COMMENT '地图钉id',
    PRIMARY KEY (`tpin_id`),
    FOREIGN KEY (`p_id`) REFERENCES buaa_map.pin (`p_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;