/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : rbac_db

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-08-29 17:39:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(10) NOT NULL,
  `permName` varchar(50) DEFAULT NULL,
  `permTag` varchar(50) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL COMMENT '请求url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '查询订单', 'showOrder', '/showOrder');
INSERT INTO `sys_permission` VALUES ('2', '添加订单', 'addOrder', '/addOrder');
INSERT INTO `sys_permission` VALUES ('3', '修改订单', 'updateOrder', '/updateOrder');
INSERT INTO `sys_permission` VALUES ('4', '删除订单', 'deleteOrder', '/deleteOrder');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL,
  `roleName` varchar(50) DEFAULT NULL,
  `roleDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员');
INSERT INTO `sys_role` VALUES ('2', 'add_user', '添加管理员');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `role_id` int(10) DEFAULT NULL,
  `perm_id` int(10) DEFAULT NULL,
  KEY `FK_Reference_3` (`role_id`),
  KEY `FK_Reference_4` (`perm_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`perm_id`) REFERENCES `sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('2', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `lastLoginTime` date DEFAULT NULL,
  `enabled` int(5) DEFAULT NULL,
  `accountNonExpired` int(5) DEFAULT NULL,
  `accountNonLocked` int(5) DEFAULT NULL,
  `credentialsNonExpired` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '张三', '15a013bcac0c50049356b322e955035e', '2018-11-13', '2018-11-13', '1', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('2', 'userAdd', '小余', '15a013bcac0c50049356b322e955035e', '2018-11-13', '2018-11-13', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(10) DEFAULT NULL,
  `role_id` int(10) DEFAULT NULL,
  KEY `FK_Reference_1` (`user_id`),
  KEY `FK_Reference_2` (`role_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
