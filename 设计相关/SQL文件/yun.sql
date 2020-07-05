/*
 Navicat Premium Data Transfer

 Source Server         : yun
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 47.112.242.4:3306
 Source Schema         : yun

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 03/07/2020 12:43:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `courseId` bigint(32) NOT NULL AUTO_INCREMENT,
  `coursename` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courseinfo` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `classId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `semester` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `schoolid` bigint(32) NOT NULL,
  PRIMARY KEY (`courseId`) USING BTREE,
  INDEX `schoolId2`(`schoolid`) USING BTREE,
  CONSTRAINT `schoolId2` FOREIGN KEY (`schoolid`) REFERENCES `school` (`schoolId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 30007 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (30001, '工程训练', '学习工程训练', '工程训练1班', '2019-2020-1', 1);
INSERT INTO `course` VALUES (30002, '工程实践', '学习工程实践', '工程训练2班', '2019-2020-2', 2);
INSERT INTO `course` VALUES (30003, '数据库', '计算机2班', '数据库1班', '2019-2020-1', 1);
INSERT INTO `course` VALUES (30004, '人工智能', '2', '人工智能1班', '2019-2020-1', 1);
INSERT INTO `course` VALUES (30005, '工程训练', '学习工程训练', '工程训练1班', '2019-2020-1', 1);

-- ----------------------------
-- Table structure for course_user
-- ----------------------------
DROP TABLE IF EXISTS `course_user`;
CREATE TABLE `course_user`  (
  `cuid` bigint(32) NOT NULL AUTO_INCREMENT,
  `totalexp` bigint(32) NOT NULL,
  `courseid` bigint(32) NOT NULL,
  `userid` bigint(32) NOT NULL,
  PRIMARY KEY (`cuid`) USING BTREE,
  INDEX `userId`(`userid`) USING BTREE,
  INDEX `courseId`(`courseid`) USING BTREE,
  CONSTRAINT `courseId` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userId` FOREIGN KEY (`userid`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_user
-- ----------------------------
INSERT INTO `course_user` VALUES (1, 3, 30001, 104);
INSERT INTO `course_user` VALUES (3, 2, 30002, 104);
INSERT INTO `course_user` VALUES (4, 2, 30005, 104);
INSERT INTO `course_user` VALUES (5, 0, 30004, 224);
INSERT INTO `course_user` VALUES (6, 0, 30001, 104);
INSERT INTO `course_user` VALUES (26, 0, 30001, 104);
INSERT INTO `course_user` VALUES (27, 0, 30001, 103);
INSERT INTO `course_user` VALUES (28, 0, 30001, 104);
INSERT INTO `course_user` VALUES (29, 0, 30001, 224);
INSERT INTO `course_user` VALUES (30, 0, 30001, 104);
INSERT INTO `course_user` VALUES (31, 0, 30001, 104);
INSERT INTO `course_user` VALUES (32, 0, 30001, 104);
INSERT INTO `course_user` VALUES (33, 0, 30001, 104);

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `dictionaryId` int(10) NOT NULL AUTO_INCREMENT,
  `dictionaryname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dictionarydescribe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dictionaryvalue` int(255) NULL DEFAULT NULL,
  `dictionaryvaluename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dictionaryId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, 'USER_TYPE', '用户类型', 1, '超级管理员');
INSERT INTO `dictionary` VALUES (2, 'USER_TYPE', '用户类型', 2, '教师');
INSERT INTO `dictionary` VALUES (3, 'USER_TYPE', '用户类型', 3, '学生');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menuId` bigint(11) NOT NULL AUTO_INCREMENT,
  `menuname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menulevel1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`menuId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '用户管理', '用户列表');
INSERT INTO `menu` VALUES (2, '权限管理', '角色列表');
INSERT INTO `menu` VALUES (3, '权限管理', '权限列表');
INSERT INTO `menu` VALUES (4, '院校管理', '院校列表');
INSERT INTO `menu` VALUES (5, '菜单管理', '菜单列表');
INSERT INTO `menu` VALUES (6, '系统管理', '数据字典');
INSERT INTO `menu` VALUES (7, '系统管理', '参数设置');

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power`  (
  `powerId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `powername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `powerlujin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `powerlevel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`powerId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', '权限管理', 'rights', '一级');
INSERT INTO `power` VALUES ('2', '用户列表', 'users', '二级');
INSERT INTO `power` VALUES ('3', '权限列表', 'rights', '二级');
INSERT INTO `power` VALUES ('4', '角色列表', 'roles', '二级');

-- ----------------------------
-- Table structure for putsign
-- ----------------------------
DROP TABLE IF EXISTS `putsign`;
CREATE TABLE `putsign`  (
  `putSignId` bigint(32) NOT NULL AUTO_INCREMENT,
  `longitude` double(32, 20) NULL DEFAULT NULL,
  `latitude` double(32, 20) NULL DEFAULT NULL,
  `gesture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `courseid` bigint(32) NULL DEFAULT NULL,
  PRIMARY KEY (`putSignId`) USING BTREE,
  INDEX `coursrId3`(`courseid`) USING BTREE,
  CONSTRAINT `coursrId3` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of putsign
-- ----------------------------
INSERT INTO `putsign` VALUES (1, 123.65230000000000000000, 63.32145000000000000000, 'n', 30002);
INSERT INTO `putsign` VALUES (4, 23.23000000000000000000, 23.21000000000000000000, 'n', 30005);
INSERT INTO `putsign` VALUES (5, 666.66000000000000000000, 33.33300000000000000000, 'n', 30002);
INSERT INTO `putsign` VALUES (6, 666.66600000000000000000, 333.33300000000000000000, 'm', 30002);
INSERT INTO `putsign` VALUES (15, NULL, NULL, '123', 30003);
INSERT INTO `putsign` VALUES (16, NULL, NULL, '', 30004);
INSERT INTO `putsign` VALUES (17, NULL, NULL, '789', 30002);
INSERT INTO `putsign` VALUES (18, NULL, NULL, '789', 30004);
INSERT INTO `putsign` VALUES (40, 119.01500000000000000000, 25.43130000000000000000, '11', 30001);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `roledetail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rolename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'superadmin', '超级管理员');
INSERT INTO `role` VALUES (2, 'teacher', '教师');
INSERT INTO `role` VALUES (4, 'student', '学生');

-- ----------------------------
-- Table structure for school
-- ----------------------------
DROP TABLE IF EXISTS `school`;
CREATE TABLE `school`  (
  `schoolId` bigint(32) NOT NULL AUTO_INCREMENT,
  `schoolname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `xueyuanid` bigint(32) NOT NULL,
  `zhuanyeid` bigint(32) NOT NULL,
  PRIMARY KEY (`schoolId`) USING BTREE,
  INDEX `xueyuanId`(`xueyuanid`) USING BTREE,
  INDEX `zhuanyeId`(`zhuanyeid`) USING BTREE,
  CONSTRAINT `xueyuanId` FOREIGN KEY (`xueyuanid`) REFERENCES `xueyuan` (`xueyuanId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `zhuanyeId` FOREIGN KEY (`zhuanyeid`) REFERENCES `zhuanye` (`zhuanyeId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school
-- ----------------------------
INSERT INTO `school` VALUES (1, '福州大学', 1, 1);
INSERT INTO `school` VALUES (2, '福建工程学院', 2, 3);
INSERT INTO `school` VALUES (3, '福建师范大学', 3, 2);
INSERT INTO `school` VALUES (4, '北京大学', 2, 2);
INSERT INTO `school` VALUES (5, '厦门大学', 2, 1);
INSERT INTO `school` VALUES (6, '复旦大学', 1, 3);
INSERT INTO `school` VALUES (7, '北京交通大学', 1, 2);
INSERT INTO `school` VALUES (10, '浙江大学', 2, 1);

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign`  (
  `signId` bigint(32) NOT NULL AUTO_INCREMENT,
  `exp` bigint(10) NULL DEFAULT NULL,
  `signtime` datetime(6) NULL DEFAULT NULL,
  `issigned` tinyint(1) NULL DEFAULT NULL,
  `courseid` bigint(32) NULL DEFAULT NULL,
  `userid` bigint(32) NULL DEFAULT NULL,
  PRIMARY KEY (`signId`) USING BTREE,
  INDEX `userId2`(`userid`) USING BTREE,
  INDEX `courseId2`(`courseid`) USING BTREE,
  CONSTRAINT `courseId2` FOREIGN KEY (`courseid`) REFERENCES `course` (`courseId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userId2` FOREIGN KEY (`userid`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES (1, 2, '2020-06-29 16:57:16.000000', 1, 30004, 224);
INSERT INTO `sign` VALUES (3, 2, '2020-07-01 16:10:26.000000', 0, 30002, 104);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telephone` bigint(255) NOT NULL,
  `roleid` bigint(10) NULL DEFAULT NULL,
  `schoolid` bigint(32) NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  INDEX `roleId`(`roleid`) USING BTREE,
  INDEX `schoolID`(`schoolid`) USING BTREE,
  CONSTRAINT `roleId` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `schoolID` FOREIGN KEY (`schoolid`) REFERENCES `school` (`schoolId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 560 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (103, 'du', 'du', '杜总统', 'du@163.com', 13858697983, 2, 4);
INSERT INTO `user` VALUES (104, 'lin', 'lin', '杜总', 'lin@163.com', 13859693384, 1, 5);
INSERT INTO `user` VALUES (224, 'aWang', '123456', '陈圆圆', 'awang@163.com', 15280100366, 4, 1);

-- ----------------------------
-- Table structure for xueyuan
-- ----------------------------
DROP TABLE IF EXISTS `xueyuan`;
CREATE TABLE `xueyuan`  (
  `xueyuanId` bigint(32) NOT NULL AUTO_INCREMENT,
  `xueyuanname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`xueyuanId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xueyuan
-- ----------------------------
INSERT INTO `xueyuan` VALUES (1, '数计学院');
INSERT INTO `xueyuan` VALUES (2, '环资学院');
INSERT INTO `xueyuan` VALUES (3, '生工学院');

-- ----------------------------
-- Table structure for zhuanye
-- ----------------------------
DROP TABLE IF EXISTS `zhuanye`;
CREATE TABLE `zhuanye`  (
  `zhuanyeId` bigint(32) NOT NULL AUTO_INCREMENT,
  `zhuanyename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`zhuanyeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zhuanye
-- ----------------------------
INSERT INTO `zhuanye` VALUES (1, '计算机技术');
INSERT INTO `zhuanye` VALUES (2, '土木工程');
INSERT INTO `zhuanye` VALUES (3, '生物工程');

SET FOREIGN_KEY_CHECKS = 1;
