# ����photo���SQL���
CREATE TABLE `buaa_map`.`photo` (
    `ph_id` INT NOT NULL AUTO_INCREMENT COMMENT '��Ƭ��id��ϵͳ�Զ�����' ,
    `ph_url` VARCHAR(2000) NOT NULL COMMENT '��Ƭ��url' ,
    `p_id` INT NOT NULL COMMENT '��ͼ��id�����' ,
    PRIMARY KEY (`ph_id`) ,
    FOREIGN KEY (`p_id`) REFERENCES pin(p_id)
                          ) ENGINE = InnoDB DEFAULT CHARSET=utf8;