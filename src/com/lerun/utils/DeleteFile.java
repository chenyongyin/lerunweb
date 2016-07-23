package com.lerun.utils;

import java.io.File;

/**
 * @Project: LeRun
 * @Author: wschenyongyin
 * @Date: 2016年7月22日
 * @explain:
 * @TestState:
 */

public class DeleteFile {

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除旧头像成功！");
				return true;
			} else {
				System.out.println("删除旧头像失败！");
				return false;
			}
		} else {
			System.out.println("用户头像" + fileName + "不存在！，删除失败");
			return false;
		}
	}

	public static void main(String[] args) {
//		String upload =ContentCommon.FileName;
//		String upload ="D:\apache-tomcat-7.0.70\webapps\LeRun\image\com_droid.png";
//		String fileName=upload + "image/"+"imgAv0C569i3CQh.jpg";
//		deleteFile(fileName);
//		System.out.println("upload:"+upload);
	}

}
