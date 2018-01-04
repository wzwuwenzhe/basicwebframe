--操作员表
CREATE TABLE `hc_operator` (
  `id` varchar(32) NOT NULL  COMMENT '操作员ID',
  `school_id` char(6) NOT NULL  COMMENT '学校ID',
  `user_type` char(1) default '2' NOT NULL  COMMENT '操作员类型:1超管;2:教师,3:学生',
  `login_name` varchar(20) NOT NULL  COMMENT '登录名',
  `pwd` varchar(100) NOT NULL  COMMENT '加密后的密码',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='操作员表'

