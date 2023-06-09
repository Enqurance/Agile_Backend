# 创建pin表的SQL语句
CREATE TABLE `buaa_map`.`pin` (
    `p_id` INT NOT NULL AUTO_INCREMENT COMMENT '地图钉id，系统自动生成' ,
    `p_name` VARCHAR(100) NOT NULL COMMENT '地图钉名' ,
    `p_lnglat` VARCHAR(100) NOT NULL COMMENT '地图钉坐标' ,
    `p_pos` VARCHAR(100) COMMENT '地图钉位置' ,
    `p_brief` VARCHAR(2000) NOT NULL COMMENT '地图钉相关介绍' ,
    `p_type` INT NOT NULL COMMENT '地图钉Tag' ,
    `p_openTime` VARCHAR(100) COMMENT '地点开放时间' ,
    `p_phone` VARCHAR(100) COMMENT '地点联系电话' ,
    `p_visibility` INT NOT NULL COMMENT '0代表私人，1代表公开' ,
    `u_id` INT NOT NULL COMMENT '用户id，个人用户创建pin为私有的，管理员创建pin为公开的' ,
    `f_id` INT NOT NULL COMMENT '地图钉相关论坛id' ,
    PRIMARY KEY (`p_id`) ,
    FOREIGN KEY (`u_id`) REFERENCES user(u_id) ,
    FOREIGN KEY (`f_id`) REFERENCES forum(f_id) ,
    FULLTEXT KEY `pin_index` (`p_name`, `p_brief`) WITH PARSER `ngram`
                          ) ENGINE = InnoDB DEFAULT CHARSET=utf8;