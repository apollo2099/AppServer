package com.taozhu.common.util;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class HttpUrlUtils {

	/**
	 * 从网络上面下载资源文件
	 * @param url 网络资源地址
	 * @param dir 保存目录
	 * @return 下载结果
	 */
	public static String downloadFromUrl(String url,String dir) {
		try {
			URL httpurl = new URL(url);
			String fileName = getFileNameFromUrl(url);
			System.out.println(fileName);
			File file = new File(dir + fileName);
			FileUtils.copyURLToFile(httpurl, file);
		} catch (Exception e) {
			e.printStackTrace();
			return "Fault!";
		} 
		return "Successful!";
	}
	
	/**
	 * 
	 * @param url 网络资源地址
	 * @return 文件名
	 */
	public static String getFileNameFromUrl(String url){
		String name = new Long(System.currentTimeMillis()).toString() + ".X";
		int index = url.lastIndexOf("/");
		if(index > 0){
			name = url.substring(index + 1);
			if(name.trim().length()>0){
				return name;
			}
		}
		return name;
	}
}
