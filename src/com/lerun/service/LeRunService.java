package com.lerun.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.lerun.dao.LeRunDao;
import com.lerun.dao.OrderInfoDao;
import com.lerun.model.LeRun;
import com.lerun.utils.JsonTools;

public class LeRunService {
	LeRunDao dao = new LeRunDao();
	int flag = 0;
	String result = null;
	OrderInfoDao orderDao = new OrderInfoDao();

	public LeRunService() {
	}

	// 发布活动
	public int ReleaseLerun(LeRun lerun) throws SQLException {
		if (dao.Release(lerun) == 1) {
			flag = 1;
		}
		return flag;
	}

	// 查询所有未结束的活动
	public String QueryAll() throws SQLException {
		List<LeRun> data = dao.QueryAllLerun();
		if (data != null&&!data.isEmpty()) {
			result = JsonTools.createJsonString("result", data);
		}else{
			result="empty";
		}

		return result;
	}

	// 查看活动详细信息
	public String QueryDetail(int lerun_id) throws SQLException {
		LeRun data = dao.QueryDetailLerun(lerun_id);
		if (data != null) {
			result = JsonTools.createJsonString("result", data);
		}else{
			result="empty";
		}

		return result;
	}

	// 更新活动
	public int update(int lerun_id, String update_type, String update_values)
			throws SQLException {
		int result = dao.updateLeRun(lerun_id, update_type, update_values);
		if (result == 1) {
			flag = 1;
		}
		return flag;
	}

	// 用户报名参加活动 0表示报名失败 1表示用户已经报名 2表示报名成功
	public int SignUp(int lerun_id, String user_id) throws SQLException {
		int result = orderDao.checkSignUp(lerun_id, user_id);
		if (result == 0) {
			flag = 1;
		} else if (result == 1) {
			int result2 = orderDao.SignUpLeRun(lerun_id, user_id);
			if (result == 1) {
				flag = 2;
			}
		}
		return flag;
	}

	// 用户付款成功
	public int paySuccess(String QRcode_Path, int lerun_id, String user_id)
			throws SQLException {
		int result = orderDao.paySuccess(QRcode_Path, user_id, lerun_id);
		if (result == 1) {
			flag = 1;
		}
		return flag;

	}

	// 用户查看自己参加的乐跑活动
	public String QueryPersonalLerun(String user_id) throws SQLException {
		List<LeRun> list = new ArrayList<LeRun>();

		int flag = orderDao.QueryViewExist(user_id);
		if (flag == 1) {
			list = orderDao.QueryLerunView(user_id);
			result = JsonTools.createJsonString("result", list);
		} else if (flag == 0) {
			int flag2 = orderDao.createView(user_id);
			if (flag2 == 0) {
				list = orderDao.QueryLerunView(user_id);
				result = JsonTools.createJsonString("result", list);
			} else {
				result = "failure";
			}
		}else{
			result = "failure";
		}
		return result;

	}

	public static void main(String[] args) throws SQLException {
		LeRunService service = new LeRunService();
		String result = service.QueryAll();

	}

}
