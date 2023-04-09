# ����pin���SQL���
CREATE TABLE `buaa_map`.`pin` (
    `p_id` INT NOT NULL AUTO_INCREMENT COMMENT '��ͼ��id��ϵͳ�Զ�����' ,
    `p_name` VARCHAR(100) NOT NULL COMMENT '��ͼ����' ,
    `p_pos` VARCHAR(100) NOT NULL COMMENT '��ͼ��λ��' ,
    `p_brief` VARCHAR(2000) NOT NULL COMMENT '��ͼ����ؽ���' ,
    `p_tag` VARCHAR(100) NOT NULL COMMENT '��ͼ��Tag' ,
    `p_openTime` VARCHAR(100) NOT NULL COMMENT '�ص㿪��ʱ��' ,
    `p_phone` VARCHAR(100) NOT NULL COMMENT '�ص���ϵ�绰' ,
    `u_id` INT NOT NULL COMMENT '�û�id�������û�����pinΪ˽�еģ�����Ա����pinΪ������' ,
    `ph_id` INT NOT NULL COMMENT '��ͼ�������Ƭ��id' ,
    `f_id` INT NOT NULL COMMENT '��ͼ�������̳id' ,
    PRIMARY KEY (`p_id`) ,
    FOREIGN KEY (`u_id`) REFERENCES user(u_id) ,
    FOREIGN KEY (`ph_id`) REFERENCES photo(ph_id) ,
    FOREIGN KEY (`f_id`) REFERENCES forum(f_id)
                          ) ENGINE = InnoDB;