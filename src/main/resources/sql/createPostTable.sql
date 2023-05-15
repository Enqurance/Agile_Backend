# 创建post表的SQL语句
CREATE TABLE `buaa_map`.`post` (
    `post_id` INT NOT NULL AUTO_INCREMENT COMMENT '帖子id，系统自动生成' ,
    `post_title` VARCHAR(100) NOT NULL COMMENT '帖子标题名' ,
    `post_content` VARCHAR(10000) NOT NULL COMMENT '帖子内容' ,
    `post_visibility` INT NOT NULL COMMENT '帖子可见性' ,
    `post_tag` INT NOT NULL COMMENT '帖子对应tag' ,
    `post_thumbs_up` INT NOT NULL COMMENT '帖子点赞数' ,
    `post_visit` INT NOT NULL COMMENT '帖子访问量' ,
    `post_floor_num` INT NOT NULL COMMENT '帖子总楼数' ,
    `pin_id_str` VARCHAR(1000) NOT NULL COMMENT '相关地图钉id组成的字符串，id之间用;隔开' ,
    `create_time` DATE NOT NULL COMMENT '创建时间',
    `user_id` INT NOT NULL COMMENT '用户id' ,
    PRIMARY KEY (`post_id`) ,
    FOREIGN KEY (`user_id`) REFERENCES user(u_id)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
