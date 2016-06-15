package com.taozhu.common.message;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.taozhu.common.util.SysPropertyUtil;

public class Sendsms {
	private static String Url = "http://gbk.sms.webchinese.cn";
	private static  String account = "jinrongren";
	private static String passwd = "438cc8359e7f1787370f";
	private static String getUrl(){
		return SysPropertyUtil.getInstance().getValue("sms.urlPath");
	}
	private static String getAccount(){
		return SysPropertyUtil.getInstance().getValue("sms.account");
	}
	private static String getPassWord(){
		return SysPropertyUtil.getInstance().getValue("sms.passowd");
	}
	public static boolean sendSms(String content, String mobiles) {
		boolean flag=false;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(getUrl()); 
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
		NameValuePair[] data ={ new NameValuePair("Uid", getAccount()),new NameValuePair("Key", getPassWord()),new NameValuePair("smsMob",mobiles),new NameValuePair("smsText",content)};
		post.setRequestBody(data);
		try {
			client.executeMethod(post);
			int statusCode = post.getStatusCode();
			System.out.println("statusCode:"+statusCode);
			if(statusCode==200){
				String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
				System.out.println(result); //打印返回消息状态
				if("1".equals(result)){
					flag=true;
				}
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
		return flag;
	}
	public static void main(String[] args) {
		/**
		 * 查看目前剩余短信条数
		 */
		String url = "http://sms.webchinese.cn/web_api/SMS/?Action=SMS_Num&Uid=jinrongren&Key=438cc8359e7f1787370f";
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url); 
		try {
			client.executeMethod(post);
			int statusCode = post.getStatusCode();
			if(statusCode==200){
				String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
				System.out.println("金融人目前还剩下短信："+result); //打印返回消息状态
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
	}
}
