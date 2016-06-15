package com.taozhu.modules.app.user.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义用户字典常量
 * @author liaoxj
 *
 */
public class UserConstants {

	/**
	 * 用户编码集合
	 */
	public static Map<String,Object> USER_ID_MAP=new HashMap<String,Object>();
	
	public static Integer USER_STATUS_ON_LINE=1;
	public static Integer USER_STATUS_STEALTH=2; 
	public static Integer USER_STATUS_OFF_LINE=0;
	public static Integer USER_STATUS_DELETE=-1;
	public static String USER_HEAD_IMAGES_DEFAULT="";
	/**
	 * 用户类型字典-业务经理
	 */
	public static Integer USER_TYPE_MANAGER=1;
	/**
	 * 用户类型字典-普通用户
	 */
	public static Integer USER_TYPE_GENERAL=2;
	/**
	 * 用户性别字典-男
	 */
	public static Integer SEX_BOY=1;
	/**
	 * 用户性别字典-女
	 */
	public static Integer SEX_GIRL=2;
	/**
	 * 用户性别字典-不详
	 */
	public static Integer SEX_UN_KNOWN=0;
	
	public static Integer USER_IS_ALLOW_TRUE=1;
	public static Integer USER_IS_ALLOW_FALSE=0;

}
