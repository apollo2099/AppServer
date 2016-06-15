package com.taozhu.test.modules.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.taozhu.common.util.ImageUtil;
import com.taozhu.common.util.SysPropertyUtil;

public class ImageUtilTest {

	public ImageUtilTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		String path="D:\\";
		String imagePath="D:/mp3详细设计-1.jpg";		
		
		
		FileInputStream fin = new FileInputStream(new File(imagePath));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes  = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);
        fin.close();
		
//		File file = new File(imagePath);//
//		InputStream 	fis = new FileInputStream(file);
//		byte[]  b= IOUtils.toByteArray(fis);
		String prefix = System.currentTimeMillis() + "";
		String fileName=prefix+".png";
		String global_path= "http://127.0.0.1:8080/AppServer/";
		File targetFile = new File(path);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		
		String filePath=path+"/"+fileName;
		//将字节流转换成图片，并且保存图片信息
//		File file=new File(filePath);
//		FileInputStream is=new FileInputStream(file);
//		FileOutputStream os=new  FileOutputStream(is);
//		os.write(b);
		Boolean isFlag=ImageUtil.byteToImage(bytes, filePath);
		String returnFilePath=global_path+"/fileServer/"+fileName; 
		System.out.println(returnFilePath);

	}

}
