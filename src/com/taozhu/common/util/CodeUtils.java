package com.taozhu.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 
 *  
 * Simple to Introduction  
 * @Package:      [com.taozhu.common.util]  
 * @ClassName:    [CodeUtils]   
 * @Description:  [用于生成字段唯一主键的算法]   
 * @Author:       [liaoxiongjian]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
public class CodeUtils {
	
	private static Integer temp = 0;
	private static Integer orderCodeTemp = 0;
	
	public synchronized static String getCode(){
		
		String code=UUID.randomUUID().toString().replace("-", "");
		return code;
	}

	
	/**
	 * 
	 * 功能描述： 生成 Code
	 * @param prefix
	 *                  code标识
	 * @return
	 */
	public synchronized static String getCode(String prefix) {
		if (temp >= 100000) {
			temp = 0;
		}
		++temp;
		String tempString = temp.toString();
		String code = String.valueOf(System.currentTimeMillis());
		while (prefix.length() < 5) {
			prefix += "U";
		}
		while (tempString.length() < 6) {
			tempString += "T";
		}
		code += prefix;
		code += tempString;
		return code;
	}
	
	

	/**
	 * 
	 * 功能描述： 生成  OrderCode
	 * @param prefix
	 *                  订单前缀标识
	 * @return
	 */
	public synchronized static String getOrderCode(String prefix){
		if (orderCodeTemp>=10000) {
			orderCodeTemp=0;
		}
		++orderCodeTemp;
		String orderCodeTempString = orderCodeTemp.toString();
		
		Date date= new Date(System.currentTimeMillis());
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    String code= formatter.format(date);
		while (orderCodeTempString.length()<5) {
			orderCodeTempString += "T";
		}
		code += prefix;
		code += orderCodeTempString;
		return code;
	}
	
	
	/**
	 * 创建指定数量的随机字符串
	 * 
	 * @param numberFlag
	 *            是否是数字
	 * @param length
	 *            长度
	 * @return
	 */
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}
	
	public synchronized static long createRandom(){
		long num=Math.round(Math.random()*89999999+1000000);
		 return num;
	}

}
