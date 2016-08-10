package com.lerun.service;

import java.sql.SQLException;

import com.lerun.bean.ResponseObject;
import com.lerun.dao.FeedBackDao;
import com.lerun.dao.VersionTableDao;
import com.lerun.model.VersionTable;
import com.lerun.utils.GsonTools;

/**
 * @Project: LeRun
 * @Author: wschenyongyin
 * @Date: 2016年8月4日
 * @explain:用于客户端版本更新，用户反馈的操作
 * @TestState:
 */

public class SystemService {
	FeedBackDao FBdao = new FeedBackDao();
	VersionTableDao vDao = new VersionTableDao();

	// 检查版本
	public String checkVersion(String version_num) throws SQLException {
		VersionTable version = vDao.getVersion(version_num);
		ResponseObject response = new ResponseObject();
		if (version == null) {
			response.setDatas("已经是最新版本");
			response.setStage(0);

		} else {
			response.setDatas(version);
			response.setStage(1);
		}
		String result = GsonTools.createJsonString(response);
		return result;

	}

	// 发布新的版本
	public String ReleaseVersion(String version_number, String update_content,
			String update_url) throws SQLException {
		int flag = vDao.ReleaseVersion(version_number, update_content,
				update_url);
		ResponseObject response = new ResponseObject();

		if (flag == 1) {
			response.setDatas("发布成功");
			response.setStage(1);
		} else {
			response.setDatas("发布失败");
			response.setStage(0);
		}
		String result = GsonTools.createJsonString(response);
		return result;
	}
	//用户反馈
	public String FeedBack(String feedback_content, String user_id,
			String user_telphone){
		
		int flag=FBdao.UserFeedback(feedback_content,user_id,user_telphone);
		ResponseObject response = new ResponseObject();
		if(flag==1){
			response.setDatas("反馈成功");
			response.setStage(1);
		}else{
			response.setDatas("反馈失败");
			response.setStage(0);
		}
		
		return GsonTools.createJsonString(response);
	}
	
	
	

	public static void main(String args[]) throws SQLException {
		SystemService service = new SystemService();
		
		System.out.println(service.checkVersion("v1.0"));

	}


}
