package com.taozhu.common.util;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
public class ZipUtils {
private static final int buffer = 2048;
	
	public static void main(String[] args)
	{
		ZipUtils.unZip("d:\\2b1aa6b2-399b-4d80-b81a-c669d0bdd1cc.zip","D:\\");
	}
	
	public static void unZip(String path,String savepath)
	{
        int count = -1;
        int index = -1;
		boolean flag = false;
		
		File file = null; 
        InputStream is = null;  
        FileOutputStream fos = null;  
        BufferedOutputStream bos = null;
        
//		savepath = path.substring(0, path.lastIndexOf("\\")) + "\\";

        try
        { 
        	ZipFile zipFile = new ZipFile(path); 

        	Enumeration<?> entries = zipFile.getEntries();
        	
            while(entries.hasMoreElements())
            { 
            	byte buf[] = new byte[buffer]; 
            	
                ZipEntry entry = (ZipEntry)entries.nextElement(); 
                
                String filename = entry.getName();
                index = filename.lastIndexOf("/");
				if(index > -1)
					filename = filename.substring(index+1);
				
                filename = savepath + filename;
                
//                flag = isPics(filename);
//                if(!flag)
//                	continue;
                
                file = new File(filename); 
                file.createNewFile();
                
                is = zipFile.getInputStream(entry); 
                
                fos = new FileOutputStream(file); 
                bos = new BufferedOutputStream(fos, buffer);
                
                while((count = is.read(buf)) > -1)
                { 
                	System.out.println("读取数据长度:"+count);
                    bos.write(buf, 0, count ); 
                } 
                bos.flush();
                fos.close(); 
                is.close(); 
            } 
            
            zipFile.close(); 
            
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        } 
    } 

	public static boolean isPics(String filename)
	{
		boolean flag = false;
		
//		if(filename.endsWith(".jpg") || filename.endsWith(".gif")  || filename.endsWith(".bmp") || filename.endsWith(".png"))
//			flag = true;
		
		return flag;
	}
}
