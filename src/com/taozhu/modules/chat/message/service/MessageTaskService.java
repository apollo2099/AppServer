package com.taozhu.modules.chat.message.service;


import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.mybatis.util.BaseDAOUtil;
import com.taozhu.common.util.DateUtil;
import com.taozhu.common.util.HttpUrlUtils;
import com.taozhu.common.util.ObjectUtils;
import com.taozhu.common.util.SysPropertyUtil;
import com.taozhu.common.util.ZipUtils;

/*
 * 用户聊天记录定时下载服务
 */
@Service("MessageTaskService")
public class MessageTaskService {
	private static final Log logger	= LogFactory.getLog(MessageTaskService.class);
	@Resource
	private MessageService messageService;
	
	public void download(){
		try {
			//融云app key
			String  appKey=SysPropertyUtil.getInstance().getValue("appKey");
			//融云app secret
			String  appSecret=SysPropertyUtil.getInstance().getValue("appSecret");
			//下载时间
			String downTime= DateUtil.dateToStr(new Date(), "yyyyMMddHH");
			String downTimeFile= DateUtil.dateToStr(new Date(), "yyyy-MM-dd-HH");
			
			//调用融云下载服务
			SdkHttpResult result=ApiHttpClient.getMessageHistoryUrl(appKey, appSecret, downTime, FormatType.json);
			if(result.getHttpCode()==200){
				String temp= result.getResult();
				//将返回的下载连接信息转换为json
				Map<String, Object> map= JSONObject.parseObject(temp,HashMap.class);
				String url=(String) map.get("url");
				String date=(String) map.get("date");
				if(ObjectUtils.isNotEmpty(url)&&ObjectUtils.isNotEmpty(date)){
		            //调用url下载到本地资源库中
					String msgPath=SysPropertyUtil.getInstance().getValue("message.his.path");
					String path=msgPath+"/"+date+"/";
					String flag =HttpUrlUtils.downloadFromUrl(url,path);
					
					  //解压文件，读取聊天记录入库
					  String fileName = HttpUrlUtils.getFileNameFromUrl(url);
					  ZipUtils.unZip(path+fileName,path);
					  
					  
					  //读取解压后的文件信息  
					 // File file=new File(path+"2014-12-27-13");

					  File file=new File(path+downTimeFile);
					  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
					  String data = br.readLine();//一次读入一行，直到读入null为文件结束  
					  while( data!=null){  
					        data = br.readLine(); //接着读下一行  
					        if(ObjectUtils.isNotEmpty(data)){
						        int index= data.indexOf("{");
						        String tempMsg= data.substring(index, data.length());
						    	Map<String, Object> msgMap= JSONObject.parseObject(tempMsg,HashMap.class);
						    	
								String appId=BaseDAOUtil.getStringValue(msgMap, "appId");
								Integer fromUserId=BaseDAOUtil.getIntValue(msgMap, "fromUserId");
								Integer targetId=BaseDAOUtil.getIntValue(msgMap, "targetId");
								Integer targetType=BaseDAOUtil.getIntValue(msgMap, "targetType");
								Integer groupId=BaseDAOUtil.getIntValue(msgMap, "groupId");
								String classname=BaseDAOUtil.getStringValue(msgMap, "classname");
								JSONObject contentJson=(JSONObject) msgMap.get("content");
								String dateTime=BaseDAOUtil.getStringValue(msgMap, "dateTime");
						    	
						    	//保存聊天记录信息
								Map<String,Object> param=new HashMap<String, Object>();
								param.put("appId", appId);
								param.put("fromUserId", fromUserId);
								param.put("targetId", targetId);
								param.put("targetType",targetType );
								param.put("groupId", groupId);
								param.put("classname", classname);
								param.put("content", contentJson.get("content"));
								param.put("dateTime", dateTime);
						    	messageService.saveMessage(param);
					        }
					 }  
				}
			}
		
		} catch (Exception e) {
			logger.error("下载聊天记录异常",e);
		}  
	}
}
