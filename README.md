# reservation

## Description: 人保预约系统，包括体检预约系统，会议室预约系统，后台管理系统

### 1. 表结构

1. 会议室预约系统

   1. yj_conference_room_info ：会议室信息表

      ```sql
      CREATE TABLE `yj_conference_room_info` (
        `room_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
        `name` varchar(8) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '会议室名称',
        `description` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '会议室描述',
        `path` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '会议室地址',
        `province` varchar(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '所在地:省',
        `city` varchar(8) CHARACTER SET utf8 DEFAULT NULL COMMENT '所在地:市',
        `company_id` int(11) DEFAULT NULL COMMENT '公司id',
        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间戳',
        `status` int(1) DEFAULT '0' COMMENT '状态码0：正常 1：已删除 2：已关闭',
        PRIMARY KEY (`room_id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4
      ```

   2. yj_conference_room_order：预约信息表

      ```sql
      CREATE TABLE `yj_conference_room_order` (
        `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
        `order_date` date DEFAULT NULL COMMENT '预约日期',
        `times_id` int(11) DEFAULT NULL COMMENT 'yj_conference_room_times外键',
        `room_id` int(11) DEFAULT NULL COMMENT 'yj_conference_room_info外键',
        `user_id` int(11) DEFAULT NULL COMMENT 'yj_user_info外键',
        `user_name` varchar(11) CHARACTER SET utf8 DEFAULT '' COMMENT '预约人的名字',
        `theme` varchar(32) DEFAULT '' COMMENT '会议主题',
        `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
        `status` int(1) DEFAULT '0' COMMENT '0:未完成预约 1：完成预约/已失效预约 2：取消的预约  3:已关闭的时段',
        PRIMARY KEY (`order_id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8mb4
      ```

   3. yj_conference_room_times：时间段表

      ```sql
      CREATE TABLE `yj_conference_room_times` (
        `times_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
        `start_time` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '开始时间',
        `end_time` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '结束时间',
        `timestamp` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
        PRIMARY KEY (`times_id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4
      ```

   4. yj_user_info：用户信息表

      ```sql
      CREATE TABLE `yj_user_info` (
        `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
        `name` varchar(11) DEFAULT NULL COMMENT '员工姓名',
        `phone` bigint(11) DEFAULT NULL COMMENT '员工电话',
        `user_type` int(1) NOT NULL DEFAULT '0' COMMENT '员工类型 0:普通员工 1: 业务员 2:管理员(暂定)',
        `employee_code` varchar(32) DEFAULT NULL COMMENT '员工证件号',
        `open_id` varchar(32) DEFAULT NULL COMMENT 'wx',
        `union_id` varchar(32) DEFAULT NULL COMMENT 'wx',
        `department` varchar(16) DEFAULT NULL COMMENT '员工部门',
        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `status` int(1) NOT NULL DEFAULT '0' COMMENT '0：在职 1：离职',
        PRIMARY KEY (`user_id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8
      ```

2. 后台管理系统

   1. yj_manager_info：管理员信息表

      ```sql
      CREATE TABLE `yj_manager_info` (
        `manager_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
        `user_type` int(1) NOT NULL DEFAULT '0' COMMENT '用户类型0：高级管理员 1：健康助手 2：会议室管家',
        `account` varchar(16) NOT NULL COMMENT '账户',
        `password` varchar(64) NOT NULL COMMENT '编译后的密码',
        `salt` varchar(64) NOT NULL COMMENT '盐',
        `sex` int(1) DEFAULT NULL COMMENT '性别0：女 1：男',
        `picture` varchar(64) DEFAULT NULL COMMENT '头像path',
        `name` varchar(8) DEFAULT NULL COMMENT '姓名',
        `phone` varchar(16) DEFAULT NULL COMMENT '电话',
        `department` varchar(16) DEFAULT NULL COMMENT '部门',
        `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
        `status` int(1) DEFAULT '0' COMMENT '状态0：正常 1： 删除',
        PRIMARY KEY (`manager_id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4
      ```

   2. yj_role_info：角色信息表

      ```sql
      CREATE TABLE `yj_role_info` (
        `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
        `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `status` int(1) DEFAULT '0',
        PRIMARY KEY (`role_id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4
      ```

   3. yj_authority_info：权限信息表

      ```sql
      CREATE TABLE `yj_authority_info` (
        `authority_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限表id',
        `parent_id` int(11) DEFAULT '0' COMMENT '父权限id，为0则为根权限',
        `name` varchar(16) CHARACTER SET utf8 DEFAULT NULL COMMENT '权限名称',
        `url` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '对应url',
        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `status` int(1) DEFAULT '0',
        PRIMARY KEY (`authority_id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4
      ```

   4. yj_relation_manager_role：管理员账户与角色关联表

      ```sql
      CREATE TABLE `yj_relation_manager_role` (
        `rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员账户与角色关联表',
        `manager_id` int(11) DEFAULT NULL COMMENT '管理员id',
        `role_id` int(11) DEFAULT NULL COMMENT '角色id',
        PRIMARY KEY (`rel_id`),
        KEY `manager_id` (`manager_id`),
        KEY `role_id` (`role_id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4
      ```

   5. yj_relation_role_authority：角色与权限关联表

      ```sql
      CREATE TABLE `yj_relation_role_authority` (
        `rel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色与权限关联表',
        `role_id` int(11) DEFAULT NULL COMMENT '角色id',
        `authority_id` int(11) DEFAULT NULL COMMENT '权限id',
        PRIMARY KEY (`rel_id`),
        KEY `role_id` (`role_id`)
      ) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb
      ```

      
