# ����pin���SQL���
CREATE TABLE `buaa_map`.`pin` (
    `p_id` INT NOT NULL AUTO_INCREMENT COMMENT '��ͼ��id��ϵͳ�Զ�����' ,
    `p_name` VARCHAR(100) NOT NULL COMMENT '��ͼ����' ,
    `p_pos` VARCHAR(100) NOT NULL COMMENT '��ͼ��λ��' ,
    `p_private` BOOLEAN NOT NULL COMMENT '��ͼ��Ȩ�ޣ�TRUEΪ˽�ˣ�falseΪ����' ,
    `p_comment` VARCHAR(2000) NOT NULL COMMENT '��ͼ����ؽ���' ,
    `p_tag` VARCHAR(100) NOT NULL COMMENT '��ͼ��Tag' ,
    `f_id` INT NOT NULL COMMENT '��ͼ�������̳id' ,
    PRIMARY KEY (`p_id`) ,
    FOREIGN KEY (`f_id`) REFERENCES forum(f_id)
                          ) ENGINE = InnoDB;
