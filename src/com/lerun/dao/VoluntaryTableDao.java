package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.lerun.model.VoluntaryTable;
import com.lerun.utils.DBConnection;
import com.lerun.utils.RandomString;

/**
 *@Author: wschenyongyin
 *@Date: 2016-7-15
 *@explain:自愿者表的操作
 *@TestState:all success
 */
public class VoluntaryTableDao {

	Connection conn = null;
	DBConnection DB = new DBConnection();

	public VoluntaryTableDao() {
		this.conn = DB.getConnection();
	}

	// 插入自愿者账号密码
	public int insertVoluntary(int Voluntary_id, String Voluntary_pwd)
			throws SQLException {
		String sql = "insert into voluntaryTable(Voluntary_id,Voluntary_pwd)values('"
				+ Voluntary_id + "','" + Voluntary_pwd + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		return result;

	}

	// 删除账号密码
	public int deleteVoluntary(String voluntary_id) throws SQLException {
		String sql = "delete from voluntaryTable where voluntary_id='"
				+ voluntary_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		return result;
	}

	// 删除所有自愿者账号密码
	public int deleteAll() throws SQLException {
		String sql = "delete from voluntaryTable";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		return result;

	}

	// 自愿者登陆
	public int voluntaryLogin(int voluntary_id, String voluntary_pwd)
			throws SQLException {
		int flag = 0;
		String sql = "select * from voluntaryTable where voluntary_id='"
				+ voluntary_id + "' and voluntary_pwd='" + voluntary_pwd + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			flag = 1;
		}

		return flag;
	}

	// 后台请求随机生成自愿者账号密码

	public List<VoluntaryTable> crateAccount(int count) throws SQLException {
		List<VoluntaryTable> list = new ArrayList<VoluntaryTable>();
		for (int i = 0; i < count; i++) {
			int id = new Random().nextInt(999999);
			String pwd = RandomString.getRandomString(6);
			VoluntaryTableDao dao = new VoluntaryTableDao();
			if (dao.insertVoluntary(id, pwd) == 1) {
				VoluntaryTable info = new VoluntaryTable();
				info.setVoluntary_id(id);
				info.setVoluntary_pwd(pwd);
				list.add(info);
			} else {
				return null;
			}

		}

		return list;
	}

	public static void main(String[] args) throws SQLException {
		VoluntaryTableDao dao = new VoluntaryTableDao();
		// int id = new Random().nextInt(999999);
		// String pwd = RandomString.getRandomString(6);
		// System.out.println(id + "----" + pwd);
		// dao.insertVoluntary(id, pwd);
		//
		// System.out.println(pwd);
		//		
		// System.out.println("登陆状态:"+dao.voluntaryLogin(id, pwd));

//		List<VoluntaryTable> list = dao.crateAccount(3);
//		System.out.println(list.size());
		// dao.deleteAll();

	}

}
