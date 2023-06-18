/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_JuLiy
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : student_mis

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 22/06/2022 21:47:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `account` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` enum('student','teacher','manager') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('123', '123', 'student');
INSERT INTO `account` VALUES ('202083290549', '1', 'student');
INSERT INTO `account` VALUES ('202083290550', '202083290550', 'student');
INSERT INTO `account` VALUES ('202083290552', '202083290552', 'student');
INSERT INTO `account` VALUES ('321', '321', 'student');
INSERT INTO `account` VALUES ('66660002', '66660002', 'teacher');
INSERT INTO `account` VALUES ('66660003', '66660003', 'teacher');
INSERT INTO `account` VALUES ('66660101', '66660101', 'teacher');
INSERT INTO `account` VALUES ('66660201', '66660201', 'teacher');
INSERT INTO `account` VALUES ('m', 'm', 'manager');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `number` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程编号',
  `teacher_number` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '任课教师',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名',
  `period` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学时',
  `credit` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学分',
  `type` enum('必修','选修','通识') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程性质',
  `profession` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专业(特有专业、面向全校)',
  `college` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '开课院系',
  PRIMARY KEY (`number`, `teacher_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('0003', '66660002', '数据库系统原理', '32', '4', '必修', '计算机类', '计算机与软件学院');
INSERT INTO `course` VALUES ('0004', '66660002', '统一建模语言UML', '32', '1', '选修', '软件工程', '计算机与软件学院');
INSERT INTO `course` VALUES ('0101', '66660101', '高等数学1', '64', '6', '必修', '面向全校', '数学与统计学院');
INSERT INTO `course` VALUES ('0102', '66660101', '高等数学2', '64', '6', '必修', '面向全校', '数学与统计学院');
INSERT INTO `course` VALUES ('0201', '66660201', '人类学入门', '48', '1', '通识', '面向全校', '人文与艺术学院');
INSERT INTO `course` VALUES ('0202', '66660201', '艺术鉴赏', '32', '1', '通识', '面向全校', '人文与艺术学院');
INSERT INTO `course` VALUES ('0203', '66660201', '诗词鉴赏', '48', '2', '通识', '面向全校', '人文与艺术学院');
INSERT INTO `course` VALUES ('0204', '66660201', '漫画鉴赏', '48', '3', '必修', '面向全校', '人文与艺术学院');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `student_number` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生编号',
  `course_number` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程编号',
  `school_year` char(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学年',
  `term` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学期',
  `total_score` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '总评成绩',
  `retest_score` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '补考成绩',
  `relearn_score` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '重修成绩',
  PRIMARY KEY (`student_number`, `course_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('123', '0201', '2021-2022', '2', NULL, NULL, NULL);
INSERT INTO `score` VALUES ('123', '0202', '2021-2022', '2', NULL, NULL, NULL);
INSERT INTO `score` VALUES ('123', '0203', '2021-2022', '2', NULL, NULL, NULL);
INSERT INTO `score` VALUES ('123', '9999', '2021-2022', '2', NULL, NULL, NULL);
INSERT INTO `score` VALUES ('202083290549', '0001', '2021-2022', '1', '88', NULL, NULL);
INSERT INTO `score` VALUES ('202083290549', '0002', '2021-2022', '2', '23', NULL, NULL);
INSERT INTO `score` VALUES ('202083290549', '0003', '2021-2022', '2', NULL, NULL, NULL);
INSERT INTO `score` VALUES ('202083290549', '0101', '2020-2021', '1', '100', NULL, NULL);
INSERT INTO `score` VALUES ('202083290549', '0102', '2020-2021', '2', '58', '59', '60');
INSERT INTO `score` VALUES ('202083290549', '0201', '2020-2021', '1', '61', NULL, NULL);
INSERT INTO `score` VALUES ('202083290549', '0202', '2020-2021', '2', '32', '60', NULL);
INSERT INTO `score` VALUES ('202083290549', '0203', '2021-2022', '2', '88', NULL, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `number` char(12) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL COMMENT '学号，固定12位，如202083290001',
  `name` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `age` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年龄',
  `sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `nationality` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '汉族' COMMENT '民族',
  `political_status` enum('中共党员','中共预备党员','共青团员','民革党员','民盟盟员','民建会员','民进会员','农工党党员','致公党党员','九三学社社员','台盟盟员','无党派人士','普通居民') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '普通居民' COMMENT '政治面貌',
  `hometown` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '籍贯',
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '现居住地址',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `college` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院',
  `pre_major` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分流前专业',
  `major` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专业',
  `class` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级，格式：20级(1)班',
  `start_time` date NOT NULL COMMENT '入学时间',
  `select_course` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否可以选课',
  `is_sports` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否体育特长生',
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('123', '123', '2', '2', '1', '共青团员', '1', '1', '', '1', '12', '213', '123', '123', '2000-01-01', '是', '是');
INSERT INTO `student` VALUES ('202083290549', '鞠立阳', '20', '男', '汉族', '共青团员', '江苏省盐城市', '12345612341212123x', '3213232132132321321233', '13182839825', '计算机与软件学院', '计算机类', '软件工程', '20级(1)班', '2020-09-12', '是', '否');
INSERT INTO `student` VALUES ('202083290550', '张三', '19', '男', '回族', '中共预备党员', '江苏省南京市', '321111123412121234', '123', '112233', '大气科学学院', '大气科学', '大气科学', '20大气1班', '2020-01-01', '是', '是');
INSERT INTO `student` VALUES ('202083290552', '李四', '20', '男', '汉族', '无党派人士', '四川省重庆市', '123456432121214321', '南京信息工程大学', '', '商学院', '国际贸易', '国际贸易', '20国贸1班', '2020-02-12', '是', '是');
INSERT INTO `student` VALUES ('321', '123', '2', '2', '1', '共青团员', '1', '1', '', '1', '12', '213', '123', '123', '2000-01-01', '是', '是');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `number` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` char(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nationality` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `political_status` enum('中共党员','中共预备党员','共青团员','民革党员','民盟盟员','民建会员','民进会员','农工党党员','致公党党员','九三学社社员','台盟盟员','无党派人士','普通居民') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '普通居民',
  `hometown` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `college` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `profession` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `job` enum('教授','副教授','讲师','助教') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('66660002', '赵荣', '41', '男', '苗族', '中共党员', '江苏省盐城市', '4444333322', '江苏省南京市浦口区光明小区6栋213', '32143214321', '计算机与软件学院', '软件工程', '教授');
INSERT INTO `teacher` VALUES ('66660003', '李明', '34', '男', '汉族', '中共党员', '江苏省南京市', '320981123443211234', '', '11122223333', '计算机与软件学院', '计算机科学与技术', '助教');
INSERT INTO `teacher` VALUES ('66660101', '高洁', '29', '女', '汉族', '无党派人士', '北京市', '1111111111111111', 'xx省xx市xx县xxxx', '11111111111', '数学与统计学院', '数学', '讲师');
INSERT INTO `teacher` VALUES ('66660201', '李松', '32', '男', '维吾尔族', '民进会员', '新疆省xx市', '1234432112344321', '江苏省南京市', '', '人文与艺术学院', '艺术', '副教授');

SET FOREIGN_KEY_CHECKS = 1;
