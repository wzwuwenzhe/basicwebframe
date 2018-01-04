--教师表
CREATE TABLE `hc_teacher` (
  `school_id`  char(6) NOT NULL  COMMENT '学校id',
  `id` char(32) NOT NULL  COMMENT '教师Id',
  `name` varchar(50) not null COMMENT '教师姓名',
  `phone` varchar(13) not null  COMMENT '教师电话',
  `is_apply` char(1) not null default '0' COMMENT '是否报名1:报名,0:未报名',
  `apply_time` timestamp  NULL  COMMENT '报名时间' ,
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='教师表'