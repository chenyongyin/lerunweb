package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.bean.GeneralBean;
import com.lerun.model.LeRun;
import com.lerun.model.OrderInfo;
import com.lerun.service.UserService;
import com.lerun.utils.DBConnection;

public class OrderInfoDao {
	Connection conn = null;
	DBConnection DB = new DBConnection();

	public OrderInfoDao() {
		this.conn = DB.getConnection();
	}

	// 查询用户是否已经报过名
	public int checkSignUp(int lerun_id, String user_id) throws SQLException {
		String sql = "select * from orderTable where lerun_id='" + lerun_id
				+ "' and user_id='" + user_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			return 1;
		}
		return 0;
	}

	// 用户参与乐跑活动
	public int SignUpLeRun(int lerun_id, String user_id) throws SQLException {
		String sql = "insert into orderTable(lerun_id,user_id,sign_state,charge_state)values('"
				+ lerun_id + "','" + user_id + "','0','0')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 用户付款成功 更改信息
	public int paySuccess(String QRcode_Path, String user_id, int lerun_id)
			throws SQLException {
		String sql = "update orderTable set charge_state='1',sign_barcode='"
				+ QRcode_Path
				+ "',pay_time=(select CONVERT(varchar(20), getdate(), 120)) where user_id='"
				+ user_id + "' and lerun_id='" + lerun_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);

		return result;

	}

	// 返回给志愿者端的二维码信息
	public GeneralBean returnVoluntaryInfo(int lerun_id, String user_id)
			throws SQLException {

		String sql = "";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			GeneralBean bean = new GeneralBean();
			bean.setLerun_title(rs.getString("lerun_title"));
			bean.setLerun_id(rs.getInt("lerun_id"));
			bean.setLerun_charge(rs.getString("lerun_charge"));
			bean.setUser_id(rs.getString("user_id"));
			bean.setPay_time(rs.getString("pay_time"));
			bean.setSign_state(rs.getString("sign_state"));

			return bean;

		}

		return null;
	}

	// 用户签到
	public int SignIn(String user_id, int lerun_id) throws SQLException {
		String sql = "update orderTable set sign_state='1',sign_time=(select CONVERT(varchar(20), getdate(), 120)) where user_id='"
				+ user_id + "' and lerun_id='" + lerun_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);

		return result;

	}

	// 先判断视图是否存在
	public int QueryViewExist(String user_id) throws SQLException {
		int flag = 0;
		String sql = "SELECT * FROM sys.views WHERE name='viewlerun_" + user_id
				+ "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		System.out.println(rs);
		while (rs.next()) {
			System.out.println("视图已经存在");
			flag = 1;
			
		}
		return flag;
	}

	// 创建视图
	public int createView(String user_id) throws SQLException {
		String sql = "create view viewlerun_"
				+ user_id
				+ " as select lerun_id,lerun_title,lerun_poster,lerun_type,lerun_time,lerun_state from lerunTable where lerunTable.lerun_id in(select orderTable.lerun_id from orderTable,lerunTable where orderTable.lerun_id=lerunTable.lerun_id and user_id='"
				+ user_id + "')";
		Connection conn = DB.getConnection();

		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 查询视图
	public List<LeRun> QueryLerunView(String user_id) throws SQLException {
		List<LeRun> list = new ArrayList<LeRun>();
		String sql = "select * from viewlerun_" + user_id + "";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_type(rs.getString("lerun_type"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(rs.getString("lerun_state"));
			lerun.setLerun_id(rs.getString("lerun_id"));

			list.add(lerun);

		}
		DB.closeAll(rs, st, conn);
		return list;

	}

	// 查询所有参与活动的用户
	public List<OrderInfo> QueryAllOrder() throws SQLException {
		String sql = "select * from orderTable";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		while (rs.next()) {
			OrderInfo info = new OrderInfo();
			info.setCharge_state(rs.getString("charge_state"));
			info.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			info.setOrder_num(Integer.parseInt(rs.getString("order_num")));
			info.setPay_time(rs.getString("pay_time"));
			info.setSign_barcode(rs.getString("sign_barcode"));
			info.setSign_state(rs.getString("sign_state"));
			info.setSign_time(rs.getString("sign_time"));
			info.setSignup_time(rs.getString("signup_time"));
			info.setUser_id(rs.getString("user_id"));
			list.add(info);
			
		}
		DB.closeAll(rs, st, conn);
		return list;
	}

	// 查询活动参与的用户
	public List<OrderInfo> QueryLerunOrder(int lerun_id) throws SQLException {
		String sql = "select * from orderTable where lerun_id='" + lerun_id
				+ "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		while (rs.next()) {
			OrderInfo info = new OrderInfo();
			info.setCharge_state(rs.getString("charge_state"));
			info.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			info.setOrder_num(Integer.parseInt(rs.getString("order_num")));
			info.setPay_time(rs.getString("pay_time"));
			info.setSign_barcode(rs.getString("sign_barcode"));
			info.setSign_state(rs.getString("sign_state"));
			info.setSign_time(rs.getString("sign_time"));
			info.setSignup_time(rs.getString("signup_time"));
			info.setUser_id(rs.getString("user_id"));
			list.add(info);
			
		}
		DB.closeAll(rs, st, conn);
		return list;
	}

	// 统计参与用户的总数量

	// 统计单个活动用户参与的数量

	// 统计已经成功报名的用户并且付款的数量

	// 统计已经成功报名的用户但未付款的数量

	// 查询未付款的用户

	// 统计总的金额

	// 统计单个活动的金额

	public static void main(String[] args) throws SQLException {
		OrderInfoDao dao = new OrderInfoDao();
		 dao.QueryViewExist("1234");
		// System.out.println(dao.createView("1234567"));
//		System.out.println(dao.checkSignUp(999, "1235"));

	}

}
