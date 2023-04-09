# 创建service表的SQL语句
CREATE TABLE `buaa_map`.`service` (
    `s_id` INT NOT NULL AUTO_INCREMENT COMMENT '服务id，系统自动生成' ,
    `s_name` VARCHAR(100) NOT NULL COMMENT '服务名' ,
    `s_brief` VARCHAR(2000) NOT NULL COMMENT '服务简介' ,
    `ph_id` INT NOT NULL COMMENT '服务相关照片组id' ,
    PRIMARY KEY (`s_id`) ,
    FOREIGN KEY (`ph_id`) REFERENCES photo(ph_id)
                          ) ENGINE = InnoDB;