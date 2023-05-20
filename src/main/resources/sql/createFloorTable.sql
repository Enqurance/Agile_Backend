# 创建floor表的SQL语句
CREATE TABLE `buaa_map`.`floor` (
    `floor_id` INT NOT NULL AUTO_INCREMENT COMMENT '楼层id，系统自动生成' ,
    `floor_content` VARCHAR(2000) NOT NULL COMMENT '楼层内容' ,
    `floor_layers` INT NOT NULL COMMENT '帖子的第几楼' ,
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `user_id` INT NOT NULL COMMENT '用户id' ,
    `post_id` INT NOT NULL COMMENT '楼层相关帖子id' ,
    PRIMARY KEY (`floor_id`) ,
    FOREIGN KEY (`user_id`) REFERENCES user(u_id) ,
    FOREIGN KEY (`post_id`) REFERENCES post(post_id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
