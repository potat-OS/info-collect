
-- 2019����ѧ����ѧ����Ϣ��
CREATE TABLE `stu_info_2019` (
  `stu_id` char(12) NOT NULL, -- ѧ��
  `real_name` varchar(32) NOT NULL, -- ��ʵ����
  `phone_num` char(11) NOT NULL, -- �Լ��绰
  `department` varchar(32) DEFAULT NULL, -- ѧԺ
  `major` varchar(32) DEFAULT NULL, -- רҵ
  `class_name` varchar(32) DEFAULT NULL, -- �༶
  `address` varchar(32) DEFAULT NULL, -- ��ͥסַ
  `parent1` varchar(32) DEFAULT NULL, -- ��ͥ��ϵ��1
  `parent1_phone_num` varchar(32) DEFAULT NULL, -- ��ϵ��1�绰
  `parent2` varchar(32) DEFAULT NULL, -- ��ͥ��ϵ��2
  `parent2_phone_num` varchar(32) DEFAULT NULL, -- ��ϵ��2�绰
  PRIMARY KEY (`stu_id`) -- ��������
) ENGINE=InnoDB DEFAULT CHARSET=utf8 


