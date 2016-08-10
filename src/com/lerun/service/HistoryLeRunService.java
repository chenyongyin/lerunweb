package com.lerun.service;

import java.sql.SQLException;
import java.util.List;

import com.lerun.bean.ResponseObject;
import com.lerun.dao.LeRunDao;
import com.lerun.dao.LeRunEvaluateTableDao;
import com.lerun.model.LeRun;
import com.lerun.model.LeRunEvaluateTable;
import com.lerun.utils.GsonTools;

/**
 * @Project: LeRun
 * @Author: wschenyongyin
 * @Date: 2016年8月10日
 * @explain:
 * @TestState:
 */

public class HistoryLeRunService {
	LeRunDao dao = new LeRunDao();
	LeRunEvaluateTableDao evdao = new LeRunEvaluateTableDao();

	// 查看历史lerun活动
	public String HistoryLerun(int lerun_theme) throws SQLException {
		List<LeRun> data = dao.QueryHistoryLerun(lerun_theme);
		ResponseObject responese = new ResponseObject();
		if (data != null && !data.isEmpty()) {
			responese.setDatas(data);
			responese.setStage(1);
		} else {
			responese.setDatas("获取数据失败或内容为空");
			responese.setStage(0);
		}

		return GsonTools.createJsonString(responese);

	}

	// 查看历史活动详情
	public String HistoryLerunDetail(int lerun_id) throws SQLException {
		LeRun data = dao.HistoryDetail(lerun_id);
		ResponseObject responese = new ResponseObject();
		if (data != null) {
			responese.setDatas(data);
			responese.setStage(1);
		} else {
			responese.setDatas("获取数据失败");
			responese.setStage(0);
		}

		return GsonTools.createJsonString(responese);

	}



	// 获取活动评论
	public String getLerunEvaluate(int lerun_id) throws SQLException {
		List<LeRunEvaluateTable> datas = evdao.QueryLerunEvaluate(lerun_id);
		ResponseObject responese = new ResponseObject();
		if (datas != null && !datas.isEmpty()) {
			responese.setDatas(datas);
			responese.setStage(1);
		} else {
			responese.setDatas("获取数据失败或内容为空");
			responese.setStage(0);
		}
		return GsonTools.createJsonString(responese);

	}

	public static void main(String[] args) throws SQLException {
		HistoryLeRunService service = new HistoryLeRunService();
		System.out.println(service.HistoryLerunDetail(999));

	}

}
