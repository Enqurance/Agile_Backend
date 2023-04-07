# 创建pin表的SQL语句
CREATE TABLE `buaa_map`.`pin` (
    `p_id` INT NOT NULL AUTO_INCREMENT COMMENT '地图钉id，系统自动生成' ,
    `p_name` VARCHAR(100) NOT NULL COMMENT '地图钉名' ,
    `p_pos` VARCHAR(100) NOT NULL COMMENT '地图钉位置' ,
    `p_private` BOOLEAN NOT NULL COMMENT '地图钉权限，TRUE为私人，false为公开' ,
    `p_comment` VARCHAR(2000) NOT NULL COMMENT '地图钉相关介绍' ,
    `p_tag` VARCHAR(100) NOT NULL COMMENT '地图钉Tag' ,
    `f_id` INT NOT NULL COMMENT '地图钉相关论坛id' ,
    PRIMARY KEY (`p_id`) ,
    FOREIGN KEY (`f_id`) REFERENCES forum(f_id)
                          ) ENGINE = InnoDB;
