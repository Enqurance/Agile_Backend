# ����service���SQL���
CREATE TABLE `buaa_map`.`service` (
    `s_id` INT NOT NULL AUTO_INCREMENT COMMENT '����id��ϵͳ�Զ�����' ,
    `s_name` VARCHAR(100) NOT NULL COMMENT '������' ,
    `s_brief` VARCHAR(2000) NOT NULL COMMENT '������' ,
    `ph_id` INT NOT NULL COMMENT '���������Ƭ��id' ,
    PRIMARY KEY (`s_id`) ,
    FOREIGN KEY (`ph_id`) REFERENCES photo(ph_id)
                          ) ENGINE = InnoDB;