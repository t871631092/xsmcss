/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 100414
 Source Host           : localhost:3306
 Source Schema         : sc

 Target Server Type    : MySQL
 Target Server Version : 100414
 File Encoding         : 65001

 Date: 05/06/2021 10:34:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coures
-- ----------------------------
DROP TABLE IF EXISTS `coures`;
CREATE TABLE `coures`  (
  `coures_id` int(25) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `credit` int(25) DEFAULT NULL,
  `period` int(25) DEFAULT NULL,
  `capacity` int(25) NOT NULL,
  `teacher_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`coures_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1017 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of coures
-- ----------------------------
INSERT INTO `coures` VALUES (1001, 'Chinese', 'C', 2, 33, 5, '001', '123123');
INSERT INTO `coures` VALUES (1002, 'math', 'c', 2, 33, 5, '002', '123123');
INSERT INTO `coures` VALUES (1007, '网络编程', 'A', NULL, 24, 50, '003', '');
INSERT INTO `coures` VALUES (1008, '软件开发', 'A', NULL, 32, 100, '112', '');
INSERT INTO `coures` VALUES (1009, '体育', 'B', NULL, 24, 200, 'teacher', '');
INSERT INTO `coures` VALUES (1010, 'Android', 'A', NULL, 36, 45, '001', '');
INSERT INTO `coures` VALUES (1011, '计算机网络', 'B', NULL, 32, 55, '002', '');
INSERT INTO `coures` VALUES (1012, '高等数学', 'B', NULL, 28, 32, '003', '');
INSERT INTO `coures` VALUES (1013, '证券与管理', 'A', NULL, 36, 49, '002', '');
INSERT INTO `coures` VALUES (1014, '保险', 'B', NULL, 23, 60, 'teacher', '');
INSERT INTO `coures` VALUES (1015, '大学英语', 'A', NULL, 32, 500, 'sd', '');
INSERT INTO `coures` VALUES (1016, '123', 'B', NULL, 0, 123, '003', '123');

-- ----------------------------
-- Table structure for elective
-- ----------------------------
DROP TABLE IF EXISTS `elective`;
CREATE TABLE `elective`  (
  `coures_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `score` int(255) DEFAULT NULL,
  `status` int(255) NOT NULL,
  PRIMARY KEY (`coures_id`, `student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of elective
-- ----------------------------
INSERT INTO `elective` VALUES ('1001', '001', '5', 50, 1);
INSERT INTO `elective` VALUES ('1001', '002', '2', 80, 1);
INSERT INTO `elective` VALUES ('1001', '003', '2', 90, 1);
INSERT INTO `elective` VALUES ('1001', '004', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1001', '006', 'admin', 20, 1);
INSERT INTO `elective` VALUES ('1001', '008', '112', 60, 1);
INSERT INTO `elective` VALUES ('1001', '009', '1', 88, 1);
INSERT INTO `elective` VALUES ('1001', '010', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1001', '011', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1001', '111', '1', 70, 1);
INSERT INTO `elective` VALUES ('1001', '1231', NULL, NULL, 0);
INSERT INTO `elective` VALUES ('1002', '001', '1', 100, 1);
INSERT INTO `elective` VALUES ('1002', '002', '1', 100, 1);
INSERT INTO `elective` VALUES ('1002', '003', '99', 70, 1);
INSERT INTO `elective` VALUES ('1002', '004', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1002', '008', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1002', '010', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1002', '011', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1002', '013', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1002', '111', '99', 50, 1);
INSERT INTO `elective` VALUES ('1003', '001', '1', 100, 1);
INSERT INTO `elective` VALUES ('1003', '002', '1', 40, 1);
INSERT INTO `elective` VALUES ('1003', '009', '1', 85, 1);
INSERT INTO `elective` VALUES ('1003', '111', NULL, 100, 1);
INSERT INTO `elective` VALUES ('1007', '004', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1007', '006', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1007', '008', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1007', '011', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1007', '015', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1008', '001', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1008', '008', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1011', '011', NULL, NULL, 1);
INSERT INTO `elective` VALUES ('1012', '011', NULL, NULL, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `student_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `electives_credits` int(5) DEFAULT NULL,
  `student_major` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `student_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('004', '123456', 'kong', 0, 'math', NULL);
INSERT INTO `student` VALUES ('005', '123', '赵四', NULL, '1', NULL);
INSERT INTO `student` VALUES ('006', '123', '安妮', NULL, '1', NULL);
INSERT INTO `student` VALUES ('007', '123', '邓昌', NULL, 'a', NULL);
INSERT INTO `student` VALUES ('008', '123', 'xiaoaaa', NULL, 'g123', NULL);
INSERT INTO `student` VALUES ('009', '123', '小李', NULL, 'f', NULL);
INSERT INTO `student` VALUES ('010', '123', '李嘉', NULL, 's', NULL);
INSERT INTO `student` VALUES ('011', '123', '小明', NULL, 'f', NULL);
INSERT INTO `student` VALUES ('013', '123', 'nihao', NULL, 'h', NULL);
INSERT INTO `student` VALUES ('014', '123', '小邹', NULL, 'j', NULL);
INSERT INTO `student` VALUES ('015', '123', '小熊', NULL, 'k', NULL);
INSERT INTO `student` VALUES ('111', '123', '孔庆勤', 4, '12312312', '123123213');
INSERT INTO `student` VALUES ('1231', '1231', '我是学生啊', NULL, '1', NULL);
INSERT INTO `student` VALUES ('1234', '123', '123', NULL, '1', NULL);
INSERT INTO `student` VALUES ('qqq', '123', '12312', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('001', '123', '小李', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('002', '123', '小张', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('003', '123', '小天', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('111', '123', '小吴', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('112', '123', '校长', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('113', '123', '12', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('114', '123', '小王', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('115', '123', '大王', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('ABC', '123456', '嘤嘤怪', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('sd', 'ad', 'd', NULL, NULL, NULL);
INSERT INTO `teacher` VALUES ('teacher', '123', 'jjjjj', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('admin', '系统管理员', '123', 1);

-- ----------------------------
-- Procedure structure for UPDATE_STUDENT_CREDITS
-- ----------------------------
DROP PROCEDURE IF EXISTS `UPDATE_STUDENT_CREDITS`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `UPDATE_STUDENT_CREDITS`()
BEGIN
CREATE TEMPORARY table if not EXISTS tmp(id BIGINT(20) PRIMARY key, xf VARCHAR(20) NOT NULL);
set @num=(select count(1) from student);
if @num>0 THEN
INSERT into tmp(id,xf) SELECT e.student_id as id,sum(c.credit) as xf FROM elective e
LEFT JOIN coures c on c.coures_id=e.coures_id
where e.score>'60'
AND STATUS='1'
GROUP BY e.student_id;
end if;

UPDATE student,tmp
SET student.electives_credits=tmp.xf
where student.student_id=tmp.id;

drop temporary TABLE SC.tmp;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
