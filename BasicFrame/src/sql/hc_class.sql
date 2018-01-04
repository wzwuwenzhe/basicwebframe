--班级表
CREATE TABLE `hc_class` (
  `school_id`  char(6) NOT NULL  COMMENT '学校id',
  `id` char(32) NOT NULL  COMMENT '班级id',
  `name` varchar(50) not null COMMENT '班级名称',
  `teacher_ids` varchar(1000)  null COMMENT '教师id集合',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='班级表'