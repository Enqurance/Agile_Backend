# 创建user表的SQL语句
CREATE TABLE `buaa_map`.`suggestion` (
    `sug_id` INT NOT NULL AUTO_INCREMENT COMMENT '用户id，系统自动生成' ,
    `sug_str` VARCHAR(2000) NOT NULL COMMENT '用户相关建议' ,
    `u_id` INT NOT NULL COMMENT '用户id，外键' ,
    PRIMARY KEY (`sug_id`) ,
    FOREIGN KEY (`u_id`) REFERENCES user(u_id)
                          ) ENGINE = InnoDB DEFAULT CHARSET=utf8;
