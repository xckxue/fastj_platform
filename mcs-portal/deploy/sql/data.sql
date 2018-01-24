/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.14-7-log : Database - voole_mcs
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`voole_mcs` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `voole_mcs`;

/*Table structure for table `cfg_database` */

DROP TABLE IF EXISTS `cfg_database`;

CREATE TABLE `cfg_database` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group` varchar(64) NOT NULL COMMENT '组',
  `database` varchar(64) NOT NULL COMMENT '数据库',
  `is_master` tinyint(1) NOT NULL COMMENT '是否主库',
  `jdbc_url` varchar(128) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `readonly` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否只读',
  `conn_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '连接超时时间',
  `idle_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '空闲超时时间',
  `max_lifetime` int(11) NOT NULL DEFAULT '0' COMMENT '最大存活时间',
  `max_pool_size` int(11) NOT NULL DEFAULT '0' COMMENT '最大连接数',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Table structure for table `mcs_code` */

DROP TABLE IF EXISTS `mcs_code`;

CREATE TABLE `mcs_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_type` varchar(64) NOT NULL,
  `code_id` varchar(64) NOT NULL,
  `code_name` varchar(64) NOT NULL,
  `remark` varchar(256) NOT NULL DEFAULT '',
  `priority` int(11) NOT NULL DEFAULT '0',
  `is_enable` tinyint(4) NOT NULL DEFAULT '1',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IX_mcs_code` (`code_type`,`code_id`)
) ENGINE=InnoDB AUTO_INCREMENT=406 DEFAULT CHARSET=utf8;

/*Table structure for table `mcs_log` */

DROP TABLE IF EXISTS `mcs_log`;

CREATE TABLE `mcs_log` (
  `logid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `path` varchar(64) NOT NULL,
  `param` text,
  `ip` varchar(32) NOT NULL,
  `duration` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `remark` text,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`logid`),
  KEY `IX_mcs_log_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=367785 DEFAULT CHARSET=utf8;

/*Table structure for table `mcs_menu` */

DROP TABLE IF EXISTS `mcs_menu`;

CREATE TABLE `mcs_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(64) NOT NULL,
  `path` varchar(64) NOT NULL,
  `remark` varchar(256) NOT NULL,
  `priority` int(11) NOT NULL,
  `is_enable` tinyint(1) NOT NULL DEFAULT '1',
  `is_admin` tinyint(1) NOT NULL DEFAULT '0',
  `icon` varchar(32) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

/*Table structure for table `mcs_menu_priv` */

DROP TABLE IF EXISTS `mcs_menu_priv`;

CREATE TABLE `mcs_menu_priv` (
  `menu_id` int(11) NOT NULL,
  `priv_id` varchar(16) NOT NULL DEFAULT 'view',
  `priv_name` varchar(64) NOT NULL DEFAULT '页面权限',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`menu_id`,`priv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `mcs_permission` */

DROP TABLE IF EXISTS `mcs_permission`;

CREATE TABLE `mcs_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `priv` varchar(16) NOT NULL,
  `is_half` int(1) NOT NULL DEFAULT '0' COMMENT '1、全选  0半选',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4076 DEFAULT CHARSET=utf8;

/*Table structure for table `mcs_role` */

DROP TABLE IF EXISTS `mcs_role`;

CREATE TABLE `mcs_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `remark` varchar(256) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Table structure for table `mcs_user` */

DROP TABLE IF EXISTS `mcs_user`;

CREATE TABLE `mcs_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(128) NOT NULL,
  `name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `phone` varchar(16) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0',
  `is_lock` tinyint(1) NOT NULL DEFAULT '0',
  `remark` varchar(256) NOT NULL DEFAULT '',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `IX_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

/*Table structure for table `mcs_user_role` */

DROP TABLE IF EXISTS `mcs_user_role`;

CREATE TABLE `mcs_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
