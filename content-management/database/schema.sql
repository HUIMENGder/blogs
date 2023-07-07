CREATE TABLE `exam_question_set` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
  `question_set_id` varchar(32) UNIQUE NOT NULL COMMENT '题集ID',
  `name` varchar(255) NOT NULL COMMENT '题集名称',
  `file_id` varchar(16) NOT NULL COMMENT '文件ID',
  `file_name` varchar(255) NOT NULL COMMENT '文件名称',
  `question_year` int NOT NULL COMMENT '年份',
  `college_name` varchar(255) NOT NULL COMMENT '学校名称',
  `major_name` varchar(255) NOT NULL COMMENT '专业名称',
  `subject_name` varchar(255) NOT NULL COMMENT '科目名称',
  `national_score_line` int NOT NULL COMMENT '国家线',
  `college_score_line` int NOT NULL COMMENT '院校线',
  `subject_category` ENUM ('PHILOSOPHY', 'ECONOMICS', 'LAW', 'EDUCATION', 'LITERATURE', 'HISTORY', 'SCIENCE', 'ENGINEERING', 'AGRICULTURE', 'MEDICINE', 'MANAGEMENT', 'ART', 'CROSS', 'PE') NOT NULL COMMENT '学科门类',
  `reference_url` varchar(2048) COMMENT '参考链接',
  `create_time` datetime NOT NULL COMMENT '创建时间'
);

CREATE TABLE `discussion` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
  `discussion_id` varchar(32) UNIQUE NOT NULL COMMENT '帖子ID',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `author_id` varchar(32) NOT NULL COMMENT '作者ID',
  `author_name` varchar(255) NOT NULL COMMENT '作者名称',
  `visit_count` int NOT NULL COMMENT '访问量',
  `good_count` int NOT NULL COMMENT '点赞量',
  `tags` varchar(255) COMMENT '标签',
  `reply_to` varchar(32) COMMENT '回复的帖子ID或租房ID，若为null则为主贴',
  `create_time` datetime NOT NULL COMMENT '创建时间'
);

CREATE TABLE `rent_house` (
  `id` int PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
  `house_id` varchar(32) UNIQUE NOT NULL COMMENT '房屋ID',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `location` varchar(255) NOT NULL COMMENT '位置',
  `price` int NOT NULL COMMENT '价格',
  `photos_id` varchar(255) NOT NULL COMMENT '图片ID',
  `author_id` varchar(32) NOT NULL COMMENT '作者ID',
  `author_name` varchar(255) NOT NULL COMMENT '作者名称',
  `visit_count` int NOT NULL COMMENT '访问量',
  `good_count` int NOT NULL COMMENT '点赞量',
  `tags` varchar(255) COMMENT '标签',
  `create_time` datetime NOT NULL COMMENT '创建时间'
);

CREATE INDEX `exam_question_set_index_0` ON `exam_question_set` (`question_set_id`);

CREATE INDEX `exam_question_set_index_1` ON `exam_question_set` (`question_year`);

CREATE INDEX `exam_question_set_index_2` ON `exam_question_set` (`college_name`);

CREATE INDEX `exam_question_set_index_3` ON `exam_question_set` (`subject_name`);

CREATE INDEX `discussion_index_4` ON `discussion` (`discussion_id`);

CREATE INDEX `discussion_index_5` ON `discussion` (`author_id`);

CREATE INDEX `discussion_index_6` ON `discussion` (`reply_to`);

CREATE INDEX `rent_house_index_7` ON `rent_house` (`house_id`);

CREATE INDEX `rent_house_index_8` ON `rent_house` (`author_id`);

ALTER TABLE `exam_question_set` COMMENT = '真题题集表';

ALTER TABLE `discussion` COMMENT = '帖子表';

ALTER TABLE `rent_house` COMMENT = '租房房屋信息';
