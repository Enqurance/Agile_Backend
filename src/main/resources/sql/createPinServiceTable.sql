# ����service���SQL���
CREATE TABLE `buaa_map`.`pin_service` (
    `p_s_id` INT NOT NULL AUTO_INCREMENT COMMENT '��ʶid��ϵͳ�Զ�����' ,
    `p_id` INT NOT NULL COMMENT '��ص�ͼ��id' ,
    `s_id` INT NOT NULL COMMENT '����id' ,
    PRIMARY KEY (`p_s_id`) ,
    FOREIGN KEY (`p_id`) REFERENCES pin(p_id) ,
    FOREIGN KEY (`s_id`) REFERENCES service(s_id)
                      ) ENGINE = InnoDB;