# 创建user表的SQL语句
CREATE TABLE `buaa_map`.`user`
(
    `u_id`          INT          NOT NULL AUTO_INCREMENT COMMENT '用户id，系统自动生成',
    `u_name`        VARCHAR(100) NOT NULL COMMENT '用户名',
    `u_email`       VARCHAR(100) NOT NULL COMMENT '用户邮箱，不可重复',
    `u_type`        BOOLEAN      NOT NULL COMMENT '用户类型，TRUE为管理员',
    `u_password`    VARCHAR(100) NOT NULL COMMENT '用户密码，加密',
    `u_icon`        VARCHAR(200) NOT NULL COMMENT '头像路径',
    `u_campus`      VARCHAR(100) NOT NULL COMMENT '用户校区',
    `u_grade`       VARCHAR(100) NOT NULL COMMENT '用户年级',
    `u_gender`      INT          NOT NULL COMMENT '用户性别',
    `u_description` VARCHAR(200) NOT NULL COMMENT '个人简介',
    PRIMARY KEY (`u_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
