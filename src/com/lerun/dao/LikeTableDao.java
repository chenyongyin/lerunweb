/**
 *@Project: LeRun
 */
package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.model.UserInfo;
import com.lerun.utils.DBConnection;

/**
 *@Author: wschenyongyin
 *@Date: 2016-7-15
 *@TestState: All suceess
 */
public class LikeTableDao {

	Connection conn = null;
	DBConnection DB = new DBConnection();

	public LikeTableDao() {
		this.conn = DB.getConnection();
	}

	// 查看当前用户是否已经点赞
	public int QueryExistUser(int show_id, String user_id) throws SQLException {
		int flag = 0;
		String sql = "select *from likeTable where show_id='"+show_id+"' and user_id='"+user_id+"'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			flag = 1;
		}
		
		return flag;
	}

	// 点赞
	public int ReleaseLike(String user_id, int show_id, String like_userid)
			throws SQLException {
		String sql = "insert into likeTable(user_id,show_id,like_userid)values('"
				+ user_id + "','" + show_id + "','" + like_userid + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int reuslt = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);

		return reuslt;
	}

	// 取消点赞
	public int CancelLike(String user_id, int show_id) throws SQLException {
		String sql = "delete from likeTable where user_id='" + user_id
				+ "' and show_id='" + show_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int reuslt = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);

		return reuslt;
	}

	// 查看当前show点赞的用户
	public List<UserInfo> QueryLikeUser(int show_id) throws SQLException {

		String sql = "select userTable.user_id,user_name,user_header,like_time from likeTable,userTable where likeTable.user_id=userTable.user_id and show_id='"
				+ show_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<UserInfo> list = new ArrayList<UserInfo>();
		while (rs.next()) {
			UserInfo info = new UserInfo();
			info.setUser_id(rs.getString("user_id"));
			info.setUser_name(rs.getString("user_name"));
			info.setLike_time(rs.getString("like_time"));
			info.setUser_header(rs.getString("user_header"));
			list.add(info);

		}
		DB.closeAll(rs, st, conn);
		return list;
	}

	// 统计点赞的用户数量
	public int countLikeNumber(int show_id) throws SQLException {
		String sql = "select count(user_id) from likeTable where show_id='"
				+ show_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			int amount = rs.getInt(1);
			return amount;
		}
		return 0;
	}

	public static void main(String[] args) throws SQLException {
		LikeTableDao dao = new LikeTableDao();
		// int result=dao.countLikeNumber(999);
		// List<UserInfo> result=dao.QueryLikeUser(999);
//		List<UserInfo> list=dao.QueryLikeUser(1000);
//		int result=dao.ReleaseLike("18270839435", 1002, "13155822449");
//		System.out.println("result:" + result);
		int result=dao.QueryExistUser(1002, "18270839435");
		System.out.println("result:" + result);

	}

}
