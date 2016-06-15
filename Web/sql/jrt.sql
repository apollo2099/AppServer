/*
SQLyog v10.2 
MySQL - 5.6.21-log : Database - jrt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`jrt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `jrt`;

/*Table structure for table `lv_app` */

DROP TABLE IF EXISTS `lv_app`;

CREATE TABLE `lv_app` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `app_name` varchar(32) DEFAULT NULL COMMENT '应用名称',
  `app_version` int(32) DEFAULT NULL COMMENT '应用版本',
  `app_url` varchar(128) DEFAULT NULL COMMENT '应用地址',
  `remark` varchar(256) DEFAULT NULL COMMENT '应用备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `lv_bank` */

DROP TABLE IF EXISTS `lv_bank`;

CREATE TABLE `lv_bank` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='业务银行表';

/*Table structure for table `lv_bank_branch` */

DROP TABLE IF EXISTS `lv_bank_branch`;

CREATE TABLE `lv_bank_branch` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `bank_code` int(16) DEFAULT NULL COMMENT '银行code',
  `branch_name` varchar(128) DEFAULT NULL COMMENT '网点名称',
  `order_value` int(11) DEFAULT NULL COMMENT '排序值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='业务银行网点表';

/*Table structure for table `lv_bank_branch_details` */

DROP TABLE IF EXISTS `lv_bank_branch_details`;

CREATE TABLE `lv_bank_branch_details` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `branch_title` varchar(64) DEFAULT NULL COMMENT '网点标题',
  `branch_desc` varchar(256) DEFAULT NULL COMMENT '网点详情',
  `branch_code` int(16) DEFAULT NULL COMMENT '网点code',
  `branch_address` varchar(256) DEFAULT NULL COMMENT '网点地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  `postcode` varchar(10) DEFAULT NULL COMMENT '邮编',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='网点简介';

/*Table structure for table `lv_banner` */

DROP TABLE IF EXISTS `lv_banner`;

CREATE TABLE `lv_banner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `banner_name` varchar(64) DEFAULT NULL COMMENT 'banner名称',
  `banner_path` varchar(128) DEFAULT NULL COMMENT 'banner广告图片路径',
  `banner_desc` varchar(256) DEFAULT NULL COMMENT 'banner广告备注',
  `banner_type` smallint(3) DEFAULT NULL COMMENT '类型（1web,2广告详情）',
  `is_show` smallint(3) DEFAULT NULL COMMENT '是否显示(1是，0否)',
  `web_url` varchar(128) DEFAULT NULL COMMENT 'weburl',
  `order_value` smallint(6) DEFAULT NULL COMMENT '排序值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='广告信息表';

/*Table structure for table `lv_branch_banner` */

DROP TABLE IF EXISTS `lv_branch_banner`;

CREATE TABLE `lv_branch_banner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `banner_name` varchar(64) DEFAULT NULL COMMENT 'banner名称',
  `banner_path` varchar(128) DEFAULT NULL COMMENT 'banner广告图片路径',
  `banner_desc` varchar(256) DEFAULT NULL COMMENT 'banner广告备注',
  `banner_type` smallint(3) DEFAULT NULL COMMENT '类型（1web,2广告详情）',
  `is_show` smallint(3) DEFAULT NULL COMMENT '是否显示(1是，0否)',
  `web_url` varchar(128) DEFAULT NULL COMMENT 'weburl',
  `order_value` smallint(6) DEFAULT NULL COMMENT '排序值',
  `branch_code` int(16) DEFAULT NULL COMMENT '网点编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='网点广告';

/*Table structure for table `lv_business` */

DROP TABLE IF EXISTS `lv_business`;

CREATE TABLE `lv_business` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `business_name` varchar(32) DEFAULT NULL COMMENT '业务名称',
  `order_value` int(6) DEFAULT NULL COMMENT '排序值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='业务列表';

/*Table structure for table `lv_business_bank` */

DROP TABLE IF EXISTS `lv_business_bank`;

CREATE TABLE `lv_business_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `sub_business_code` int(16) DEFAULT NULL COMMENT '子业务编码',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `lv_business_details` */

DROP TABLE IF EXISTS `lv_business_details`;

CREATE TABLE `lv_business_details` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `sub_name` varchar(32) DEFAULT NULL COMMENT '子业务名称',
  `sub_images` varchar(128) DEFAULT NULL COMMENT '子业务图片地址',
  `sub_type` int(3) DEFAULT '2' COMMENT '子业务类型（类型：1，需要回答问题。2：不需要回答）',
  `business_code` int(16) DEFAULT NULL COMMENT '父业务code',
  `order_value` int(6) DEFAULT NULL COMMENT '排序值',
  `sub_desc` varchar(256) DEFAULT NULL COMMENT '业务简介',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  `is_visible` smallint(3) DEFAULT '0' COMMENT '主页是否显示（0：不显示；1：显示）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='业务列表详情';

/*Table structure for table `lv_business_user` */

DROP TABLE IF EXISTS `lv_business_user`;

CREATE TABLE `lv_business_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `sub_business_code` int(16) DEFAULT NULL COMMENT '子业务编码',
  `user_code` varchar(32) DEFAULT NULL COMMENT '业务经理编码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Table structure for table `lv_file_define` */

DROP TABLE IF EXISTS `lv_file_define`;

CREATE TABLE `lv_file_define` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DATA_BO` varchar(80) NOT NULL COMMENT '正式BO',
  `DATA_ROW_NUM` varchar(80) NOT NULL COMMENT '开始数据行',
  `FILE_PATH` varchar(80) NOT NULL COMMENT '表格路径',
  `HEAD_BO` varchar(80) NOT NULL COMMENT '表头BO',
  `FILE_BO` varchar(80) NOT NULL COMMENT '临时表BO',
  `CALCULATE_BO` varchar(80) NOT NULL COMMENT '结果BO',
  `TEMPLATE_CUID` varchar(80) NOT NULL COMMENT '导入主键',
  `SHEET_NUM` int(11) NOT NULL COMMENT '导入sheet页',
  `TITLE_ROW_NUM` int(11) NOT NULL COMMENT '表头行',
  `TEMP_TABLE` varchar(80) NOT NULL COMMENT '临时表',
  `ERROR_BO` varchar(80) NOT NULL COMMENT '错误数据BO',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `lv_interface` */

DROP TABLE IF EXISTS `lv_interface`;

CREATE TABLE `lv_interface` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号（自动增长）',
  `inf_name` varchar(64) DEFAULT NULL COMMENT '接口名称',
  `inf_path` varchar(128) DEFAULT NULL COMMENT '接口请求地址',
  `inf_param` text COMMENT '接口请求参数',
  `inf_result` text COMMENT '接口请求结果',
  `inf_remark` varchar(256) DEFAULT NULL COMMENT '接口备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_name` varchar(32) DEFAULT NULL COMMENT '修改人名称',
  `code` varchar(32) DEFAULT NULL COMMENT '内部编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='接口展示表';

/*Table structure for table `lv_message` */

DROP TABLE IF EXISTS `lv_message`;

CREATE TABLE `lv_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `app_id` varchar(32) DEFAULT NULL COMMENT '聊天内容',
  `from_user_id` int(11) DEFAULT NULL COMMENT '聊天时间',
  `target_id` int(11) DEFAULT NULL COMMENT '发送者code',
  `target_type` int(3) DEFAULT NULL COMMENT '接收者code',
  `group_id` int(11) DEFAULT NULL COMMENT 'group_id',
  `classname` varchar(32) DEFAULT NULL COMMENT '内部编码',
  `content` text COMMENT '聊天内容',
  `date_time` datetime DEFAULT NULL COMMENT '聊天时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='聊天消息记录';

/*Table structure for table `lv_message_auth_code` */

DROP TABLE IF EXISTS `lv_message_auth_code`;

CREATE TABLE `lv_message_auth_code` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机',
  `auth_code` varchar(16) DEFAULT NULL COMMENT '验证码',
  `create_time` datetime DEFAULT NULL COMMENT '生效时间',
  `status` smallint(3) DEFAULT NULL COMMENT '状态(1启用，0失效，-1删除)',
  `code` varchar(32) DEFAULT NULL COMMENT '内部编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8;

/*Table structure for table `lv_question` */

DROP TABLE IF EXISTS `lv_question`;

CREATE TABLE `lv_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `title` varchar(64) DEFAULT NULL COMMENT '问题名称',
  `description` text COMMENT '问题描述',
  `business_code` int(16) DEFAULT NULL COMMENT '业务编号',
  `is_answer` smallint(3) DEFAULT NULL COMMENT '是否必答（1是，0否）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `code` int(16) DEFAULT NULL COMMENT '内部编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='问题列表';

/*Table structure for table `lv_task_quartz` */

DROP TABLE IF EXISTS `lv_task_quartz`;

CREATE TABLE `lv_task_quartz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(64) NOT NULL COMMENT '任务名称',
  `target_object` varchar(64) NOT NULL COMMENT '目标实例',
  `target_method` varchar(64) NOT NULL COMMENT '目标方法',
  `target_date` varchar(64) NOT NULL COMMENT '定时目标时间',
  `description` varchar(225) DEFAULT NULL COMMENT '描述',
  `status` int(4) NOT NULL DEFAULT '1' COMMENT '0=>暂停任务,1=>启用任务',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_id` int(11) DEFAULT NULL COMMENT '修改人id',
  `modify_user_name` varchar(64) DEFAULT NULL COMMENT '修改人名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Table structure for table `lv_user` */

DROP TABLE IF EXISTS `lv_user`;

CREATE TABLE `lv_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account` varchar(32) DEFAULT NULL COMMENT '登录帐号',
  `password` varchar(32) DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `head_image` varchar(128) DEFAULT NULL COMMENT '头像地址',
  `user_type` smallint(3) DEFAULT NULL COMMENT '用户类型(1业务经理，2个人用户)',
  `sex` smallint(3) DEFAULT NULL COMMENT '性别',
  `recommend` varchar(128) DEFAULT NULL COMMENT '主要推荐',
  `introduction` varchar(256) DEFAULT NULL COMMENT '个人简介',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `tel` varchar(32) DEFAULT NULL COMMENT '固定电话',
  `is_attention` smallint(3) DEFAULT '0' COMMENT '是否被关注(1是，0否)',
  `is_answer` smallint(3) DEFAULT '0' COMMENT '是否已经回答问题(1是，0否)',
  `is_allow` smallint(3) DEFAULT '1' COMMENT '是否允许聊天(1是，0否)',
  `user_card_path` varchar(128) DEFAULT NULL COMMENT '用户名片地址',
  `last_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `code` varchar(32) DEFAULT NULL COMMENT '用户code',
  `longitude` double(11,3) DEFAULT NULL COMMENT '经度',
  `latitude` double(11,3) DEFAULT NULL COMMENT '纬度',
  `status` smallint(3) DEFAULT NULL COMMENT '用户在线状态：(1在线，2隐身，0离线,-1删除)',
  `branch_code` int(16) DEFAULT NULL COMMENT '所属支行',
  `user_id` int(16) DEFAULT NULL COMMENT '用户编码',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_name` varchar(32) DEFAULT NULL COMMENT '修改人',
  `office` varchar(64) DEFAULT NULL COMMENT '个人职位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Table structure for table `lv_user_attention` */

DROP TABLE IF EXISTS `lv_user_attention`;

CREATE TABLE `lv_user_attention` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_code` int(16) DEFAULT NULL COMMENT '用户code',
  `attention_code` int(16) DEFAULT NULL COMMENT '被关注用户code',
  `create_time` datetime DEFAULT NULL COMMENT '关注时间',
  `code` int(16) DEFAULT NULL COMMENT 'code',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='用户关注关联表';

/*Table structure for table `lv_user_question` */

DROP TABLE IF EXISTS `lv_user_question`;

CREATE TABLE `lv_user_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '序号',
  `user_code` int(16) DEFAULT NULL COMMENT '用户code',
  `question_code` int(16) DEFAULT NULL COMMENT '问题code',
  `create_time` datetime DEFAULT NULL COMMENT '关注时间',
  `code` int(16) DEFAULT NULL COMMENT '内部编码',
  `is_answer` smallint(3) NOT NULL COMMENT '是否答案（1是，0否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='用户问题关联表';

/*Table structure for table `ma_function` */

DROP TABLE IF EXISTS `ma_function`;

CREATE TABLE `ma_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `function_code` varchar(90) DEFAULT NULL COMMENT '菜单编码',
  `function_name` varchar(150) DEFAULT NULL COMMENT '菜单名称',
  `function_desc` varchar(600) DEFAULT NULL COMMENT '菜单描述',
  `parent_id` int(11) DEFAULT NULL COMMENT '菜单父级ID',
  `function_page` varchar(300) DEFAULT NULL COMMENT '菜单URL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Table structure for table `ma_user` */

DROP TABLE IF EXISTS `ma_user`;

CREATE TABLE `ma_user` (
  `id` int(11) NOT NULL COMMENT 'id',
  `user_code` varchar(32) NOT NULL COMMENT '登录用户名',
  `user_password` varchar(32) NOT NULL COMMENT '登录用户密码',
  `user_name` varchar(32) NOT NULL COMMENT '真实名子',
  `user_sex` char(2) NOT NULL COMMENT '性别',
  `email` varchar(64) DEFAULT NULL COMMENT '邮件',
  `address` varchar(256) DEFAULT NULL COMMENT '联系地址',
  `telephone` varchar(16) NOT NULL COMMENT '联系电话',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `temp_bank` */

DROP TABLE IF EXISTS `temp_bank`;

CREATE TABLE `temp_bank` (
  `NUM` int(10) NOT NULL,
  `BANK_NAME` varchar(200) NOT NULL,
  `MSG` varchar(300) DEFAULT NULL,
  `BATCH_NO` varchar(100) NOT NULL,
  `RELATED_TEMPLATE_CUID` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `temp_bank_branch` */

DROP TABLE IF EXISTS `temp_bank_branch`;

CREATE TABLE `temp_bank_branch` (
  `NUM` int(11) DEFAULT NULL COMMENT '数字',
  `BATCH_NO` varchar(80) DEFAULT NULL COMMENT '批次号',
  `RELATED_TEMPLATE_CUID` varchar(80) DEFAULT NULL COMMENT '导入CUID',
  `MSG` varchar(200) DEFAULT NULL COMMENT '错误消息',
  `BANK_NAME` varchar(80) DEFAULT NULL COMMENT '银行名称',
  `BRANCH_NAME` varchar(200) DEFAULT NULL COMMENT '网点名称',
  `ADDRESS` varchar(200) DEFAULT NULL COMMENT '地址',
  `POSTCODE` varchar(20) DEFAULT NULL COMMENT '邮编',
  `TELEPHONE` varchar(40) DEFAULT NULL COMMENT '联系电话'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `temp_batch` */

DROP TABLE IF EXISTS `temp_batch`;

CREATE TABLE `temp_batch` (
  `NUM` int(11) DEFAULT NULL COMMENT '编号',
  `BANK_NAME` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `BRANCH_NAME` varchar(32) DEFAULT NULL COMMENT '网点名称',
  `ADDRESS` varchar(128) DEFAULT NULL COMMENT '地址',
  `POSTCODE` varchar(32) DEFAULT NULL COMMENT '邮编',
  `TELEPHONE` varchar(32) DEFAULT NULL COMMENT '联系电话',
  `ACCOUNT` varchar(32) DEFAULT NULL COMMENT '业务经理帐号',
  `NICKNAME` varchar(32) DEFAULT NULL COMMENT '昵称',
  `MOBILE` varbinary(32) DEFAULT NULL COMMENT '手机',
  `RECOMMEND` varchar(128) DEFAULT NULL COMMENT '主推业务',
  `MSG` varchar(300) DEFAULT NULL COMMENT '错误消息',
  `BATCH_NO` varchar(64) NOT NULL COMMENT '批次号',
  `RELATED_TEMPLATE_CUID` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `temp_business` */

DROP TABLE IF EXISTS `temp_business`;

CREATE TABLE `temp_business` (
  `NUM` int(11) DEFAULT NULL,
  `BUSINESS_NAME` varchar(32) DEFAULT NULL COMMENT '业务名称',
  `SUB_NAME` varchar(32) DEFAULT NULL COMMENT '子业务名称',
  `SUB_IMAGES` varchar(128) DEFAULT NULL COMMENT '子业务图片',
  `SUB_DESC` varchar(256) DEFAULT NULL COMMENT '子业务描述',
  `MSG` varchar(300) DEFAULT NULL,
  `BATCH_NO` varchar(100) NOT NULL,
  `RELATED_TEMPLATE_CUID` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `temp_user` */

DROP TABLE IF EXISTS `temp_user`;

CREATE TABLE `temp_user` (
  `NUM` int(11) DEFAULT NULL,
  `ACCOUNT` varchar(32) DEFAULT NULL,
  `NICKNAME` varchar(32) DEFAULT NULL,
  `MOBILE` varchar(32) DEFAULT NULL,
  `RECOMMEND` varchar(128) DEFAULT NULL,
  `BRANCH_CODE` varchar(32) DEFAULT NULL,
  `MSG` varchar(300) DEFAULT NULL,
  `BATCH_NO` varchar(100) NOT NULL,
  `RELATED_TEMPLATE_CUID` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `lv_loan`;

CREATE TABLE `lv_loan` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `business_code` int(11) DEFAULT NULL COMMENT '业务编码',
  `loan_name` varchar(64) DEFAULT NULL COMMENT '贷款名称',
  `month_rate` varchar(11) DEFAULT NULL COMMENT '月利率',
  `on_the_rate` varchar(11) DEFAULT NULL COMMENT '月费率',
  `money` int(11) DEFAULT NULL COMMENT '金额',
  `deadline` int(11) DEFAULT NULL COMMENT '期限',
  `conditions` varchar(200) DEFAULT NULL COMMENT '申请条件',
  `material` varchar(200) DEFAULT NULL COMMENT '所需材料',
  `loan_icon` varchar(128) DEFAULT NULL COMMENT '图标',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `lv_loan` */

/*Table structure for table `lv_money_product` */

DROP TABLE IF EXISTS `lv_money_product`;

CREATE TABLE `lv_money_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `business_code` int(11) DEFAULT NULL COMMENT '业务编码',
  `product_name` varchar(128) DEFAULT NULL COMMENT '产品名称',
  `product_code` varchar(128) NOT NULL COMMENT '产品编码',
  `earnings_type` varchar(128) DEFAULT NULL COMMENT '收益类型',
  `subscribe` varchar(200) DEFAULT NULL COMMENT '申购赎回时间',
  `origin_money` varchar(200) DEFAULT NULL COMMENT '首次起点金额',
  `subscribe_way` varchar(200) DEFAULT NULL COMMENT '赎回到账方式',
  `deal_trench` varchar(200) DEFAULT NULL COMMENT '交易渠道',
  `yield_rate` varchar(200) DEFAULT NULL COMMENT '预期年化收益率',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
