# 创建message表的SQL语句
CREATE TABLE `buaa_map`.`texamine`
(
    `texamine_id`      INT NOT NULL AUTO_INCREMENT COMMENT '任务id，系统自动生成',
    `post_id`          INT NOT NULL COMMENT '原帖子内容',
    `texamine_title`   VARCHAR(200) COMMENT '修改后的标题',
    `texamine_content` VARCHAR(2000) COMMENT '修改后的内容',
    `texamine_basis`   VARCHAR(500) COMMENT '审核原因',
    PRIMARY KEY (`texamine_id`),
    FOREIGN KEY (`post_id`) REFERENCES buaa_map.post (`post_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;