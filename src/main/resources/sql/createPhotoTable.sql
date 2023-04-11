# 创建photo表的SQL语句
CREATE TABLE `buaa_map`.`photo` (
    `ph_id` INT NOT NULL AUTO_INCREMENT COMMENT '照片组id，系统自动生成' ,
    `ph_url` VARCHAR(2000) NOT NULL COMMENT '照片组url' ,
    PRIMARY KEY (`ph_id`)
                          ) ENGINE = InnoDB DEFAULT CHARSET=utf8;