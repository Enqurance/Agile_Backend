# 创建tfeedback表的SQL语句
CREATE TABLE `buaa_map`.`tfeedback`
(
    `tfeedback_id`      INT          NOT NULL AUTO_INCREMENT COMMENT '任务id，系统自动生成',
    `p_id`              INT          NOT NULL COMMENT '地图钉id',
    `tfeedback_title`   VARCHAR(200) NOT NULL COMMENT '反馈标题',
    `tfeedback_content` VARCHAR(500) NOT NULL COMMENT '反馈理由',
    `u_id`              INT          NOT NULL COMMENT '反馈用户id',
    PRIMARY KEY (`tfeedback_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
