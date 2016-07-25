package com.lerun.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@Project: LeRun
 *@Author: wschenyongyin
 *@Date: 2016年7月25日
 *@explain:
 *@TestState:
 */

public class StringUtils {
	
	//获取系统时间
	public static String getDate(){
		// 创建一个时间对象，获取到当前的时间
		Date date = new Date();
		// 设置时间显示格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 将当前时间格式化为需要的类型
		String datetime = sdf.format(date);
		
		return datetime;
	}
	

}
