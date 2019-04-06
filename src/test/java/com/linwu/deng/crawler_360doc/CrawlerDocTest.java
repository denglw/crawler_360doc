/**
 * @Title: CrawlerDocTest.java
 * @Desc: TODO
 * @Package: com.linwu.deng.crawler_360doc
 * @author: denglw
 * @date: 2019年4月6日 上午11:42:16
 * @version 1.0
 */

package com.linwu.deng.crawler_360doc;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import com.linwu.deng.crawler_360doc.Crawler360Doc;

/**
 * @ClassName: CrawlerDocTest
 * @Desc: TODO
 * @author: denglw
 * @email: 892253193@qq.com
 * @date: 2019年4月6日 上午11:42:16
 * @version 1.0
 */
public class CrawlerDocTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//参考 https://blog.csdn.net/weixin_44112790/article/details/86775221
//		System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径 
		String currentPath = System.getProperty("user.dir");//user.dir指定了当前的路径 
		String imageFolderPath = currentPath +"/images/" ;
		String pdfPathFile = imageFolderPath +"概率论与数理统计.pdf";
		File filePdf = new File(pdfPathFile);
		if(!filePdf.getParentFile().exists()){
			filePdf.getParentFile().mkdirs();
        }
		Crawler360Doc crawler360Doc = new Crawler360Doc();
		try {
			for(int i=1;i<=141;i++){
				String XXi = String.format("%03d", i);// => "001" 规范化命名文件，合并顺序正确
				URL url = new URL("http://html11.360doc.com/2017/1130/17/708684095_"+i+".Jpeg");
				File file = new File(imageFolderPath+XXi+".jpeg");
				crawler360Doc.downLoadFile(url, file);
			}
			crawler360Doc.mergeImgToPdf(imageFolderPath, pdfPathFile);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
