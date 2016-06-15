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

/*clean data for system*/
DELETE FROM `lv_app`;
DELETE FROM `lv_bank`;
DELETE FROM `lv_bank_branch`;
DELETE FROM `lv_bank_branch_details`;
DELETE FROM `lv_banner`;
DELETE FROM `lv_branch_banner`;
DELETE FROM `lv_business`;
DELETE FROM `lv_business_bank`;
DELETE FROM `lv_business_details`;
DELETE FROM `lv_business_user`;
DELETE FROM `lv_interface`;
DELETE FROM `lv_message_auth_code`;
DELETE FROM `lv_question`;
DELETE FROM `lv_user`;
DELETE FROM `lv_user_attention`;
DELETE FROM `lv_user_question`;
DELETE FROM `lv_task_quartz`;
DELETE FROM `temp_bank`;
DELETE FROM `temp_bank_branch`;
DELETE FROM `temp_business`;
DELETE FROM `temp_user`;
DELETE FROM `temp_batch`;



/*Data for the table `lv_interface` */
LOCK TABLES `lv_interface` WRITE;
insert  into `lv_interface`(`id`,`inf_name`,`inf_path`,`inf_param`,`inf_result`,`inf_remark`,`create_time`,`modify_time`,`modify_name`,`code`) values (40,'广告列表查询','http://120.24.214.0:8080/AppServer/bannerController.do?queryList',NULL,'{\"msg\":\"返回成功\",\"result\":[],\"success\":true}','1','2014-11-28 23:54:37',NULL,NULL,NULL),(41,'用户帐号信息登录','http://120.24.214.0:8080/AppServer/userController.do?login',' param={\"account\":\"帐号\",\"password\":\"密码\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"[]\"}','用户帐号信息登录','2014-11-29 00:05:54',NULL,NULL,NULL),(42,'用户注册功能接口实现','http://120.24.214.0:8080/AppServer/userController.do?register',' param={\"account\":\"帐号\",\"password\":\"密码\",\"authCode\":\"验证码\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"[\"user_code\":\"用户编码\"]\"}',NULL,'2014-11-29 00:08:20',NULL,NULL,NULL),(43,'查看用户详情接口实现','http://120.24.214.0:8080/AppServer/userController.do?userDetails','param={\"userId\":\"用户编码\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"{\"account\":\"账号\",\"nick_name\":\"昵称\",\"head_image\":\"头像\",\r\n     \"user_type\":\"用户类型\",\"sex\":\"性别\",\"introduction\":\"个人简介\",\"create_time\":\"注册时间\",\"mobile\":\"手机\",\"tel\":\"电话\",\r\n     \"isAllow\":\"是否允许聊天\",\"user_card_path\":\"用户名片\"，\"user_id\",\"用户编码\"}\"','查看用户详情接口实现','2014-11-29 00:11:13',NULL,NULL,NULL),(44,'获取用户关注列表接口实现','http://120.24.214.0:8080/AppServer/userController.do?findUserAttention','param={\"userId\":\"用户编号\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"[{\"nick_name\":\"用户名称\",\"head_image\":\"用户头像\"}....{}]\"}','获取用户关注列表接口实现','2014-11-29 00:12:31',NULL,NULL,NULL),(45,'用户关注接口实现','http://120.24.214.0:8080/AppServer/userController.do?saveUserAttention','param={\"userId\":\"帐号\",\"userAttentionId\":\"被关注者账号\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"\"}','关注客户经理','2014-11-29 00:14:21',NULL,NULL,NULL),(46,'修改用户信息接口实现','http://120.24.214.0:8080/AppServer/userController.do?updateUser','param={\"userId\":用户编码,\"headImage\":\"头像\",\"sex\":\"性别\",\"nickname\":\"昵称\",\"introduction\":\"个性签名\",\"userCardPath\":\"个人名片\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"\"}','修改用户信息','2014-11-29 00:15:45',NULL,NULL,NULL),(47,'上传用户地理位置接口','http://120.24.214.0:8080/AppServer/userController.do?updateUserPosition','param={\"userId\":\"帐号\",\"longitude\":\"经度\",\"latitude\":\"纬度\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"\"}',NULL,'2014-11-29 00:16:19',NULL,NULL,NULL),(48,'修改用户密码接口实现','http://120.24.214.0:8080/AppServer/userController.do?updateUserPwd','param={\"userId\":\"帐号\",\"oldPassword\":\"旧密码\",\"newPassword\":\"新密码\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"\"}','密码修改','2014-11-29 00:18:40',NULL,NULL,NULL),(49,'找回密码接口实现（重置密码）','http://120.24.214.0:8080/AppServer/userController.do?findUserPwd','param={\"userId\":\"帐号\",\"password\":\"密码\",\"authCode\":\"验证码\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[\"password\":\"密码\"]}','找回密码','2014-11-29 00:21:47',NULL,NULL,NULL),(50,'发送验证码','http://120.24.214.0:8080/AppServer/messageAuthCodeController.do?sendValidateCode',' param={\"mobile\":\"手机号码\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"\"}','发送验证码','2014-11-29 00:23:49',NULL,NULL,NULL),(51,'网点业务经理查询列表','http://120.24.214.0:8080/AppServer/userController.do?queryUser','param={\"branchCode\":网点编码}\r\n',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[\"{\"account\":\"账号\",\"nick_name\":\"昵称\",\"head_image\":\"头像\",\"user_type\":\"用户类型\",\"sex\":\"性别\",\"introduction\":\"个人简介\",\"create_time\":\"注册时间\",\"mobile\":\"手机\",\"tel\":\"电话\",\"is_allow\":\"是否允许聊天\",\"user_card_path\":\"用户名片\",\"user_code\",\"用户编码\"}...{}]}\r\n',NULL,'2014-11-29 00:28:16',NULL,NULL,NULL),(52,'附近业务经理','http://120.24.214.0:8080/AppServer/userController.do?queryUser','param={\"longitude\":\"经度\",\"latitude\":\"纬度\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[\"{\"account\":\"账号\",\"nick_name\":\"昵称\",\"head_image\":\"头像\",\"user_type\":\"用户类型\",\"sex\":\"性别\",\"introduction\":\"个人简介\",\"create_time\":\"注册时间\",\"mobile\":\"手机\",\"tel\":\"电话\",\"is_allow\":\"是否允许聊天\",\"user_card_path\":\"用户名片\",\"user_code\",\"用户编码\"}...{}]}\r\n','备注：附近业务经理接口是用户提交自己信息，你们计算','2014-11-29 00:30:09',NULL,NULL,NULL),(53,'网点所有广告','http://120.24.214.0:8080/AppServer/bannerController.do?queryDetails','param={\"bannerCode\":\"广告编号}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[\"banner_path\":\"广告图片地址\",\"banner_type\":\"广告类型\",\"webUrl\":\"web_url\",\"order_value\":\"排序值\"]}',NULL,'2014-11-29 00:31:39',NULL,NULL,NULL),(54,'业务列表查询','http://120.24.214.0:8080/AppServer/businessController.do?queryList',NULL,' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[\"business_name\":\"业务名称\",\"business_code\":\"业务编码\",sub:[\"subName\":\"子业务名称\",\"subImages\":\"子业务图片\",\"subType\":\"子业务类型\",\"subBusinessCode\":\"子业务编码\"]]}\r\n',NULL,'2014-11-29 00:33:00',NULL,NULL,NULL),(55,'问题列表查询','http://120.24.214.0:8080/AppServer/questionController.do?queryList','param={\"subBusinessCode\":\"子业务编号\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[{\"question_name\":\"业务名称\",\"question_desc\":\"业务编码\"}....{}]}\r\n',NULL,'2014-11-29 00:33:53',NULL,NULL,NULL),(56,'问题调查反馈','http://120.24.214.0:8080/AppServer/questionController.do?insertUserQues','param={uiserId:[{questionCode:1,isAnswer:0},{questionCode:2,isAnswer:1}],b:[{questionCode:3,isAnswer:0}]}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[]}\r\n',NULL,'2014-11-29 00:35:07',NULL,NULL,NULL),(57,'银行列表查询','http://120.24.214.0:8080/AppServer/bankController.do?queryList','param={\"subBusinessCode\":\"子业务编号（非必填）\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[\"bank_name\":\"银行名称\",\"bank_code\":\"银行编码\"]}\r\n',NULL,'2014-11-29 00:36:19',NULL,NULL,NULL),(58,'银行网点查询','http://120.24.214.0:8080/AppServer/bankController.do?queryBranchList','param={\"bankCode\":\"银行编码\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[\"branch_name\":\"网点名称\",\"branch_title\":\"网点标题\"，\"branch_desc\":\"网点描述\"，\"branch_code\":\"网点编码\"，\"branchCode\":\"网点编码\",\"bannerList\":[{\"banner_name\":\"网点广告名称\",\"banner_path\":\"网点广告标题\"，\"banner_desc\":\"网点广告描述\"}....{}...{}]]}\r\n',NULL,'2014-11-29 00:36:39',NULL,NULL,NULL),(59,'根据子业务编号查询推荐的业务经理接口','http://120.24.214.0:8080/AppServer/userController.do?queryUser','param={subBusinessCode\":\"子业务编码\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[\"{\"account\":\"账号\",\"nick_name\":\"昵称\",\"head_image\":\"头像\",\"user_type\":\"用户类型\",\r\n\"sex\":\"性别\",\"introduction\":\"个人简介\",\"create_time\":\"注册时间\",\"mobile\":\"手机\",\"tel\":\"电话\",\"is_allow\":\"是否允许聊天\",\"user_card_path\":\"用户名片\",\"user_code\",\"用户编码\"}...{}]}','根据子业务编号查询推荐的业务经理接口（子业务编码必填）','2014-12-03 01:09:39',NULL,NULL,NULL),(60,'业务详情搜索','http://120.24.214.0:8080/AppServer/businessController.do?queryBusinessByName','param={\"businessName\":\"aaa\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[{\"sub_images\":\"图片路径\",\"sub_name\":\"业务名称\",\"code\":\"业务code\"}...{}]}',NULL,'2014-12-06 23:03:06',NULL,NULL,NULL),(61,'图片上传接口实现','http://120.24.214.0:8080/AppServer/photoController.do?upload',NULL,'{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"[]\"}','将图片字节流放如请求流中；','2014-12-07 00:29:35',NULL,NULL,NULL),(62,'更多业务列表接口','http://120.24.214.0:8080/AppServer/businessController.do?queryBusinessDetails','param={businessCode:\"aaa\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[{sub_images:\"图片路径\",sub_name:\"名称\",sub_desc:\"描述\",code:\"code\"}]}\r\n',NULL,'2014-12-13 15:22:30',NULL,NULL,NULL),(63,'首页广告详情','http://120.24.214.0:8080/AppServer/bannerController.do?queryBannerByCode','param={code:\"code\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[{banner_name:\"广告名称\",banner_path:\"图片路径\",banner_desc:\"描述\",code:\"code\"}]}\r\n',NULL,'2014-12-13 15:47:10',NULL,NULL,NULL),(64,'网点广告详情','http://120.24.214.0:8080/AppServer/bannerController.do?queryBankBannerByCode','param={code:\"code\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":[{banner_name:\"广告名称\",banner_path:\"图片路径\",banner_desc:\"描述\",branch_code:\"网点code\",code:\"code\"}]}',NULL,'2014-12-13 15:48:41',NULL,NULL,NULL),(65,'查看业务经理详情（他人详情信息，包含关注标识）','http://120.24.214.0:8080/AppServer/userController.do?userMngDetails',' param={\"userId\":\"帐号（非必填）\",\"userAttentionId\":\"被关注者账号（必填）\"}',' {\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"{\"account\":\"账号\",\"nick_name\":\"昵称\",\"head_image\":\"头像\",\r\n     \"user_type\":\"用户类型\",\"sex\":\"性a别\",\"introduction\":\"个人简介\",\"create_time\":\"注册时间\",\"mobile\":\"手机\",\"tel\":\"电话\",\r\n     \"isAllow\":\"是否允许聊天\",\"user_card_path\":\"用户名片\"，\"user_id\",\"用户编码\",\"attionFlag\":关注标志}\"','11','2014-12-13 23:48:40',NULL,NULL,NULL),(66,'取消用户经理关注接口','http://120.24.214.0:8080/AppServer/userController.do?deleteUserAttention','param={\"userId\":\"帐号\",\"userAttentionId\":\"被关注者账号\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":\"\"}',NULL,'2014-12-14 23:36:21',NULL,NULL,NULL),(67,'根据版本号查询app应用信息','http://120.24.214.0:8080/AppServer/applicationController.do?queryDetails','param={\"appVersion\":\"应用版本（必填）\"}','{\"success\":\"true/false\",\"msg\":\"结果消息\",\"result\":{\"app_name\":\"应用名称\",\"app_url\":\"应用地址\",\"app_version\":\"应用版本\",\"remark\":\"更新备注\"}}','android应用版本更新接口','2014-12-27 14:09:26',NULL,NULL,NULL);
UNLOCK TABLES;

/*Data for the table `ma_function` */

LOCK TABLES `ma_function` WRITE;

insert  into `ma_function`(`id`,`function_code`,`function_name`,`function_desc`,`parent_id`,`function_page`) 
values (1,'menu1','系统配置',NULL,0,NULL),
(2,'menu2','接口管理',NULL,0,NULL),
(3,'sonmenu1','菜单列表',NULL,1,'maFunctionGrid.do'),
(4,'sonmenu2','用户列表',NULL,1,'maUserController.do?maUserGrid'),
(5,'sonmenu3','接口列表',NULL,2,'interfaceGrid.do'),
(6,'sonmenu5','手机验证码',NULL,2,'messageAuthCodeGrid.do'),
(7,'1417540363788MENUU1TTTTT','广告管理',NULL,0,NULL),
(8,'1417540690901MENUU2TTTTT','广告列表',NULL,7,'enterBanner.do?bannerList'),
(9,'1417540733020MENUU3TTTTT','业务管理',NULL,0,NULL),
(10,'1417540763425MENUU4TTTTT','银行列表',NULL,19,'BankGrid.do'),
(11,'1417540808198MENUU5TTTTT','业务列表',NULL,9,'businessGrid.do'),
(12,'1417540845344MENUU6TTTTT','问题列表',NULL,9,'questionGrid.do'),
(13,'1417540914190MENUU7TTTTT','用户管理',NULL,0,NULL),
(14,'1417540940722MENUU8TTTTT','用户信息',NULL,13,'userWebController.do?userGrid'),
(15,'1417540957298MENUU9TTTTT','业务经理列表',NULL,13,'userWebController.do?userManagerGrid'),
(16,'1417707420037MENUU1TTTTT','用户关注列表',NULL,13,'userWebController.do?userAttentionGrid'),
(17,'1417714096765MENUU1TTTTT','银行网点列表',NULL,19,'bankBranchGrid.do'),
(18,'1417714131785MENUU2TTTTT','业务详情列表',NULL,9,'businessDetailWebController.do?businessDetailGrid'),
(19,'1417714331857MENUU3TTTTT','银行管理',NULL,0,NULL),
(20,'1417795092690MENUU1TTTTT','银行网点广告',NULL,19,'enterBanner.do?branchBannerList'),
(21,'1418468388373MENUU1TTTTT','定时任务配置',NULL,1,'taskWebController.do?taskGrid'),
(22,'1419660406650MENUU1TTTTT','应用管理',NULL,0,NULL),
(23,'1419660427858MENUU2TTTTT','应用列表',NULL,22,'applicationWebController.do?applicationGrid'),
(24,'1417540845344MENUU6TTTTT','问题答案列表',NULL,9,'questionAttentionGrid.do');
UNLOCK TABLES;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


/*Data for the table `lv_file_define` */

LOCK TABLES `lv_file_define` WRITE;

insert  into `lv_file_define`(`id`,`DATA_BO`,`DATA_ROW_NUM`,`FILE_PATH`,`HEAD_BO`,`FILE_BO`,`CALCULATE_BO`,`TEMPLATE_CUID`,`SHEET_NUM`,`TITLE_ROW_NUM`,`TEMP_TABLE`,`ERROR_BO`)
values 
(1,'BankTempDataHandler','1','/page/file/bank.xls','BankHeadDefineHandler','BankFileDataHandler','BankCalculateHandler','BankImport',0,0,'temp_bank','BankFileErrorHandler'),
(2,'UserTempDataHandler','1','/page/file/user.xls','UserHeadDefineHandler','UserFileDataHandler','UserCalculateHandler','UserImport',0,0,'temp_user','UserFileErrorHandler'),
(3,'BusinessTempDataHandler','1','/page/file/business.xls','BusinessHeadDefineHandler','BusinessFileDataHandler','BusinessCalculateHandler','BusinessImport',0,0,'temp_business','BusinessFileErrorHandler'),
(4,'BankBranchTempDataHandler','1','/page/file/bank_branch.xls','BankBranchHeadDefineHandler','BankBranchFileDataHandler','BankBranchCalculateHandler','BankBranchImport',0,0,'temp_bank_branch','BankBranchFileErrorHandler'),
(5,'BatchTempDataHandler','1','/page/file/batch.xls','BatchHeadDefineHandler','BatchFileDataHandler','BatchCalculateHandler','BatchImport',0,0,'temp_batch','BatchFileErrorHandler');

UNLOCK TABLES;

/*Data for the table `lv_task_quartz` */

LOCK TABLES `lv_task_quartz` WRITE;

insert  into `lv_task_quartz`(`id`,`task_name`,`target_object`,`target_method`,`target_date`,`description`,`status`,`create_time`,`modify_time`,`modify_user_id`,`modify_user_name`) 
values 
(1,'短信服务器','MessageAuthCodeTaskService','init','0 0/30 * * * ?','短信服务器',1,'2014-12-14 02:08:29',NULL,NULL,NULL),
(2,'聊天记录下载','MessageTaskService','download','0 0/30 * * * ?','聊天记录下载',1,'2014-12-27 16:43:57',NULL,NULL,NULL);

UNLOCK TABLES;
