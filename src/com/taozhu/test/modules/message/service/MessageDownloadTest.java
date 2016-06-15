package com.taozhu.test.modules.message.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.util.ZipUtils;
import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;

public class MessageDownloadTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SdkHttpResult result=ApiHttpClient.getMessageHistoryUrl("c9kqb3rdk7mfj", "yBI76FQQOt", "2014122713", FormatType.json);
			System.out.println(result);
			System.out.println();
			String temp= result.getResult();
			Map<String, Object> map= JSONObject.parseObject(temp,HashMap.class);
			System.out.println(map.get("url"));
			String url=(String) map.get("url");
			String date=(String) map.get("date");
			// http://119.254.110.38/15/2014122713/2b1aa6b2-399b-4d80-b81a-c669d0bdd1cc.zip
		
			  String res = downloadFromUrl("http://119.254.110.38/15/2014122713/2b1aa6b2-399b-4d80-b81a-c669d0bdd1cc.zip","d:/");  
			  System.out.println(res);
			  
			  //解压文件，读取聊天记录入库
			  ZipUtils.unZip("d:\\2b1aa6b2-399b-4d80-b81a-c669d0bdd1cc.zip","d:\\");
			  
			  
			  //
			  BufferedReader br = new BufferedReader(new FileReader("D:/2014-12-27-13"));  
			  String data = br.readLine();//一次读入一行，直到读入null为文件结束  
			  while( data!=null){  
			        System.out.println(data);  
			        data = br.readLine(); //接着读下一行  
			        System.out.println(data);
			        
			        int index= data.indexOf("{");
			        String tempMsg= data.substring(index, data.length());
			        System.out.println(tempMsg);
			    	Map<String, Object> msgMap= JSONObject.parseObject(tempMsg,HashMap.class);
			    	System.out.println("聊天发送者"+msgMap.get("fromUserId"));
			 }  

			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String downloadFromUrl(String url,String dir) {

		try {
			URL httpurl = new URL(url);
			String fileName = getFileNameFromUrl(url);
			System.out.println(fileName);
			File f = new File(dir + fileName);
			FileUtils.copyURLToFile(httpurl, f);
		} catch (Exception e) {
			e.printStackTrace();
			return "Fault!";
		} 
		return "Successful!";
	}
	
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
