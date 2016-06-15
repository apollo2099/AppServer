CREATE TABLE `ma_user` (
  `user_id` int(11) NOT NULL COMMENT 'id',
  `user_code` varchar(32) NOT NULL COMMENT '登录用户名',
  `user_password` varchar(32) NOT NULL COMMENT '登录用户密码',
  `user_name` varchar(32) NOT NULL COMMENT '真实名子',
  `user_sex` char(2) NOT NULL COMMENT '性别',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `address` varchar(256) DEFAULT NULL COMMENT '联系地址',
  `telephone` varchar(16) NOT NULL COMMENT '联系电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
