package com.taozhu.common.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;
public class ImageUtil {
		
	/**
	 * 转换Image数据为byte数组
	 * 
	 * @param image
	 *            Image对象
	 * @param format
	 *            image格式字符串.如"jpeg","png"
	 * @return byte数组
	 */
	public static byte[] imageToBytes(BufferedImage image, String format) {
		BufferedImage bImage = new BufferedImage(image.getWidth(null),image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics bg = bImage.getGraphics();
		bg.drawImage(image, 0, 0, null);
		bg.dispose();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, format, out);
		} catch (IOException e) {
			e.printStackTrace();
			// Log.log(null,"imageToBytes():   "+e);
		}
		byte[] bs = out.toByteArray();
		return bs;
	}
 
	
	/**
	 * 转换byte数组为Image
	 * 
	 * @param bytes
	 *            Image的bytes数据数组
	 * @param filename
	 *            为要生成新的文件名
	 * @return boolean
	 */
	public static boolean byteToImage(byte[] b, String FilePath) {
		boolean bl = false;
		File binaryFile = new File(FilePath);
		FileOutputStream fileOutStream=null;
		try {
			fileOutStream = new FileOutputStream(binaryFile);
			for (int i = 0; i < b.length; i++) {
				fileOutStream.write(b[i]);
			}
			fileOutStream.flush();
			bl = true;
		} catch (FileNotFoundException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}// 创建文件输出流。
		catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}finally{
			if(fileOutStream!=null){//关闭流
				try {
					fileOutStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bl;
	}

	
 /** 
  * 反格式化byte 
  *  
  * @param s 
  * @return 
  */  
 public static byte[] hex2byte(String s) {  
     byte[] src = s.toLowerCase().getBytes();  
     byte[] ret = new byte[src.length / 2];  
     for (int i = 0; i < src.length; i += 2) {  
         byte hi = src[i];  
         byte low = src[i + 1];  
         hi = (byte) ((hi >= 'a' && hi <= 'f') ? 0x0a + (hi - 'a')  
                 : hi - '0');  
         low = (byte) ((low >= 'a' && low <= 'f') ? 0x0a + (low - 'a')  
                 : low - '0');  
         ret[i / 2] = (byte) (hi << 4 | low);  
     }  
     return ret;  
 }  
   
 /** 
  * 格式化byte 
  *  
  * @param b 
  * @return 
  */  
 public static String byte2hex(byte[] b) {  
     char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',  
             'B', 'C', 'D', 'E', 'F' };  
     char[] out = new char[b.length * 2];  
     for (int i = 0; i < b.length; i++) {  
         byte c = b[i];  
         out[i * 2] = Digit[(c >>> 4) & 0X0F];  
         out[i * 2 + 1] = Digit[c & 0X0F];  
     }  
   
     return new String(out);  
 }  
 
	//验证图片格式是否正确（字节流）
	public static boolean validateImageType(byte[] mapObj) throws IOException {
		boolean resultFlag = false;
		ByteArrayInputStream bais = null;
		MemoryCacheImageInputStream mcis = null;
		try {
			bais = new ByteArrayInputStream(mapObj);
			mcis = new MemoryCacheImageInputStream(bais);
			Iterator<javax.imageio.ImageReader> itr = ImageIO.getImageReaders(mcis);
			while (itr.hasNext()) {
				ImageReader reader = (ImageReader) itr.next();
				String imageName = reader.getClass().getSimpleName();
				if (imageName != null
						&& ("GIFImageReader".equals(imageName)
						 || "JPEGImageReader".equals(imageName)
						 || "PNGImageReader".equals(imageName) 
						 || "BMPImageReader".equals(imageName))) {
					resultFlag = true;
				}
			}
		} finally {
			// 关闭流
			bais.close();
			mcis.close();
		}
		return resultFlag;
	}
	public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	public static void saveImageAsJpg(String fromFileStr, String saveToFileStr, int width, int hight) throws Exception {
		BufferedImage srcImage;
		String imgType = "JPEG";
		if (fromFileStr.toLowerCase().endsWith(".png")) {
			imgType = "PNG";
		}
		File saveFile = new File(saveToFileStr);
		File fromFile = new File(fromFileStr);
		srcImage = ImageIO.read(fromFile);
		if (width > 0 || hight > 0) {
			srcImage = resize(srcImage, width, hight);
		}
		ImageIO.write(srcImage, imgType, saveFile);

	}
	//验证图片格式是否正确（字符流）
	public static boolean validateImageType(InputStream input) throws IOException {
		return validateImageType(IOUtils.toByteArray(input));
	}
	
	public static void main(String args[]) throws Exception {
		byte[] b = null;

		File file = new File("d://mp3详细设计-1.jpg");
		BufferedImage src;
		try {
//			src = javax.imageio.ImageIO.read(file);
//			b = new ImageUtil().imageToBytes(src, "jpeg");
			new ImageUtil().saveImageAsJpg("C:/Users/admin/Desktop/head_image_default.png", "C:/Users/admin/Desktop/head_image_default200.png", 200, 200);
//			System.out.println("b.length=======" + b.length);
			// for(int i=0;i<b.length;i++)
			// {
			// // System.out.println(b[i]);
			// }
			// new ImageByte().bytesToImage(b);
		} catch (IOException e) {
			e.printStackTrace();
		} // 构造Image对象
//		if (new ImageUtil().byteToImage(b, "2")){
//			System.out.println("文件从流中成功读出");
//			String FilePath = "D://111.jpg";
//			ImageUtil.byteToImage(b, FilePath);
//		}
//		else
//			System.out.println("失败");

	}
}