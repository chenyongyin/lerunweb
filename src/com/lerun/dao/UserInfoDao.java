package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lerun.model.UserInfo;
import com.lerun.utils.ContentCommon;
import com.lerun.utils.DBConnection;
import com.lerun.utils.DeleteFile;

/**
 * action：对用户的操作
 * 
 * @author wschenyongyin
 * 
 */
public class UserInfoDao {

	private Connection conn = null;
	DBConnection DB = new DBConnection();

	public UserInfoDao() {
		this.conn = DB.getConnection();
	}

	// 用户注册

	public int Register(String user_id, String user_pwd) throws SQLException {
		String sql = "insert into userTable(user_id,user_pwd) values('"
				+ user_id + "','" + user_pwd + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 用户修改信息
	public int upDateInfo(UserInfo info) throws SQLException {
		String sql = "update userTable set " + info.getUpdate_type() + "='"
				+ info.getUpdate_values() + "' where user_id='"
				+ info.getUser_id() + "'";

		if (info.getUpdate_type().equals("user_header")) {
			String user_header = getHeader(info.getUser_id());
			String fileName = ContentCommon.FileName + user_header;
			DeleteFile.deleteFile(fileName);
		}
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		// 如果是更新用户头像 则执行删除用户旧的头像
		

		DB.closeAll(null, st, conn);
		return result;
	}

	// 用户登录

	public String Login(String user_id) throws SQLException {
		String sql = "select user_pwd from userTable where user_id=" + user_id
				+ "";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			String user_pwd = rs.getString("user_pwd");
			return user_pwd;
		}
		DB.closeAll(rs, st, conn);
		return null;
	}

	// 查询用户信息
	public UserInfo QueryInfo(String user_id) throws SQLException {
		String sql = "select * from userTable where user_id=" + user_id + " ";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			UserInfo info = new UserInfo();
			info.setUser_address(rs.getString("user_address"));
			info.setUser_bankid(rs.getString("user_bankid"));
			info.setUser_email(rs.getString("user_email"));
			info.setUser_fullname(rs.getString("user_fullname"));
			info.setUser_header(rs.getString("user_header"));
			info.setUser_health(rs.getString("user_health"));
			info.setUser_height(rs.getString("user_height"));
			info.setUser_id(rs.getString("user_id"));
			info.setUser_identity(rs.getString("user_identity"));
			info.setUser_level(rs.getString("user_level"));
			info.setUser_name(rs.getString("user_name"));
			info.setUser_otherid(rs.getString("user_otherid"));
			info.setUser_pwd(rs.getString("user_pwd"));
			info.setUser_sex(rs.getString("user_sex"));
			info.setUser_state(rs.getString("user_state"));
			info.setUser_weight(rs.getString("user_weight"));
			info.setUser_sign(rs.getString("user_sign"));
			info.setUser_phone(rs.getString("user_phone"));

			return info;

		}
		DB.closeAll(rs, st, conn);
		return null;
	}

	private String getHeader(String user_id) throws SQLException {
		String sql = "select user_header from userTable where user_id="
				+ user_id + "";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			String user_header = rs.getString("user_header");
			return user_header;
		}
		DB.closeAll(rs, st, conn);
		return null;

	}

	public static void main(String[] args) throws SQLException {
		UserInfoDao dao = new UserInfoDao();
		// dao.Register("123", "admin");
		UserInfo info = new UserInfo();
		info.setUpdate_type("user_name");
		info.setUpdate_values("2222");
		// info.set
		// String result=dao.Login("125");
		// System.out.println("r"+result);

	}
	/**
	 * 测试内容 注册：1
	 */

}
