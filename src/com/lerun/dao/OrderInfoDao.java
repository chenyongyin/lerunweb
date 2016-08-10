package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.bean.QrcodeBean;
import com.lerun.bean.imageBean;
import com.lerun.model.LeRun;
import com.lerun.model.OrderInfo;
import com.lerun.utils.DBConnection;

public class OrderInfoDao {
	Connection conn = null;
	DBConnection DB = new DBConnection();

	public OrderInfoDao() {
		this.conn = DB.getConnection();
	}

	// 查询用户是否已经报过名
	public int checkSignUp(int lerun_id, String user_id, String user_telphone)
			throws SQLException {
		String sql = "select * from orderTable where lerun_id='" + lerun_id
				+ "' and user_id='" + user_id + "' and user_telphone='"
				+ user_telphone + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			return 1;
		}
		return 0;
	}

	// 用户参与乐跑活动
	public int SignUpLeRun(OrderInfo info) throws SQLException {
		String sql = "insert into orderTable(lerun_id,user_telphone,lerun_title,user_id,signin_type,personal_name,company_name,certificate_image,charge_state,Check_state,identity_type,identity_card,insurance_state,insurance_id,dress_size,sign_state,payment,user_sex)values('"
				+ info.getLerun_id()
				+ "','"
				+ info.getUser_telphone()
				+ "','"
				+ info.getLerun_title()
				+ "','"
				+ info.getUser_id()
				+ "','"
				+ info.getSignin_type()
				+ "','"
				+ info.getPersonal_name()
				+ "','"
				+ info.getCompany_name()
				+ "','"
				+ info.getCertificate_image()
				+ "','0','0','"
				+ info.getIdentity_type()
				+ "','"
				+ info.getIdentity_card()
				+ "','0','"
				+ info.getInsurance_id()
				+ "','"
				+ info.getDress_size()
				+ "','0','"
				+ info.getPayment()
				+ "','"
				+ info.getUser_sex() + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 用户付款成功 更改信息
	public int paySuccess(String QRcode_Path, int payment, String user_id,
			int lerun_id) throws SQLException {
		String sql = "update orderTable set charge_state='1',sign_barcode='"
				+ QRcode_Path
				+ "',pay_time=(select CONVERT(varchar(20), getdate(), 120)),payment='"
				+ payment
				+ "' ,check_state='1' ,insurance_state='1' where user_id='"
				+ user_id + "' and lerun_id='" + lerun_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);

		return result;

	}

	// 返回给志愿者端的二维码信息
	public OrderInfo returnVoluntaryInfo(int lerun_id, String user_id,String user_telphone)
			throws SQLException {

		String sql = "select * from orderTable,lerunTable where orderTable.lerun_id=lerunTable.lerun_id and user_id='"
				+ user_id + "' and orderTable.lerun_id='" + lerun_id + "' and user_telphone='"+user_telphone+"'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			OrderInfo bean = new OrderInfo();
			bean.setLerun_title(rs.getString("lerun_title"));
			bean.setLerun_id(rs.getInt("lerun_id"));
			bean.setPayment(Integer.parseInt(rs.getString("payment")));
			bean.setUser_id(rs.getString("user_id"));
			bean.setPay_time(rs.getString("pay_time"));
			bean.setSign_state(Integer.parseInt(rs.getString("sign_state")));
			bean.setDress_size(rs.getString("dress_size"));
			bean.setUser_telphone(user_telphone);

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
				+ " as select distinct lerunTable.lerun_id,lerunTable.lerun_title,lerun_address,lerun_poster,lerun_type,lerun_time,lerun_state  from lerunTable ,orderTable where lerunTable.lerun_id=orderTable.lerun_id  and lerunTable.lerun_id in(select distinct(orderTable.lerun_id) from orderTable,lerunTable where orderTable.lerun_id=lerunTable.lerun_id and user_id='"
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
			lerun.setLerun_state(Integer.parseInt(rs.getString("lerun_state")));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			// lerun.setCharge_state(Integer.parseInt(rs.getString("charge_state")));

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
			info.setCharge_state(Integer.parseInt(rs.getString("charge_state")));
			info.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			info.setOrder_num(Integer.parseInt(rs.getString("order_num")));
			info.setPay_time(rs.getString("pay_time"));
			info.setSign_barcode(rs.getString("sign_barcode"));
			info.setSign_state(Integer.parseInt(rs.getString("sign_state")));
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
			info.setCharge_state(Integer.parseInt(rs.getString("charge_state")));
			info.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			info.setOrder_num(Integer.parseInt(rs.getString("order_num")));
			info.setPay_time(rs.getString("pay_time"));
			info.setSign_barcode(rs.getString("sign_barcode"));
			info.setSign_state(Integer.parseInt(rs.getString("sign_state")));
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

	// 精确获取一个报名的二维码
	public String getUserPrivateCode(String user_id,String user_telphone) throws SQLException {
		String sql = "select sign_barcode from orderTable where user_id='"+user_id+"' and user_telphone='"+user_telphone+"' and charge_state='1' order by lerun_id desc";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			String qrcode = rs.getString(1);
			return qrcode;
			
		}
		return null;
	}

	// 获取用户所报名活动的二维码
	public List<QrcodeBean> getAllQrcode(String user_id, int lerun_id)
			throws SQLException {
		String sql = "select sign_barcode,personal_name,sign_state,lerun_title,charge_state,payment from orderTable where user_id='"
				+ user_id + "' and lerun_id='" + lerun_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<QrcodeBean> list = new ArrayList<QrcodeBean>();
		while (rs.next()) {
			QrcodeBean bean = new QrcodeBean();
			bean.setImagePath(rs.getString("sign_barcode"));
			bean.setPersonal_name(rs.getString("personal_name"));
			bean.setSign_state(Integer.parseInt(rs.getString("sign_state")));
			bean.setLerun_title(rs.getString("lerun_title"));
			bean.setCharge_state(Integer.parseInt(rs.getString("charge_state")));
			bean.setPayment(Integer.parseInt(rs.getString("payment")));
			System.out.println("价格:"+bean.getPayment());
//			bean.setLerun_time(rs.getString("lerun_time"));
			list.add(bean);
		}

		return list;
	}

	// 获取未缴费的乐跑
	public List<LeRun> QueryNoPayLerun(String user_id) throws SQLException {
		List<LeRun> list = new ArrayList<LeRun>();
		String sql = "select * from viewlerun_" + user_id
				+ " where charge_state='0'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_type(rs.getString("lerun_type"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(Integer.parseInt(rs.getString("lerun_state")));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			
			list.add(lerun);

		}
		DB.closeAll(rs, st, conn);
		return list;

	}

	public static void main(String[] args) throws SQLException {
		OrderInfoDao dao = new OrderInfoDao();
		// // dao.QueryViewExist("1234");
		// // System.out.println(dao.createView("1234567"));
		// // System.out.println(dao.checkSignUp(999, "1235"));
		// int result=dao.paySuccess("",100, "123", 999);
		// System.out.println(result);
		// if(result==1){
		// System.out.println("ggygy");
		// }else{
		// System.err.println("aaaaa");
		// }

		OrderInfo info = new OrderInfo();
		// 测试报名
		// info.setUser_id("13155822449");
		// info.setLerun_id(999);
		// info.setUser_telphone("13155822449");
		// info.setLerun_title("泡泡卡乐跑");
		// info.setSignin_type(1);
		// info.setPersonal_name("陈永银");
		// info.setCompany_name("天虹");
		// info.setCertificate_image("image/psb.jpg");
		// info.setIdentity_type("1");
		// info.setIdentity_card("360731199304650098");
		// info.setInsurance_id(1);
		// info.setDress_size("L");
		// info.setPayment(88);
		// info.setUser_sex("男");
		// int result = dao.SignUpLeRun(info);

		// 测试付款

		// int result=dao.paySuccess("www.baidu.com", 88, "13155822449", 999);
		// System.out.println("result-->" + result);
//		int result = dao.checkSignUp(999, "13155822449", "13155822449");
//		List<LeRun> list = dao.QueryNoPayLerun("13155822449");
		
		String qrcode=dao.getUserPrivateCode("18270839435", "18270839435");
		
		System.out.println(qrcode);
	}
}
