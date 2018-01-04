--学生表
CREATE TABLE `hc_student` (
  `school_id`  char(6) NOT NULL  COMMENT '学校id',
   `class_id` char(32) NOT NULL  COMMENT '班级id',
  `id` char(32) NOT NULL  COMMENT '学生Id',
  `name` varchar(50) not null COMMENT '学生姓名',
  `phone` varchar(13) not null  COMMENT '学生电话',
  `code` varchar(10)  null  COMMENT '学号',
  `is_apply` char(1) not null default '0' COMMENT '是否报名1:报名,0:未报名',
  `apply_time` timestamp  NULL  COMMENT '报名时间' ,
  `is_pay` char(1) not null default '0' COMMENT '是否缴费 1:已缴费,0:未缴费',
  `pay_time` timestamp  NULL  COMMENT '缴费时间' ,
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='学生表'