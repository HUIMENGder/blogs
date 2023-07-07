CREATE TABLE `user_info` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(32) UNIQUE NOT NULL COMMENT '用户ID',
  `gender` ENUM ('MALE', 'FEMALE') NOT NULL COMMENT '性别',
  `birthday_year` int NOT NULL COMMENT '生日年份',
  `target_college` varchar(128) NOT NULL COMMENT '目标院校',
  `target_major` varchar(128) NOT NULL COMMENT '目标专业',
  `create_time` datetime NOT NULL COMMENT '创建时间'
);

CREATE INDEX `user_info_index_0` ON `user_info` (`user_id`);

ALTER TABLE `user_info` COMMENT = '用户信息表';
