--学校表
CREATE TABLE `hc_school` (
  `id` char(6) NOT NULL  COMMENT '学校id',
  `name` varchar(50) not null COMMENT '学校名称',
  `admin` varchar(500) null COMMENT '学校管理员id',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='学校表'