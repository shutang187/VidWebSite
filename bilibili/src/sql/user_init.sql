use vidwebsite;
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                          `phone` varchar(100) DEFAULT NULL COMMENT '手机号',
                          `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                          `password` varchar(255) DEFAULT NULL COMMENT '密码',
                          `salt` varchar(50) DEFAULT NULL COMMENT '盐值',
                          `nick` varchar(100) DEFAULT NULL COMMENT '昵称',
                          `avatar` varchar(1024) DEFAULT NULL COMMENT '头像',
                          `sign` text COMMENT '签名',
                          `gender` varchar(2) DEFAULT NULL COMMENT '性别：0男，1女，2未知',
                          `birth` varchar(20) DEFAULT NULL COMMENT '生日',
                          `role_id` bigint DEFAULT NULL COMMENT '角色id',
                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';


DROP TABLE IF EXISTS `t_user_coin`;
CREATE TABLE `t_user_coin` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                               `user_id` bigint DEFAULT NULL COMMENT '用户id',
                               `amount` bigint DEFAULT NULL COMMENT '硬币总数',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户硬币数量表';


DROP TABLE IF EXISTS `t_user_following`;
CREATE TABLE `t_user_following` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                    `user_id` bigint DEFAULT NULL COMMENT '用户id',
                                    `following_id` bigint DEFAULT NULL COMMENT '关注用户id',
                                    `group_id` bigint DEFAULT NULL COMMENT '关注分组id',
                                    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户关注表';

DROP TABLE IF EXISTS `t_following_group`;
CREATE TABLE `t_following_group` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                     `user_id` bigint DEFAULT NULL COMMENT '用户id',
                                     `name` varchar(50) DEFAULT NULL COMMENT '关注分组名称',
                                     `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `type` varchar(5) DEFAULT NULL COMMENT '关注分组类型：0特别关注，1悄悄关注，2默认关注，3用户自定义关注',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户关注分组表';


DROP TABLE IF EXISTS `t_collection_group`;
CREATE TABLE `t_collection_group` (
                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                      `user_id` bigint DEFAULT NULL COMMENT '用户id',
                                      `name` varchar(50) DEFAULT NULL COMMENT '收藏分组名称',
                                      `type` varchar(5) DEFAULT NULL COMMENT '收藏分组类型：0默认收藏分组',
                                      `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户收藏分组表';

DROP TABLE IF EXISTS `t_user_moments`;
CREATE TABLE `t_user_moments` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                  `user_id` bigint DEFAULT NULL COMMENT '用户id',
                                  `type` varchar(5) DEFAULT NULL COMMENT '动态类型：0视频，1直播，2动态专栏',
                                  `content_id` bigint DEFAULT NULL COMMENT '内容详情id',
                                  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户动态表';


DROP TABLE IF EXISTS `t_auth_role`;
CREATE TABLE `t_auth_role` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                               `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `code` varchar(50) DEFAULT NULL COMMENT '唯一编码',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限控制--角色表';

DROP TABLE IF EXISTS `t_auth_menu`;
CREATE TABLE `t_auth_menu` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                               `name` varchar(255) DEFAULT NULL COMMENT '菜单项目名称',
                               `code` varchar(50) DEFAULT NULL COMMENT '唯一编码',
                               `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限控制--页面访问表';



DROP TABLE IF EXISTS `t_auth_element_operation`;
CREATE TABLE `t_auth_element_operation` (
                                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                            `element_name` varchar(255) DEFAULT NULL COMMENT '页面元素名称',
                                            `element_code` varchar(50) DEFAULT NULL COMMENT '页面元素唯一编码',
                                            `operation_type` varchar(5) DEFAULT NULL COMMENT '操作类型：0可点击，1可见',
                                            `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                            `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限控制--页面元素操作表';

DROP TABLE IF EXISTS `t_auth_role_element_operation`;
CREATE TABLE `t_auth_role_element_operation` (
                                                 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                                 `role_id` bigint DEFAULT NULL COMMENT '角色id',
                                                 `element_operation_id` bigint DEFAULT NULL COMMENT '元素操作id',
                                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限控制--角色与元素操作关联表';

DROP TABLE IF EXISTS `t_auth_role_menu`;
CREATE TABLE `t_auth_role_menu` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                    `role_id` bigint DEFAULT NULL COMMENT '角色id',
                                    `menu_id` bigint DEFAULT NULL COMMENT '页面菜单id',
                                    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限控制--角色页面菜单关联表';


DROP TABLE IF EXISTS t_sys_oper_log;
CREATE TABLE `sys_oper_log`  (
                                 `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
                                 `title` varchar(50)  NULL DEFAULT '' COMMENT '模块标题',
                                 `business_type` int NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
                                 `method` varchar(100) NULL DEFAULT '' COMMENT '方法名称',
                                 `request_method` varchar(10)  NULL DEFAULT '' COMMENT '请求方式',
                                 `operator_type` int NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
                                 `oper_name` varchar(50) NULL DEFAULT '' COMMENT '操作人员',
                                 `oper_url` varchar(255)  NULL DEFAULT '' COMMENT '请求URL',
                                 `oper_ip` varchar(128)  NULL DEFAULT '' COMMENT '主机地址',
                                 `oper_location` varchar(255)  NULL DEFAULT '' COMMENT '操作地点',
                                 `oper_param` varchar(2000)  NULL DEFAULT '' COMMENT '请求参数',
                                 `json_result` varchar(2000)  NULL DEFAULT '' COMMENT '返回参数',
                                 `status` int NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
                                 `error_msg` varchar(2000)  NULL DEFAULT '' COMMENT '错误消息',
                                 `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
                                 PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT = '操作日志记录';
