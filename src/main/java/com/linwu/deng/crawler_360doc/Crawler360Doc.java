/**
 * @Title: Crawler360Doc.java
 * @Desc: TODO
 * @Package: com.linwu.deng.crawler_360doc
 * @author: denglw
 * @date: 2019年4月4日 下午4:02:59
 * @version 1.0
 */

package com.linwu.deng.crawler_360doc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.imageio.ImageIO;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

/**
 * @ClassName: Crawler360Doc
 * @Desc: TODO
 * @author: denglw
 * @email: 892253193@qq.com
 * @date: 2019年4月4日 下午4:02:59
 * @version 1.0
 */
public class Crawler360Doc {

	
	/**
	 * 
	 * @param url 下载地址
	 * @param file 文件名称（文件目录+文件名称） imagePathFile
	 */
	
	public void downLoadFile(URL url,File file){
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			URLConnection conn = url.openConnection();
			conn.connect();
			is = conn.getInputStream();
			byte[] bytes = new byte[10240];
			fos = new FileOutputStream(file);
			int temp = 0;
			while( (temp = is.read(bytes)) != -1){
				fos.write(bytes,0,temp);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(is != null)
					is.close();
				if(fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * 
	 * @param imageFolderPath 输入图片文件目录
	 * @param pdfPathFile 输出pdf文件名称（带目录+文件名称）
	 */
	public void mergeImgToPdf(String imageFolderPath, String pdfPathFile) {
    	// 创建文档
        Document doc = new Document(null, 0, 0, 0, 0);
        // 输入流
        FileOutputStream fos = null;
    	try {
            // 图片地址
            String imagePath = null;
            // 问题解决 https://blog.csdn.net/xujingcheng123/article/details/78997819/
            fos = new FileOutputStream(pdfPathFile);
            // 写入PDF文档
            PdfWriter.getInstance(doc, fos);
            // 实例化图片
            Image image = null;
            // 读取图片流
            BufferedImage bi = null;
            // 获取图片文件夹对象并对文件列表按整数排序
            File dir = new File(imageFolderPath);
            ArrayList<File> files = new ArrayList<File>(Arrays.asList(dir.listFiles()));
//            files.sort(new FileIntComparator());
            Collections.sort(files);
            // 循环获取图片文件夹内的图片
            for (File file : files) {
                if (file.getName().endsWith(".png")
                        || file.getName().endsWith(".jpg")
                        || file.getName().endsWith(".gif")
                        || file.getName().endsWith(".jpeg")
                        || file.getName().endsWith(".tif")) {
                	//图片路径
                	imagePath = imageFolderPath + file.getName();
                    // 读取图片流
                	bi = ImageIO.read(new File(imagePath));
                    // 根据图片大小设置文档大小
                    doc.setPageSize(new Rectangle(bi.getWidth(), bi
                            .getHeight()));
                    // 实例化图片
                    image = Image.getInstance(imagePath);
                    // 添加图片到文档
                    doc.open();
                    doc.add(image);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
			try {
				if(doc != null)
	        		doc.close();
				if(fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }


}
