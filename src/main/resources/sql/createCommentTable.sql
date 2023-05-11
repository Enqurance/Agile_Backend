# 创建comment表的SQL语句
CREATE TABLE `buaa_map`.`comment` (
    `comment_id` INT NOT NULL AUTO_INCREMENT COMMENT '回复id，系统自动生成' ,
    `c_content` VARCHAR(1000) NOT NULL COMMENT '回复内容' ,
    `floor_id` INT NOT NULL COMMENT '所属楼层id，外键' ,
    `cuser_id` INT NOT NULL COMMENT '创建用户id，外键' ,
    `ruser_id` INT COMMENT '回复用户id，外键，可以为空，代表没有回复' ,
    PRIMARY KEY (`comment_id`) ,
    FOREIGN KEY (`floor_id`) REFERENCES floor(floor_id) ,
    FOREIGN KEY (`cuser_id`) REFERENCES user(u_id) ,
    FOREIGN KEY (`ruser_id`) REFERENCES user(u_id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
