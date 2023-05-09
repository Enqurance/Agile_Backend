# 创建message表的SQL语句
CREATE TABLE `buaa_map`.`message`
(
    `texamine_id`      INT           NOT NULL AUTO_INCREMENT COMMENT '任务id，系统自动生成',
    `post_id`          INT           NOT NULL COMMENT '原帖子内容',
    `texamine_title`   VARCHAR(200)  NOT NULL COMMENT '修改后的标题',
    `texamine_content` VARCHAR(2000) NOT NULL COMMENT '修改后的内容',
    PRIMARY KEY (`texamine_id`),
    FOREIGN KEY (`post_id`) REFERENCES buaa_map.post (`post_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;