package com.lerun.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lerun.bean.QrcodeBean;
import com.lerun.bean.ResponseObject;
import com.lerun.dao.LeRunDao;
import com.lerun.dao.OrderInfoDao;
import com.lerun.model.LeRun;
import com.lerun.model.OrderInfo;
import com.lerun.utils.GsonTools;
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
	public String QueryAll(String lerun_province) throws SQLException {
		List<LeRun> data = dao.getLerun(lerun_province);
//		if (data != null && !data.isEmpty()) {
//			ResponseObject object = new ResponseObject();
//			object.setDatas(data);
//			object.setStage(1);
//			result = GsonTools.createJsonString(object);
//		}else{
//			ResponseObject object = new ResponseObject();
//			object.setDatas("没有数据");
//			object.setStage(0);
//			result = GsonTools.createJsonString(object);
//		}
		if (data != null && !data.isEmpty()) {
			result = JsonTools.createJsonString("result", data);
		} else {
			result = "empty";
		}


		return result;
	}

	// 查看结束的活动
	public String QueryEnd() throws SQLException {
		List<LeRun> data = dao.QueryEndLerun();

		if (data != null && !data.isEmpty()) {
			result = JsonTools.createJsonString("result", data);
		} else {
			result = "empty";
		}

		return result;
	}

	// 查看活动详细信息
	public String QueryDetail(int lerun_id) throws SQLException {
		LeRun data = dao.QueryDetailLerun(lerun_id);
		if (data != null) {
			result = JsonTools.createJsonString("result", data);
			return result;
		}

		return "faliure";
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
	public int SignUp(OrderInfo info) throws SQLException {
		int result = orderDao.checkSignUp(info.getLerun_id(),
				info.getUser_id(), info.getUser_telphone());
		// 如果报名类型是免费 则需查看是否还有免费的名额
		if (info.getSignin_type() == 1) {
			dao.getFreeSurplus(info.getLerun_id());

		} else {
		}

		if (result == 0) {
			int result2 = orderDao.SignUpLeRun(info);
			if (result2 == 1) {
				flag = 2;
			}
		} else if (result == 1) {

			flag = 1;
		}
		return flag;
	}

	// 用户付款成功
	public int paySuccess(String QRcode_Path, int payment, int lerun_id,
			String user_id) throws SQLException {
		int result = orderDao.paySuccess(QRcode_Path, payment, user_id,
				lerun_id);
		if (result == 1) {
			flag = 1;
		}
		return flag;

	}

	// 用户查看自己参加的所有乐跑活动
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
		} else {
			result = "failure";
		}
		return result;

	}

	// 查询为付款的乐跑活动
	public String QueryPersonalNoPayLerun(String user_id) throws SQLException {
		List<LeRun> list = new ArrayList<LeRun>();

		int flag = orderDao.QueryViewExist(user_id);
		if (flag == 1) {
			list = orderDao.QueryNoPayLerun(user_id);
			result = JsonTools.createJsonString("result", list);
		} else if (flag == 0) {
			int flag2 = orderDao.createView(user_id);
			if (flag2 == 0) {
				list = orderDao.QueryNoPayLerun(user_id);
				result = JsonTools.createJsonString("result", list);
			} else {
				result = "failure";
			}
		} else {
			result = "failure";
		}
		return result;

	}

	// 获取用户报名的二维码
	public String getQrCode(String user_id, int lerun_id) throws SQLException {
		List<QrcodeBean> list = orderDao.getAllQrcode(user_id, lerun_id);

		if (list != null && !list.isEmpty()) {
			ResponseObject responseObject = new ResponseObject();
			responseObject.setDatas(list);
			responseObject.setStage(1);
			result = GsonTools.createJsonString(responseObject);

		} else {
			ResponseObject responseObject = new ResponseObject();
			responseObject.setDatas("没有数据");
			responseObject.setStage(0);
			result = GsonTools.createJsonString(responseObject);
		}

		return result;
	}

	// 获取用户单张二维码
	public String getPersonQrCode(String user_id, String user_telphone)
			throws SQLException {
		String qrcode = orderDao.getUserPrivateCode(user_id, user_telphone);

		if (qrcode != null && !qrcode.equals("")) {
			ResponseObject responseObject = new ResponseObject();
			responseObject.setDatas(qrcode);
			responseObject.setStage(1);
			result = GsonTools.createJsonString(responseObject);

		} else {
			ResponseObject responseObject = new ResponseObject();
			responseObject.setDatas("该用户未报名乐跑活动");
			responseObject.setStage(0);
			result = GsonTools.createJsonString(responseObject);
		}

		return result;
	}

	public static void main(String[] args) throws SQLException {
		LeRunService service = new LeRunService();
		// String result = service.getQrCode("13155822449",999);
		String result = service.QueryAll("江西");
		System.out.println(result);

	}

}
