
-- 2019年入学新生学生信息表
CREATE TABLE `stu_info_2019` (
  `stu_id` char(12) NOT NULL, -- 学号
  `real_name` varchar(32) NOT NULL, -- 真实姓名
  `phone_num` char(11) NOT NULL, -- 自己电话
  `department` varchar(32) DEFAULT NULL, -- 学院
  `major` varchar(32) DEFAULT NULL, -- 专业
  `class_name` varchar(32) DEFAULT NULL, -- 班级
  `address` varchar(32) DEFAULT NULL, -- 家庭住址
  `parent1` varchar(32) DEFAULT NULL, -- 家庭联系人1
  `parent1_phone_num` varchar(32) DEFAULT NULL, -- 联系人1电话
  `parent2` varchar(32) DEFAULT NULL, -- 家庭联系人2
  `parent2_phone_num` varchar(32) DEFAULT NULL, -- 联系人2电话
  PRIMARY KEY (`stu_id`) -- 声明主键
) ENGINE=InnoDB DEFAULT CHARSET=utf8 


