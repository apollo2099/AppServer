
/*
SQLyog Ultimate v11.26 (32 bit)
MySQL - 5.0.96-community-nt 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;


drop Table ma_function;

create table ma_function (
	`function_id` Decimal (4),
	`function_code` varchar (90),
	`function_name` varchar (150),
	`function_desc` varchar (600),
	`parent_id` Decimal (4),
	`function_page` varchar (300)
); 


--测试数据
insert into ma_function (`function_id`, `function_code`, `function_name`, `function_desc`, `parent_id`, `function_page`) values('1','menu1','菜单1',NULL,'0',NULL);
insert into ma_function (`function_id`, `function_code`, `function_name`, `function_desc`, `parent_id`, `function_page`) values('3','sonmenu1','菜单1-子菜单1',NULL,'1','/AppServer/login.jsp');
insert into ma_function (`function_id`, `function_code`, `function_name`, `function_desc`, `parent_id`, `function_page`) values('4','sonmenu2','菜单1-子菜单2',NULL,'1',NULL);
insert into ma_function (`function_id`, `function_code`, `function_name`, `function_desc`, `parent_id`, `function_page`) values('2','menu2','菜单2',NULL,'0',NULL);
insert into ma_function (`function_id`, `function_code`, `function_name`, `function_desc`, `parent_id`, `function_page`) values('5','sonmenu3','菜单2-子菜单1',NULL,'2',NULL);


