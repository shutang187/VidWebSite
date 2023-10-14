
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                           `user_id` bigint NOT NULL COMMENT '用户id',
                           `url` varchar(500) NOT NULL COMMENT '视频链接',
                           `thumbnail` varchar(500) NOT NULL COMMENT '封面链接',
                           `title` varchar(255) NOT NULL COMMENT '视频标题',
                           `type` varchar(5) NOT NULL COMMENT '视频类型：0原创，1转载',
                           `duration` varchar(255) NOT NULL COMMENT '视频时长',
                           `area` varchar(255) NOT NULL COMMENT '所在分区:0鬼畜，1音乐，2电影',
                           `description` text COMMENT '视频简介',
                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频投稿记录表';

DROP TABLE IF EXISTS `t_video_coin`;
CREATE TABLE `t_video_coin` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                `user_id` bigint DEFAULT NULL COMMENT '用户id',
                                `video_id` bigint DEFAULT NULL COMMENT '视频投稿id',
                                `amount` int DEFAULT NULL COMMENT '投币数',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频投币记录表';

DROP TABLE IF EXISTS `t_video_collection`;
CREATE TABLE `t_video_collection` (
                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                      `video_id` bigint DEFAULT NULL COMMENT '视频投稿id',
                                      `user_id` bigint DEFAULT NULL COMMENT '用户id',
                                      `group_id` bigint DEFAULT NULL COMMENT '收藏分组',
                                      `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频收藏表';


DROP TABLE IF EXISTS `t_video_comment`;
CREATE TABLE `t_video_comment` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                   `video_id` bigint NOT NULL COMMENT '视频id',
                                   `user_id` bigint NOT NULL COMMENT '用户id',
                                   `comment` text NOT NULL COMMENT '评论',
                                   `reply_user_id` bigint DEFAULT NULL COMMENT '回复用户id',
                                   `root_id` bigint DEFAULT NULL COMMENT '根结点评论id',
                                   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频评论表';

DROP TABLE IF EXISTS `t_video_like`;
CREATE TABLE `t_video_like` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                `user_id` bigint NOT NULL COMMENT '用户id',
                                `video_id` bigint NOT NULL COMMENT '视频投稿id',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频点赞表';

DROP TABLE IF EXISTS `t_video_operation`;
CREATE TABLE `t_video_operation` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                     `user_id` bigint DEFAULT NULL COMMENT '用户id',
                                     `video_id` bigint DEFAULT NULL COMMENT '视频id',
                                     `operation_type` varchar(5) DEFAULT NULL COMMENT '操作类型:0点赞，1收藏，2投币',
                                     `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户操作表';

DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                         `name` varchar(255) DEFAULT NULL COMMENT '名称',
                         `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='标签表';

DROP TABLE IF EXISTS `t_video_tag`;
CREATE TABLE `t_video_tag` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                               `video_id` bigint NOT NULL COMMENT '视频id',
                               `tag_id` bigint NOT NULL COMMENT '标签id',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频标签关联表';

DROP TABLE IF EXISTS `t_video_view`;
CREATE TABLE `t_video_view` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                `video_id` bigint NOT NULL COMMENT '视频id',
                                `user_id` bigint DEFAULT NULL COMMENT '用户id',
                                `client_id` varchar(500) DEFAULT NULL COMMENT '客户端id',
                                `ip` varchar(50) DEFAULT NULL COMMENT 'ip',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频观看记录表';

DROP TABLE IF EXISTS `t_danmu`;
CREATE TABLE `t_danmu` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                           `user_id` bigint DEFAULT NULL COMMENT '用户id',
                           `video_id` bigint DEFAULT NULL COMMENT '视频id',
                           `content` text COMMENT '弹幕内容',
                           `danmu_time` varchar(50) DEFAULT NULL COMMENT '弹幕出现时间',
                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='弹幕表';

DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                          `url` varchar(500) DEFAULT NULL COMMENT '文件存储路径',
                          `type` varchar(50) DEFAULT NULL COMMENT '文件类型',
                          `md5` varchar(500) DEFAULT NULL COMMENT '文件MD5唯一标识',
                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='上传文件信息表';