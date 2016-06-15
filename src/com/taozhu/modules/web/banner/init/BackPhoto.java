package com.taozhu.modules.web.banner.init;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BackPhoto extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BackPhoto() {
		super();
	}


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	    String path = this.getServletContext().getRealPath("/").replace("AppServer1\\", "")+"backup";
	    File file = new File(path);
	    if(!file.exists()){
	    	file.mkdir();
	    }
	    String[] photos = file.list();
	    
	    String destPath = this.getServletContext().getRealPath("/").replace("\\", "/")+"fileServer";
	    
	    for (String item: photos){
	    	String srcFile = path + "/" + item;
	    	String destFile = destPath + "/" + item;
	    	
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

}
