package com.taozhu.modules.app.photo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.common.base.controller.BaseController;
import com.taozhu.common.util.ImageUtil;
import com.taozhu.common.util.SysPropertyUtil;
import com.taozhu.modules.app.common.AppResultUtil;

@Controller
@RequestMapping(value="/photoController.do")
public class PhotoController  extends BaseController{
	 @Value("${global.path}")   
	 private String global_path;
	
	/**
	 * 图片文件上传接口实现
	 * @param response
	 * @param request
     * @param param={"data":"图片字节流数据 byte[]"}
     * @return {"resultFlag":"true/false","msg":"结果消息","result":"[]"}
	 * @throws Exception 
	 */
	@RequestMapping(params="upload")
	public void upload(HttpServletResponse response,HttpServletRequest request) throws Exception{
		ServletInputStream input = request.getInputStream();
		String path = request.getSession().getServletContext().getRealPath("/").replace("\\", "/")+ "fileServer";
		byte[] bytes=IOUtils.toByteArray(input);
		if(!StringUtils.isEmpty(bytes)){
			String prefix = System.currentTimeMillis() + "";
			String fileName=prefix+".png";
			String global_path=  SysPropertyUtil.getInstance().getValue("global.path");
			File targetFile = new File(path);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			
			String filePath=path+"/"+fileName;
			//将字节流转换成图片，并且保存图片信息
			Boolean isFlag=ImageUtil.byteToImage(bytes, filePath);
			//组装消息返回数据
			String returnFilePath=global_path+"/fileServer/"+fileName; 
			String fileName96=prefix+"_96.png";
			String fileName200=prefix+"_200.png";
			//生成对应的对应的缩放图
			ImageUtil.saveImageAsJpg(filePath, path+"/"+fileName96, 96, 96);
			ImageUtil.saveImageAsJpg(filePath, path+"/"+fileName200, 200, 200);
			JSONObject retVal=new JSONObject();
			if(isFlag){
				retVal=AppResultUtil.resultPack(true, "上传文件成功", returnFilePath);
			}else{
				retVal=AppResultUtil.resultPack(false, "上传文件失败", "");
			}
			this.printJson(response, retVal);
		}
		input.close();
	}

}
