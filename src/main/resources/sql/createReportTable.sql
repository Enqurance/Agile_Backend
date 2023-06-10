#创建treport表的SQL语句
CREATE TABLE `buaa_map`.`treport`
(
    `treport_id`   INT NOT NULL AUTO_INCREMENT COMMENT '任务id，系统自动生成',
    `o_id`           INT NOT NULL COMMENT '举报内容id',
    `treport_type` INT NOT NULL COMMENT '举报内容类型',
    PRIMARY KEY (`treport_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;