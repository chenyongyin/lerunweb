package com.lerun.service;

import java.sql.SQLException;
import java.util.List;

import com.lerun.dao.OrderInfoDao;
import com.lerun.dao.UserInfoDao;
import com.lerun.model.LeRun;
import com.lerun.model.UserInfo;
import com.lerun.utils.JsonTools;

/**
 * action: 用户管理用户信息的操作
 * 
 * @author wschenyongyin
 * 
 */
public class UserService {
	UserInfoDao dao = new UserInfoDao();
	OrderInfoDao orderDao = new OrderInfoDao();
	UserInfo info = new UserInfo();

	public UserService() {
	}

	// 用户注册
	public int userRegister(String user_id, String user_pwd)
			throws SQLException {
		int flag = 0;
		// 先查询用户是否已经存在
		info = dao.QueryInfo(user_id);
		// 如果用户不存在则进行注册
		if (info == null) {
			flag = dao.Register(user_id, user_pwd);
			System.out.println("注册成功");
		} else {
			System.out.println("用户已存在");
		}
		return flag;
	}

	// 用户登录
	public int userLog(String user_pwd, String user_id) throws SQLException {
		int flag = 1;
		/**
		 * 1、判断用户是否存在 2、判断用户密码是否正确 3、更新用户的登陆状态
		 */

		info = dao.QueryInfo(user_id);
		if (info == null) {
			flag = 0;
		} else if (!user_pwd.equals(info.getUser_pwd())) {
			System.out.println("后台用户密码:" + info.getUser_pwd());
			System.out.println("客户端传过来的密码:" + user_pwd);
			flag = 0;
		} else {
			// 更新用户登陆状态
			info.setUpdate_type("user_state");
			info.setUpdate_values("1");
			info.setUser_id(user_id);
			flag = dao.upDateInfo(info);
		}

		return flag;
	}

	// 用户注销
	public int userLogout(String user_id) throws SQLException {
		int flag = 0;
		info.setUpdate_type("user_state");
		info.setUpdate_values("0");
		info.setUser_id(user_id);
		if (dao.upDateInfo(info) == 1) {
			flag = 1;
		}
		return flag;
	}

	// 查询用户的信息
	public String QueryInfo(String user_id) throws SQLException {

		if (dao.QueryInfo(user_id) != null) {
			String result = JsonTools.createJsonString("result", dao
					.QueryInfo(user_id));
			return result;

		}
		return "failure";
	}

	// 更新用户信息
	public int upDateInfo(UserInfo info) throws SQLException {
		int flag = 0;
		if (dao.upDateInfo(info) == 1) {
			flag = 1;
		}
		return flag;
	}

	// 用户查询自己参与的乐跑活动
	public List<LeRun> QueryLerun(String user_id) throws SQLException {
		// flag为1时说明视图已经存在 为0 时不存在
		List<LeRun> list;
		int flag = orderDao.QueryViewExist(user_id);

		if (flag == 1) {
			list = orderDao.QueryLerunView(user_id);
			System.out.println("视图已经存在");
			return list;
		} else if (flag == 0) {
			// 先创建视图 再查询视图

			if (orderDao.createView(user_id) == 0) {
				System.out.println("视图创建成功");
				list = orderDao.QueryLerunView(user_id);
				return list;
			} else {
				return null;
			}

		}

		return null;
	}

	public static void main(String[] args) throws SQLException {
		UserService service = new UserService();
		// service.userRegister("123", "admin");
		List<LeRun> list = service.QueryLerun("123");
		System.out.println(list.size());

	}

}
