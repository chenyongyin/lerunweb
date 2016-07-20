package com.lerun.service;

import java.sql.SQLException;
import java.util.List;

import com.lerun.bean.ResponseObject;
import com.lerun.dao.LunboTableDao;
import com.lerun.dao.VideoTableDao;
import com.lerun.model.LunboTable;
import com.lerun.model.VideoTable;
import com.lerun.utils.GsonTools;

/**
 * @Project: LeRun
 * @Author: wschenyongyin
 * @Date: 2016年7月20日
 * @explain:
 * @TestState:AllSucess
 */

public class LunBoService {
	LunboTableDao lunboDao = new LunboTableDao();
	VideoTableDao videoDao = new VideoTableDao();

	ResponseObject response;
	String result = null;

	public LunBoService() {
		// TODO Auto-generated constructor stub
	}

	// 获取图片轮播信息
	public String getImageData() throws SQLException {
		List<LunboTable> imagedata = lunboDao.QueryLunbo();
		if (imagedata != null && imagedata.size() != 0) {
			response = new ResponseObject(1, imagedata);
			result = GsonTools.createJsonString(response);
		} else {
			response = new ResponseObject(0, "获取数据失败");
			result = GsonTools.createJsonString(response);
		}

		return result;

	}

	// 获取视频轮播信息
	public String getVideoData() throws SQLException {
		List<VideoTable> videodata = videoDao.QueryVideo();
		if (videodata != null && videodata.size() != 0) {
			response = new ResponseObject(1, videodata);
			result = GsonTools.createJsonString(response);
		} else {
			response = new ResponseObject(0, "获取数据失败");
			result = GsonTools.createJsonString(response);
		}

		return result;

	}
	
	public static void main(String args[]) throws SQLException{
		LunBoService service=new LunBoService();
		String result=service.getImageData();
		System.out.println(result);
	}

}
