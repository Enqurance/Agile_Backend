# 创建message表的SQL语句
CREATE TABLE `buaa_map`.`message`
(
    `m_id`        INT      NOT NULL AUTO_INCREMENT COMMENT '消息id，系统自动生成',
    `m_content`   VARCHAR(2000) COMMENT '消息内容',
    `m_status`    BOOLEAN  NOT NULL COMMENT '消息状态，0为未读，1为已读',
    `m_para`      VARCHAR(50) COMMENT '消息携带的参数',
    `m_type`      SMALLINT NOT NULL COMMENT '消息类型',
    `ureceive_id` INT      NOT NULL COMMENT '消息接收用户',
    `m_time`      DATE     NOT NULL COMMENT '消息生成时间',
    PRIMARY KEY (`m_id`),
    FOREIGN KEY (`ureceive_id`) REFERENCES buaa_map.user (`u_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;