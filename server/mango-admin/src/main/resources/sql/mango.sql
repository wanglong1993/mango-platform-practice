/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.30 : Database - mango
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mango` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `mango`;

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统配置表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`id`,`value`,`label`,`type`,`description`,`sort`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`remarks`,`del_flag`) values (1,'#14889A','theme','color','主题色','0','admin','2018-09-23 19:52:54',NULL,NULL,'主题色',0);

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dept_tree` varchar(255) DEFAULT NULL COMMENT '机构树',
  `name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级机构ID，一级机构为0',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='机构管理';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`id`,`dept_tree`,`name`,`parent_id`,`order_num`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values (1,'1','轻尘集团',NULL,0,'admin','2018-09-23 19:35:22',NULL,NULL,0),(2,NULL,'牧尘集团',NULL,1,'admin','2018-09-23 19:35:55',NULL,NULL,0),(3,NULL,'三国集团',NULL,2,'admin','2018-09-23 19:36:24',NULL,NULL,0),(4,'2,4','上海分公司',2,0,'admin','2018-09-23 19:37:03',NULL,NULL,0),(5,'1,5','北京分公司',1,1,'admin','2018-09-23 19:37:17',NULL,NULL,0),(6,'2,6','北京分公司',2,1,'admin','2018-09-23 19:37:28',NULL,NULL,0),(7,'1,5,7','技术部',5,0,'admin','2018-09-23 19:38:00',NULL,NULL,0),(8,'2,4,8','技术部',4,0,'admin','2018-09-23 19:38:10',NULL,NULL,0),(9,'2,6,9','技术部',6,0,'admin','2018-09-23 19:38:17',NULL,NULL,0),(10,'1,5,10','市场部',5,0,'admin','2018-09-23 19:38:45',NULL,NULL,0),(11,NULL,'市场部',6,0,'admin','2018-09-23 19:39:01',NULL,NULL,0),(12,NULL,'魏国',3,0,'admin','2018-09-23 19:40:42',NULL,NULL,0),(13,NULL,'蜀国',3,1,'admin','2018-09-23 19:40:54',NULL,NULL,0),(14,NULL,'吴国',3,2,'admin','2018-09-23 19:41:04',NULL,NULL,0);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`value`,`label`,`type`,`description`,`sort`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`remarks`,`del_flag`) values (1,'male','男','sex','性别','0','admin','2018-09-23 19:52:54',NULL,NULL,'性别',0),(2,'female','女','sex','性别','1','admin','2018-09-23 19:53:17',NULL,NULL,'性别',0),(3,'femalemale','不男不女','sex','性别','1','admin','2018-09-23 19:53:17',NULL,NULL,'性别',0);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统操作日志';

/*Data for the table `sys_log` */

/*Table structure for table `sys_login_log` */

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `status` varchar(50) DEFAULT NULL COMMENT '登录状态（online:在线，登录初始状态，方便统计在线人数；login:退出登录后将online置为login；logout:退出登录）',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统登录日志';

/*Data for the table `sys_login_log` */

insert  into `sys_login_log`(`id`,`user_name`,`status`,`ip`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`remarks`) values (1,'admin','online','0:0:0:0:0:0:0:1','admin','2018-09-23 19:54:16',NULL,NULL,NULL),(2,'heshenghao','online','0:0:0:0:0:0:0:1',NULL,'2020-07-26 07:04:02',NULL,NULL,NULL);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`parent_id`,`url`,`perms`,`type`,`icon`,`order_num`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values (1,'系统管理',0,NULL,NULL,0,'el-icon-setting',0,NULL,NULL,NULL,NULL,0),(2,'用户管理',1,'/sys/user',NULL,1,'el-icon-service',1,NULL,NULL,NULL,NULL,0),(3,'查看',2,NULL,'sys:user:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(4,'新增',2,NULL,'sys:user:add',2,NULL,0,NULL,NULL,NULL,NULL,0),(5,'修改',2,NULL,'sys:user:edit',2,NULL,0,NULL,NULL,NULL,NULL,0),(6,'删除',2,NULL,'sys:user:delete',2,NULL,0,NULL,NULL,NULL,NULL,0),(7,'机构管理',1,'/sys/dept',NULL,1,'el-icon-news',2,NULL,NULL,NULL,NULL,0),(8,'查看',7,NULL,'sys:dept:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(9,'新增',7,NULL,'sys:dept:add',2,NULL,0,NULL,NULL,NULL,NULL,0),(10,'修改',7,NULL,'sys:dept:edit',2,NULL,0,NULL,NULL,NULL,NULL,0),(11,'删除',7,NULL,'sys:dept:delete',2,NULL,0,NULL,NULL,NULL,NULL,0),(12,'角色管理',1,'/sys/role',NULL,1,'el-icon-view',4,NULL,NULL,NULL,NULL,0),(13,'查看',12,NULL,'sys:role:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(14,'新增',12,NULL,'sys:role:add',2,NULL,0,NULL,NULL,NULL,NULL,0),(15,'修改',12,NULL,'sys:role:edit',2,NULL,0,NULL,NULL,NULL,NULL,0),(16,'删除',12,NULL,'sys:role:delete',2,NULL,0,NULL,NULL,NULL,NULL,0),(17,'菜单管理',1,'/sys/menu',NULL,1,'el-icon-menu',5,NULL,NULL,NULL,NULL,0),(18,'查看',17,NULL,'sys:menu:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(19,'新增',17,NULL,'sys:menu:add',2,NULL,0,NULL,NULL,NULL,NULL,0),(20,'修改',17,NULL,'sys:menu:edit',2,NULL,0,NULL,NULL,NULL,NULL,0),(21,'删除',17,NULL,'sys:menu:delete',2,NULL,0,NULL,NULL,NULL,NULL,0),(22,'字典管理',1,'/sys/dict',NULL,1,'el-icon-edit-outline',7,NULL,NULL,NULL,NULL,0),(23,'查看',22,NULL,'sys:dict:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(24,'新增',22,NULL,'sys:dict:add',2,NULL,0,NULL,NULL,NULL,NULL,0),(25,'修改',22,NULL,'sys:dict:edit',2,NULL,0,NULL,NULL,NULL,NULL,0),(26,'删除',22,NULL,'sys:dict:delete',2,NULL,0,NULL,NULL,NULL,NULL,0),(27,'系统配置',1,'/sys/config',NULL,1,'el-icon-edit-outline',7,NULL,NULL,NULL,NULL,0),(28,'查看',27,NULL,'sys:config:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(29,'新增',27,NULL,'sys:config:add',2,NULL,0,NULL,NULL,NULL,NULL,0),(30,'修改',27,NULL,'sys:config:edit',2,NULL,0,NULL,NULL,NULL,NULL,0),(31,'删除',27,NULL,'sys:config:delete',2,NULL,0,NULL,NULL,NULL,NULL,0),(32,'登录日志',1,'/sys/loginlog',NULL,1,'el-icon-info',8,NULL,NULL,'admin','2018-09-23 19:32:28',0),(33,'查看',32,NULL,'sys:loginlog:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(34,'删除',32,NULL,'sys:loginlog:delete',2,NULL,0,NULL,NULL,NULL,NULL,0),(35,'操作日志',1,'/sys/log',NULL,1,'el-icon-info',8,NULL,NULL,'admin','2018-09-23 19:32:28',0),(36,'查看',35,NULL,'sys:log:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(37,'删除',35,NULL,'sys:log:delete',2,NULL,0,NULL,NULL,NULL,NULL,0),(38,'系统监控',0,'','',0,'el-icon-info',4,'admin','2018-12-27 10:57:29','admin','2019-01-10 17:31:04',0),(39,'数据监控',38,'http://127.0.0.1:8001/druid/login.html',NULL,1,'el-icon-warning',0,NULL,NULL,'admin','2018-12-27 11:03:45',0),(40,'查看',39,NULL,'sys:druid:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(41,'服务监控',38,'http://127.0.0.1:8000/',NULL,1,'el-icon-view',1,'admin','2018-11-02 20:02:15','admin','2018-12-27 11:03:53',0),(42,'查看',41,NULL,'sys:monitor:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(43,'服务治理',0,'','',0,'el-icon-service',2,'admin','2018-12-27 11:05:48','admin','2018-12-27 11:06:39',0),(44,'注册中心',43,'http://127.0.0.1:8500',NULL,1,' el-icon-view',0,'admin','2018-11-03 11:06:48','admin','2018-12-27 11:08:11',0),(45,'查看',44,NULL,'sys:consul:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(47,'查看',46,NULL,'sys:swagger:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(48,'研发工具',0,'/generator/generator','',1,'el-icon-star-on',5,'admin','2018-11-15 14:39:30','admin','2018-11-15 14:56:18',0),(49,'查看',48,NULL,'sys:generator:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(50,'在线用户',0,'/sys/online','',1,'el-icon-view',5,'admin','2018-11-15 14:39:30','admin','2018-11-15 14:56:18',0),(51,'查看',50,NULL,'sys:online:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(52,'使用案例',0,NULL,NULL,0,'el-icon-picture-outline',6,NULL,NULL,'admin','2018-11-15 14:39:43',0),(53,'国际化',52,'/demo/i18n',NULL,1,'el-icon-edit',1,NULL,NULL,NULL,NULL,0),(54,'查看',53,NULL,'sys:dict:view',2,NULL,0,NULL,NULL,NULL,NULL,0),(55,'换皮肤',52,'/demo/theme',NULL,1,'el-icon-picture',2,NULL,NULL,NULL,NULL,0),(56,'代码生成',48,'/sys/genTable',NULL,1,NULL,5,NULL,NULL,NULL,NULL,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色管理';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`remark`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`) values (1,'admin','超级管理员','admin','2019-01-19 11:11:11','admin','2019-01-19 19:07:18',0),(2,'mng','项目经理','admin','2019-01-19 11:11:11','admin','2019-01-19 11:39:28',0),(3,'dev','开发人员','admin','2019-01-19 11:11:11','admin','2019-01-19 11:39:28',0),(4,'test','测试人员','admin','2019-01-19 11:11:11','admin','2019-01-19 11:11:11',0);

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色机构';

/*Data for the table `sys_role_dept` */

insert  into `sys_role_dept`(`id`,`role_id`,`dept_id`,`create_by`,`create_time`,`last_update_by`,`last_update_time`) values (1,1,1,'admin','2019-01-11 08:30:37','admin','2019-01-11 08:30:25'),(2,2,2,'admin','2019-01-11 08:31:01','admin','2019-01-11 08:31:04'),(3,3,3,'admin','2019-01-11 08:31:18','admin','2019-01-11 08:31:21'),(4,2,5,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1238 DEFAULT CHARSET=utf8 COMMENT='角色菜单';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`,`create_by`,`create_time`,`last_update_by`,`last_update_time`) values (431,8,1,'admin','2018-09-23 19:55:08',NULL,NULL),(432,8,2,'admin','2018-09-23 19:55:08',NULL,NULL),(433,8,9,'admin','2018-09-23 19:55:08',NULL,NULL),(434,8,3,'admin','2018-09-23 19:55:08',NULL,NULL),(435,8,13,'admin','2018-09-23 19:55:08',NULL,NULL),(436,8,4,'admin','2018-09-23 19:55:08',NULL,NULL),(437,8,17,'admin','2018-09-23 19:55:08',NULL,NULL),(438,8,5,'admin','2018-09-23 19:55:08',NULL,NULL),(439,8,21,'admin','2018-09-23 19:55:08',NULL,NULL),(440,8,7,'admin','2018-09-23 19:55:08',NULL,NULL),(441,8,31,'admin','2018-09-23 19:55:08',NULL,NULL),(442,8,8,'admin','2018-09-23 19:55:08',NULL,NULL),(443,8,6,'admin','2018-09-23 19:55:08',NULL,NULL),(444,8,35,'admin','2018-09-23 19:55:08',NULL,NULL),(608,4,1,'admin','2019-01-22 14:46:44',NULL,NULL),(609,4,2,'admin','2019-01-22 14:46:44',NULL,NULL),(610,4,3,'admin','2019-01-22 14:46:44',NULL,NULL),(611,4,7,'admin','2019-01-22 14:46:44',NULL,NULL),(612,4,8,'admin','2019-01-22 14:46:44',NULL,NULL),(613,4,17,'admin','2019-01-22 14:46:44',NULL,NULL),(614,4,18,'admin','2019-01-22 14:46:44',NULL,NULL),(615,4,32,'admin','2019-01-22 14:46:44',NULL,NULL),(616,4,33,'admin','2019-01-22 14:46:44',NULL,NULL),(617,4,35,'admin','2019-01-22 14:46:44',NULL,NULL),(618,4,36,'admin','2019-01-22 14:46:44',NULL,NULL),(619,4,46,'admin','2019-01-22 14:46:44',NULL,NULL),(620,4,47,'admin','2019-01-22 14:46:44',NULL,NULL),(621,4,50,'admin','2019-01-22 14:46:44',NULL,NULL),(622,4,51,'admin','2019-01-22 14:46:44',NULL,NULL),(842,3,2,NULL,NULL,NULL,NULL),(843,3,3,NULL,NULL,NULL,NULL),(844,3,4,NULL,NULL,NULL,NULL),(845,3,5,NULL,NULL,NULL,NULL),(846,3,6,NULL,NULL,NULL,NULL),(847,3,7,NULL,NULL,NULL,NULL),(848,3,8,NULL,NULL,NULL,NULL),(849,3,9,NULL,NULL,NULL,NULL),(850,3,10,NULL,NULL,NULL,NULL),(851,3,11,NULL,NULL,NULL,NULL),(852,3,12,NULL,NULL,NULL,NULL),(853,3,13,NULL,NULL,NULL,NULL),(854,3,14,NULL,NULL,NULL,NULL),(855,3,15,NULL,NULL,NULL,NULL),(856,3,16,NULL,NULL,NULL,NULL),(857,3,43,NULL,NULL,NULL,NULL),(858,3,44,NULL,NULL,NULL,NULL),(859,3,45,NULL,NULL,NULL,NULL),(1232,2,1,NULL,NULL,NULL,NULL),(1233,2,2,NULL,NULL,NULL,NULL),(1234,2,3,NULL,NULL,NULL,NULL),(1235,2,4,NULL,NULL,NULL,NULL),(1236,2,7,NULL,NULL,NULL,NULL),(1237,2,8,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `nick_name` varchar(150) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(150) DEFAULT NULL COMMENT '头像',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(40) DEFAULT NULL COMMENT '加密盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '机构ID',
  `dept_tree` varchar(255) DEFAULT NULL COMMENT '机构树',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `order_num` int(11) DEFAULT '80' COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='用户管理';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`name`,`nick_name`,`avatar`,`password`,`salt`,`email`,`mobile`,`status`,`dept_id`,`dept_tree`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`del_flag`,`order_num`) values (1,'admin','超管',NULL,'bd1718f058d8a02468134432b8656a86','YzcmCZNvbXocrsz9dm8e','admin@qq.com','13612345678',1,4,'2,4','admin','2018-08-14 11:11:11','admin','2018-08-14 11:11:11',0,80),(22,'liubei','刘备',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,10,'1,5,10','admin','2018-09-23 07:43:00','admin','2019-01-10 11:41:13',0,80),(23,'zhaoyun','赵云',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,7,'1,5,7','admin','2018-09-23 19:43:44','admin','2018-09-23 19:43:52',0,80),(24,'zhugeliang','诸葛亮',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',7,11,NULL,'admin','2018-09-23 19:44:23','admin','2018-09-23 19:44:29',0,80),(25,'caocao','曹操',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,8,NULL,'admin','2018-09-23 19:45:32','admin','2019-01-10 17:59:14',0,80),(26,'dianwei','典韦',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,10,'1,5,10','admin','2018-09-23 07:45:48','admin','2018-09-23 07:45:57',0,80),(27,'xiahoudun','夏侯惇',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,8,NULL,'admin','2018-09-23 19:46:09','admin','2018-09-23 19:46:17',0,80),(28,'xunyu','荀彧',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,10,NULL,'admin','2018-09-23 19:46:38','admin','2018-11-04 15:33:17',0,80),(29,'sunquan','孙权',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,10,NULL,'admin','2018-09-23 19:46:54','admin','2018-09-23 19:47:03',0,80),(30,'zhouyu','周瑜',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,11,NULL,'admin','2018-09-23 19:47:28','admin','2018-09-23 19:48:04',0,80),(31,'luxun','陆逊',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,11,NULL,'admin','2018-09-23 19:47:44','admin','2018-09-23 19:47:58',0,80),(32,'huanggai','黄盖',NULL,'fd80ebd493a655608dc893a9f897d845','YzcmCZNvbXocrsz9dm8e','test@qq.com','13889700023',1,11,NULL,'admin','2018-09-23 19:48:38','admin','2018-09-23 19:49:02',0,80),(33,'zhaoyun2',NULL,NULL,'e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,80),(35,'heshenghao','heshenghao',NULL,'4b62ffc14c802354c331134435139195','1192d96e629c49249a67','','',1,1,NULL,NULL,'2020-07-23 08:30:16',NULL,'2020-07-23 08:30:16',0,80);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`user_id`,`role_id`,`create_by`,`create_time`,`last_update_by`,`last_update_time`,`remarks`) values (1,1,1,NULL,NULL,NULL,NULL,NULL),(26,5,3,NULL,NULL,NULL,NULL,NULL),(33,6,2,NULL,NULL,NULL,NULL,NULL),(34,4,2,NULL,NULL,NULL,NULL,NULL),(35,9,2,NULL,NULL,NULL,NULL,NULL),(36,10,3,NULL,NULL,NULL,NULL,NULL),(37,11,2,NULL,NULL,NULL,NULL,NULL),(38,12,3,NULL,NULL,NULL,NULL,NULL),(39,15,2,NULL,NULL,NULL,NULL,NULL),(41,16,3,NULL,NULL,NULL,NULL,NULL),(42,8,2,NULL,NULL,NULL,NULL,NULL),(43,7,4,NULL,NULL,NULL,NULL,NULL),(45,18,2,NULL,NULL,NULL,NULL,NULL),(46,17,3,NULL,NULL,NULL,NULL,NULL),(47,3,4,NULL,NULL,NULL,NULL,NULL),(48,21,2,NULL,NULL,NULL,NULL,NULL),(57,31,2,NULL,NULL,NULL,NULL,NULL),(58,30,2,NULL,NULL,NULL,NULL,NULL),(59,32,3,NULL,NULL,NULL,NULL,NULL),(73,33,8,NULL,NULL,NULL,NULL,NULL),(74,25,8,NULL,NULL,NULL,NULL,NULL),(75,25,2,NULL,NULL,NULL,NULL,NULL),(81,23,3,NULL,NULL,NULL,NULL,NULL),(82,24,4,NULL,NULL,NULL,NULL,NULL),(83,26,3,NULL,NULL,NULL,NULL,NULL),(85,29,2,NULL,NULL,NULL,NULL,NULL),(86,28,4,NULL,NULL,NULL,NULL,NULL),(87,27,3,NULL,NULL,NULL,NULL,NULL),(93,22,2,'admin','2020-07-21 06:15:13','admin','2020-07-21 06:15:13',''),(94,22,3,'admin','2020-07-21 06:15:13','admin','2020-07-21 06:15:13',''),(103,35,2,'admin','2020-07-25 14:22:23','admin','2020-07-25 14:22:23','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
