package com.taozhu.modules.web.banner.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.taozhu.modules.web.apkUtil.entity.ApkInfo;
import com.taozhu.modules.web.apkUtil.utils.ApkUtil;

/**
 * 上传广告图片
 *
 */
@Controller
@RequestMapping(value="upBannerPhotoController")
public class UpBannerPhotoController {

	@RequestMapping(params = "upload",method=RequestMethod.POST)  
    public @ResponseBody String upload(@RequestParam(value = "fileAddPic", required = false) CommonsMultipartFile file, HttpServletRequest request) {  
		String dir= request.getParameter("dir");
        String path = request.getSession().getServletContext().getRealPath("/").replace("\\", "/")+dir;
        String backupPath = request.getSession().getServletContext().getRealPath("/").replace("AppServer1\\", "")+"backup";
        String prefix = System.currentTimeMillis()+"";
        int index = file.getOriginalFilename().indexOf(".");
        String fileName = prefix+file.getOriginalFilename().substring(index-1);  
        File targetFile = new File(path, fileName);  
        File backupFile = new File(backupPath);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        if(!backupFile.exists()){  
        	backupFile.mkdirs();  
        }  
  
        //保存  
        try {  
            file.transferTo(targetFile); 
            
           // file.transferTo(backupFile);
            copyPhoto2Backup(path+"/"+fileName,backupPath+"/"+fileName);
        } catch (Exception e) {  
            e.printStackTrace();  
            return "-1";
        }  
        Map<String, String> map = new HashMap<String, String>();
		map.put("fileUrl",request.getScheme() + "://" + request.getServerName() + ":"+ request.getServerPort() + request.getContextPath()+ "/"+dir+"/" + fileName);
		map.put("fileName", fileName);  
        return JSONObject.toJSONString(map);  
    }  
	
	@RequestMapping(params = "uploadApk",method=RequestMethod.POST)  
    public @ResponseBody String uploadApk(@RequestParam(value = "fileAddPic", required = false) CommonsMultipartFile file, HttpServletRequest request) {  
		String dir= request.getParameter("dir");
        String path = request.getSession().getServletContext().getRealPath("/").replace("\\", "/")+dir;
        String backupPath = request.getSession().getServletContext().getRealPath("/").replace("AppServer1\\", "")+"fileServer";
        String prefix = System.currentTimeMillis()+"";
        int index = file.getOriginalFilename().indexOf(".");
        String fileName = prefix+file.getOriginalFilename().substring(index-1);  
        File targetFile = new File(path, file.getOriginalFilename());  
        File backupFile = new File(backupPath);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        if(!backupFile.exists()){  
        	backupFile.mkdirs();  
        }  
        String versionCode="";
        //保存  
        try {  
            file.transferTo(targetFile); 
           // file.transferTo(backupFile);
            //获取apk的版本号
            ApkInfo apkInfo = new ApkUtil().getApkInfo(request.getSession().getServletContext().getRealPath("/").replace("\\", "/")+"aapt",path+"/"+file.getOriginalFilename());
            versionCode = apkInfo.getVersionCode();
            copyPhoto2Backup(path+"/"+file.getOriginalFilename(),backupPath+"/"+fileName);
        } catch (Exception e) {  
            e.printStackTrace();  
            return "-1";
        }  
        Map<String,String> map = new  HashMap<String,String>();
        map.put("fileUrl", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"+dir+"/"+fileName);  
        map.put("fileName", fileName);  
        map.put("versionCode", versionCode);  
        return JSONObject.toJSONString(map);  
    }  
	
	
	private void copyPhoto2Backup(String srcFile,String destFile){
		try {
    		
		  		  FileInputStream fi=new FileInputStream(srcFile);
		  		  BufferedInputStream in=new BufferedInputStream(fi);
		  		  FileOutputStream fo=new FileOutputStream(destFile);
		  		  BufferedOutputStream out=new BufferedOutputStream(fo);
		  		  byte[] buf=new byte[1024];
		  		  int len=in.read(buf);//读文件，将读到的内容放入到buf数组中，返回的是读到的长度
		  		  while(len!=-1){
		  		   out.write(buf, 0, len);
		  		   len=in.read(buf);
		  		  }
		
		  		  out.close();
		  		  fo.close();
		  		  in.close();
		  		  fi.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
