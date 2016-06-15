package com.taozhu.test.modules.user.controller;


import java.io.*;
/**
 模拟一个jpeg位图到byte[]
 */
class Image2Buff
{
	static byte[] image2Bytes(String imgSrc) throws Exception
	{
		FileInputStream fin = new FileInputStream(new File(imgSrc));
		//可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
		byte[] bytes  = new byte[fin.available()];
		//将文件内容写入字节数组，提供测试的case
		fin.read(bytes);
		fin.close();
		return bytes;
	}
}

class Buff2Image
{
	/**
	 @param b 传入的byte
	 @param tagSrc 目标文件名
		
	 */
	static void buff2Image(byte[] b,String tagSrc) throws Exception
	{
		FileOutputStream fout = new FileOutputStream(tagSrc);
		//将字节写入文件
		fout.write(b);
		fout.close();
	}
}

//本质上实现了copy 命令
public class ImageBufTest
{
	public static void main(String[] args) throws Exception
	{
		//先模拟一个图形byte[]
		byte[] b = Image2Buff.image2Bytes("D:/mp3详细设计-1.jpg");
		//存为文件
		Buff2Image.buff2Image(b,"D:/test1.jpg");

		System.out.println("Hello World!");
	}
}



