package com.taozhu.common.message;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
 
/**
 *  尚景 短信    http://goinsms.com/
 *  依赖jar包  
 *   httpcore-4.2.2.jar
 *   httpclient-4.2.1.jar
 *   commons-io-2.0.1.jar
 * 
  * 名称: SMSUtils.java
  * 描述: 
  * 类型: JAVA
  * @since  2014-11-29
  * @author liaoxj
 */
 
public class SMSUtils {
	static String  url = "http://api.goinsms.com/sms";
    // api账号(请联系销售获取,不是网站登陆账号)
	static  String userId = "a0e72f36-e80d-4931-b70a-b2bd41d8ce2b";
    // api密码(不是网站登陆密码)
	static String passwd = "ebbfd3b1da";
 
    public static String sendSms(String content, String mobiles) {
        String result = null;
        String posturl = url + "/http/submit";
        String message = "{'content':'" + content + "'," + "'mobiles':'"
                + mobiles + "'," + "'passwd':'" + passwd + "'," + "'userId':'"
                + userId + "'}";
     
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(posturl);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("message", message));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = null;
                try {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "utf-8");
                } finally {
                    if (instream != null)
                        instream.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    public static String getReport() {
        String result = null;
        String posturl = url + "/http/report";
        String message = "{\"passwd\":\"" + passwd + "\",\"userId\":\""
                + userId + "\"}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(posturl);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("message", message));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = null;
                try {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "utf-8");
                } finally {
                    if (instream != null)
                        instream.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    public static String getDeliver() {
        String result = null;
        String posturl = url + "/http/deliver";
        String message = "{\"passwd\":\"" + passwd + "\",\"userId\":\""
                + userId + "\"}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(posturl);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("message", message));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = null;
                try {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "utf-8");
                } finally {
                    if (instream != null)
                        instream.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    public static String getBalance() {
        String result = null;
        String posturl = url + "/http/balance";
        String message = "{\"passwd\":\"" + passwd + "\",\"userId\":\""
                + userId + "\"}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(posturl);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("message", message));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = null;
                try {
                    instream = entity.getContent();
                    result = IOUtils.toString(instream, "utf-8");
                } finally {
                    if (instream != null)
                        instream.close();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
 
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    	SMSUtils api = new SMSUtils();
        System.out.println("*************发送短信*************");
        String content = "祝你生日快乐，天天开心！";
        String mobiles = "13927409420";
        System.out.println("号码:" + mobiles + " 内容:" + content);
        System.out.println("结果:" + api.sendSms(content, mobiles));
        System.out.println("*************状态报告*************");
        System.out.println("结果:" + api.getReport());
        System.out.println("*************获取上行*************");
        System.out.println("结果:" + api.getDeliver());
        System.out.println("*************获取余额*************");
        System.out.println("结果:" + api.getBalance());
 
    }
 
}
 

      