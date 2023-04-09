# 创建service表的SQL语句
CREATE TABLE `buaa_map`.`pin_service` (
    `p_s_id` INT NOT NULL AUTO_INCREMENT COMMENT '标识id，系统自动生成' ,
    `p_id` INT NOT NULL COMMENT '相关地图钉id' ,
    `s_id` INT NOT NULL COMMENT '服务id' ,
    PRIMARY KEY (`p_s_id`) ,
    FOREIGN KEY (`p_id`) REFERENCES pin(p_id) ,
    FOREIGN KEY (`s_id`) REFERENCES service(s_id)
                      ) ENGINE = InnoDB;