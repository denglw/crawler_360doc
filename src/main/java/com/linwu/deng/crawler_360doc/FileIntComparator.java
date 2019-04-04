/**
 * @Title: FileIntComparator.java
 * @Desc: TODO
 * @Package: com.linwu.deng.crawler_360doc
 * @author: denglw
 * @date: 2019年4月4日 下午4:05:09
 * @version 1.0
 */

package com.linwu.deng.crawler_360doc;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @ClassName: FileIntComparator
 * @Desc: TODO
 * @author: denglw
 * @email: 892253193@qq.com
 * @date: 2019年4月4日 下午4:05:09
 * @version 1.0
 */
public class FileIntComparator implements Comparator<File>{

	public int compare(File o1, File o2) {
		int i1 = Integer.parseInt(o1.getName().substring(0,o1.getName().lastIndexOf('.')));
		int i2 = Integer.parseInt(o2.getName().substring(0, o2.getName().lastIndexOf('.')));
		return i1 - i2;
	}

	public static void main(String[] args) {
		 File dir = new File("F:/概率论与数理统计");
         ArrayList<File> files = new ArrayList<File>(Arrays.asList(dir.listFiles()));
         Collections.sort(files);
         for(File file:files){
         	System.out.println(file.getName().substring(0,file.getName().lastIndexOf('.')));
         }
	}


}

