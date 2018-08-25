/*
Navicat MySQL Data Transfer

Source Server         : 10.1.16.50
Source Server Version : 50627
Source Host           : 10.1.16.50:3306
Source Database       : appmanagera

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-08-24 16:11:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `app_prefix`
-- ----------------------------
DROP TABLE IF EXISTS `app_prefix`;
CREATE TABLE `app_prefix` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `alias` varchar(255) NOT NULL,
  `prefix` varchar(255) NOT NULL,
  `internet_prefix` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_prefix
-- ----------------------------
INSERT INTO `app_prefix` VALUES ('1', '0', 'default', 'http://10.1.16.50:8080/', 'http://202.204.194.18:8080/');
INSERT INTO `app_prefix` VALUES ('2', '0', '81', 'http://10.1.16.50:8081/', 'http://202.204.194.18:8081/');

-- ----------------------------
-- Table structure for `app_roles`
-- ----------------------------
DROP TABLE IF EXISTS `app_roles`;
CREATE TABLE `app_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `app_right` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_roles
-- ----------------------------
INSERT INTO `app_roles` VALUES ('1', '0', '0', '一般程序');
INSERT INTO `app_roles` VALUES ('2', '0', '1', '用户程序');
INSERT INTO `app_roles` VALUES ('3', '0', '1', '煤层气');
INSERT INTO `app_roles` VALUES ('4', '0', '1', 'VMS');
INSERT INTO `app_roles` VALUES ('5', '0', '1', '辅助教学');
INSERT INTO `app_roles` VALUES ('8', '0', '0', 'Test');
INSERT INTO `app_roles` VALUES ('9', '0', '1', '储运工程');
INSERT INTO `app_roles` VALUES ('10', '0', '2', '实验室管理');
INSERT INTO `app_roles` VALUES ('11', '0', '0', '输气管道计算');

-- ----------------------------
-- Table structure for `swing_app`
-- ----------------------------
DROP TABLE IF EXISTS `swing_app`;
CREATE TABLE `swing_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `developer` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `target_file_name` varchar(255) DEFAULT NULL,
  `upload_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of swing_app
-- ----------------------------
INSERT INTO `swing_app` VALUES ('1', '1', '李晓平', '张2李3', '/home/usr/share/apache-tomcat-8.0.37/webapps/SwingAppManager/swingApps/李晓平/1/GrailsAuxToolA2.jar', '2017-03-06 00:00:00');
INSERT INTO `swing_app` VALUES ('2', '1', '李晓平', '测试性根节点', '/home/usr/share/apache-tomcat-8.0.37/webapps/SwingAppManager/swingApps/李晓平/2/GrailsAuxToolA1.jar', '2017-03-06 00:00:00');
INSERT INTO `swing_app` VALUES ('3', '1', '软件开发小组', 'MPFCore', '/home/usr/share/apache-tomcat-8.0.37/webapps/SwingAppManager/swingApps/软件开发小组/3/MPFcore.jar', '2017-03-20 00:00:00');
INSERT INTO `swing_app` VALUES ('4', '1', '软件开发小组', 'MPFcoreJavadoc', '/home/usr/share/apache-tomcat-8.0.37/webapps/SwingAppManager/swingApps/软件开发小组/4/MPFjavadoc.zip', '2017-03-20 00:00:00');
INSERT INTO `swing_app` VALUES ('5', '1', 'CUPB多相流软件开发小组', 'VMS2017（内测版）', '/home/usr/share/apache-tomcat-8.0.37/webapps/SwingAppManager/swingApps/CUPB多相流软件开发小组/5/VMS2017.exe', '2017-04-29 00:00:00');
INSERT INTO `swing_app` VALUES ('6', '1', '李晓平', 'Grails程序拷贝工具', '/home/usr/share/apache-tomcat-8.0.37/webapps/SwingAppManager/swingApps/李晓平/6/GrailsProjectAuxA_jar.rar', '2017-05-01 00:00:00');
INSERT INTO `swing_app` VALUES ('7', '1', '李晓平', '葫芦瓢工具A', '/home/usr/share/apache-tomcat-8.0.37/webapps/SwingAppManager/swingApps/李晓平/7/GrailsProjectAuxA.zip', '2017-05-02 00:00:00');
INSERT INTO `swing_app` VALUES ('8', '1', '李晓平', '代码辅助工具A', '/home/usr/share/apache-tomcat-8.0.37/webapps/SwingAppManager/swingApps/李晓平/8/GrailsAuxToolA_jar.rar', '2017-05-02 00:00:00');

-- ----------------------------
-- Table structure for `tomcat_instance`
-- ----------------------------
DROP TABLE IF EXISTS `tomcat_instance`;
CREATE TABLE `tomcat_instance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `app_prefix` varchar(255) NOT NULL,
  `tomcat_path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_46jqs3nsxnhuuy9lnr9p0wh34` (`tomcat_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tomcat_instance
-- ----------------------------

-- ----------------------------
-- Table structure for `user_app`
-- ----------------------------
DROP TABLE IF EXISTS `user_app`;
CREATE TABLE `user_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `app_name` varchar(255) NOT NULL,
  `app_path` varchar(255) NOT NULL,
  `app_prefix_id` bigint(20) NOT NULL,
  `date` datetime NOT NULL,
  `app_roles_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b80wnu40l0htvkivhw9twr6qt` (`app_prefix_id`),
  KEY `FK_g57nklxlhf4bjt8nl8u9q9kpi` (`app_roles_id`),
  CONSTRAINT `FK_b80wnu40l0htvkivhw9twr6qt` FOREIGN KEY (`app_prefix_id`) REFERENCES `app_prefix` (`id`),
  CONSTRAINT `FK_g57nklxlhf4bjt8nl8u9q9kpi` FOREIGN KEY (`app_roles_id`) REFERENCES `app_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_app
-- ----------------------------
INSERT INTO `user_app` VALUES ('1', '2', '应用管理', 'AppManagerB', '1', '2015-10-12 00:00:00', '1');
INSERT INTO `user_app` VALUES ('2', '2', '压缩机特性曲线测试程序--只是例子', 'CompressorB', '1', '2015-10-12 18:31:19', '1');
INSERT INTO `user_app` VALUES ('5', '1', '洪炳沅', 'yourgrails', '1', '2015-10-13 14:25:07', '1');
INSERT INTO `user_app` VALUES ('7', '1', '闵元', 'mygrails', '1', '2015-10-13 16:31:23', '1');
INSERT INTO `user_app` VALUES ('8', '2', '伍星光', 'myGrails2-0.1', '1', '2015-10-13 00:00:00', '1');
INSERT INTO `user_app` VALUES ('9', '1', '皮礼仕', 'pls789-0.1', '1', '2015-10-16 09:38:37', '1');
INSERT INTO `user_app` VALUES ('10', '2', '吴恒威', 'GrailsApplication12-0.1', '1', '2015-10-18 17:04:38', '1');
INSERT INTO `user_app` VALUES ('11', '1', '金浩', 'jinhao', '1', '2015-10-18 17:04:38', '1');
INSERT INTO `user_app` VALUES ('12', '1', '测试日志文件', 'TestStacktrace00-0.1', '1', '2015-10-19 00:00:00', '1');
INSERT INTO `user_app` VALUES ('13', '1', '吴恒威2', 'GrailsApplication14-0.1', '1', '2015-10-19 18:46:00', '1');
INSERT INTO `user_app` VALUES ('15', '1', '赵文佳', 'GrailsApplication8-0.1', '1', '2015-10-22 11:24:12', '1');
INSERT INTO `user_app` VALUES ('16', '1', '首页程序', 'AppManagerA', '1', '2015-10-24 00:00:00', '1');
INSERT INTO `user_app` VALUES ('18', '1', '煤层气测试', 'CbmDynamicSimulationA', '1', '2015-10-26 00:00:00', '2');
INSERT INTO `user_app` VALUES ('19', '2', '案例-racetrack-beta', 'racetrack1-0.1', '1', '2015-10-27 00:00:00', '1');
INSERT INTO `user_app` VALUES ('20', '1', '开放式专业试题库', 'Cai2015B', '1', '2015-10-27 00:00:00', '2');
INSERT INTO `user_app` VALUES ('25', '1', '天然气物性1', 'GasA1', '1', '2015-11-14 00:00:00', '2');
INSERT INTO `user_app` VALUES ('26', '0', '未命名', 'compressorWyyA', '1', '2015-11-23 10:30:59', '1');
INSERT INTO `user_app` VALUES ('27', '0', '未命名', 'compressorWyyA-0.1', '1', '2015-11-24 09:09:00', '1');
INSERT INTO `user_app` VALUES ('28', '2', '煤层气39-2', 'OilManage', '1', '2015-12-17 00:00:00', '3');
INSERT INTO `user_app` VALUES ('30', '2', '废弃了', 'AppManagerC', '1', '2016-01-18 00:00:00', '2');
INSERT INTO `user_app` VALUES ('31', '2', '煤层气II', 'OilDynamic', '1', '2016-01-18 00:00:00', '3');
INSERT INTO `user_app` VALUES ('32', '2', '39-4-6', 'Cbm390406B', '1', '2016-01-21 00:00:00', '3');
INSERT INTO `user_app` VALUES ('33', '1', 'VMSDemoA', 'Vms2016a', '1', '2016-01-31 00:00:00', '4');
INSERT INTO `user_app` VALUES ('34', '1', '压缩机特性曲线-王玉叶', 'PipelineSimulationA', '1', '2016-02-13 00:00:00', '2');
INSERT INTO `user_app` VALUES ('35', '2', 'CAI计算机辅助教学', 'cai2016A', '1', '2016-03-11 00:00:00', '5');
INSERT INTO `user_app` VALUES ('37', '1', 'VMS-online-2.1', 'VMS-online-2.1', '1', '2016-06-15 00:00:00', '4');
INSERT INTO `user_app` VALUES ('38', '2', '实时显示测试', 'TestRealTimeD', '1', '2016-07-29 00:00:00', '4');
INSERT INTO `user_app` VALUES ('39', '1', 'CnoocTest', 'CnoocTest-0.1', '1', '2016-07-31 00:00:00', '8');
INSERT INTO `user_app` VALUES ('40', '1', '海油软件测试版A', 'CnoocTest', '1', '2016-07-31 00:00:00', '8');
INSERT INTO `user_app` VALUES ('41', '1', '润尼尔', 'rainier', '1', '2016-08-02 00:00:00', '5');
INSERT INTO `user_app` VALUES ('42', '1', '管道模拟', 'pipelinesimulationC', '1', '2016-08-04 00:00:00', '2');
INSERT INTO `user_app` VALUES ('43', '1', '武浩', 'calculate', '1', '2016-08-04 00:00:00', '8');
INSERT INTO `user_app` VALUES ('44', '2', '武浩遗传算法输入', 'calculate-0.1', '1', '2016-08-04 00:00:00', '1');
INSERT INTO `user_app` VALUES ('45', '0', '未命名', 'opt', '1', '2016-08-05 17:17:45', '1');
INSERT INTO `user_app` VALUES ('46', '0', '未命名', 'opt-0.1', '1', '2016-08-05 17:27:08', '1');
INSERT INTO `user_app` VALUES ('47', '1', '武浩的程序（改进版）', 'optpicandbutton', '1', '2016-08-05 00:00:00', '1');
INSERT INTO `user_app` VALUES ('48', '1', '管道模拟A', 'PipelineSimulation255A', '1', '2016-08-18 00:00:00', '9');
INSERT INTO `user_app` VALUES ('49', '1', '请删除', 'mpfga', '1', '2016-08-31 00:00:00', '1');
INSERT INTO `user_app` VALUES ('50', '1', '请删除', 'mpfg', '1', '2016-08-31 00:00:00', '1');
INSERT INTO `user_app` VALUES ('51', '1', '多相流管网测试', 'mpfgb', '1', '2016-08-31 00:00:00', '8');
INSERT INTO `user_app` VALUES ('52', '1', '软件概论', 'SoftwarePracticeA', '1', '2016-09-08 00:00:00', '5');
INSERT INTO `user_app` VALUES ('53', '2', '请删除', 'mpfn0.1', '1', '2016-09-17 00:00:00', '1');
INSERT INTO `user_app` VALUES ('54', '1', '请删除', 'mpfn2', '1', '2016-09-17 00:00:00', '1');
INSERT INTO `user_app` VALUES ('55', '1', '管道模拟B', 'PipelineSimulation255B', '1', '2016-09-17 00:00:00', '9');
INSERT INTO `user_app` VALUES ('56', '1', '加入PVT', 'mpfn', '1', '2016-09-17 00:00:00', '8');
INSERT INTO `user_app` VALUES ('59', '1', '实验室管理V1.1', 'LimsLA', '1', '2016-09-19 00:00:00', '10');
INSERT INTO `user_app` VALUES ('60', '2', '第一程序管理', 'AppManager255A', '1', '2016-09-23 00:00:00', '10');
INSERT INTO `user_app` VALUES ('61', '4', '输气管道优化计算软件', 'cqds2016a', '1', '2016-10-13 00:00:00', '11');
INSERT INTO `user_app` VALUES ('62', '1', '性能测试A', 'PTestAA', '1', '2016-10-22 00:00:00', '8');
INSERT INTO `user_app` VALUES ('63', '0', '未命名', 'paperTest', '1', '2016-11-23 10:32:34', '1');
INSERT INTO `user_app` VALUES ('64', '1', '川气东送应急系统', 'SeedApp6D', '1', '2016-12-20 00:00:00', '2');
INSERT INTO `user_app` VALUES ('65', '1', '种子程序测试', 'SeedApp324A', '1', '2016-12-28 00:00:00', '8');
INSERT INTO `user_app` VALUES ('66', '1', 'Grails3.x测试-种子程序', 'SeedApp324B', '1', '2017-01-05 00:00:00', '8');
INSERT INTO `user_app` VALUES ('67', '1', '种子程序测试', 'SeedApp2017A324', '1', '2017-01-09 00:00:00', '8');
INSERT INTO `user_app` VALUES ('68', '1', 'paperTest2', 'paperTest2', '1', '2017-01-20 00:00:00', '1');
INSERT INTO `user_app` VALUES ('69', '1', '种子-李晓平分支', 'SeedApp2017A326', '1', '2017-02-20 00:00:00', '3');
INSERT INTO `user_app` VALUES ('70', '1', '种子程序-王茀玺分支', 'SeedApp2017A326-wfx', '1', '2017-02-20 00:00:00', '3');
INSERT INTO `user_app` VALUES ('71', '2', '种子程序-李愚分支', 'SeedApp2017A326-lv-0.1', '1', '2017-02-20 00:00:00', '3');
INSERT INTO `user_app` VALUES ('72', '1', '桌面程序管理', 'SwingAppManager', '1', '2017-03-05 00:00:00', '4');
INSERT INTO `user_app` VALUES ('73', '1', 'VMS主页', 'grails326-0.1', '1', '2017-03-05 00:00:00', '4');
INSERT INTO `user_app` VALUES ('74', '1', 'PVTdatabase', 'PVTDataBase-0.1', '1', '2017-03-08 00:00:00', '1');
INSERT INTO `user_app` VALUES ('75', '1', '第二程序管理', 'AppManager255A', '2', '2017-03-13 00:00:00', '10');
INSERT INTO `user_app` VALUES ('76', '1', 'VMS主页', 'HomeMPF', '2', '2017-03-14 00:00:00', '4');
INSERT INTO `user_app` VALUES ('77', '0', '未命名', 'grails327-0.1', '2', '2017-03-14 15:40:12', '1');
INSERT INTO `user_app` VALUES ('78', '1', '种子程序327', 'SeedApp2017A327', '2', '2017-03-22 00:00:00', '8');
INSERT INTO `user_app` VALUES ('79', '2', '西部管网优化软件测试', 'westPipe', '2', '2017-03-30 00:00:00', '11');
INSERT INTO `user_app` VALUES ('80', '2', '多气合采', 'EasyPipe2017A', '2', '2017-05-22 00:00:00', '3');
INSERT INTO `user_app` VALUES ('81', '1', '多气合采B', 'EasyPipe2017B', '2', '2017-06-18 00:00:00', '3');
INSERT INTO `user_app` VALUES ('82', '1', '哈利波特', 'satbuilder', '2', '2017-06-26 00:00:00', '8');
INSERT INTO `user_app` VALUES ('83', '0', '未命名', 'wb', '1', '2017-07-18 09:40:35', '1');
INSERT INTO `user_app` VALUES ('84', '0', '未命名', 'wb72', '2', '2017-07-19 09:24:58', '1');
INSERT INTO `user_app` VALUES ('86', '2', '华南管道-万洋洋', 'EasyPipe2017B-HuaNan-Wyy', '2', '2017-07-20 00:00:00', '9');
INSERT INTO `user_app` VALUES ('87', '1', '华南管道-韦宝成', 'EasyPipe2017B-HuaNan-WBC', '2', '2017-07-20 00:00:00', '9');
INSERT INTO `user_app` VALUES ('88', '1', '华南管网-V0.1-请测试', 'EasyPipe2017B-HuaNan-V1.0', '2', '2017-07-24 00:00:00', '9');
INSERT INTO `user_app` VALUES ('89', '1', '华南项目 V1.1 请测试', 'EasyPipe2017B-HuaNan-V1.1', '2', '2017-07-26 00:00:00', '9');
INSERT INTO `user_app` VALUES ('90', '0', '未命名', 'EasyPipe2017B-HuaNan-V1.2', '2', '2017-08-04 15:12:02', '1');
INSERT INTO `user_app` VALUES ('91', '2', '华南管网-V1.3-请测试', 'EasyPipe2017B-HuaNan-V1.3', '2', '2017-08-11 00:00:00', '9');
INSERT INTO `user_app` VALUES ('92', '1', '华南管道-万洋洋', 'EasyPipe2017B-HuaNanWyy', '2', '2018-03-06 00:00:00', '9');
INSERT INTO `user_app` VALUES ('93', '0', '未命名', 'xdoc', '2', '2018-03-24 14:15:19', '1');
INSERT INTO `user_app` VALUES ('94', '2', '华南管道-韦宝成', 'EasyPipe2017B-HuaNanWbc', '2', '2018-03-24 00:00:00', '9');
INSERT INTO `user_app` VALUES ('95', '1', '华南管道（合并版）', 'EasyPipe2017B-HuaNan', '2', '2018-05-14 00:00:00', '9');
INSERT INTO `user_app` VALUES ('96', '0', '未命名', 'wb', '2', '2018-06-22 16:28:32', '1');
INSERT INTO `user_app` VALUES ('97', '1', 'SCADA', 'EasyPipe2017B-SCADA', '2', '2018-07-24 00:00:00', '10');
