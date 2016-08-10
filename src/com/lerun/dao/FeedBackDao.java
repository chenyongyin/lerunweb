package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lerun.model.FeedBackTable;
import com.lerun.utils.DBConnection;

/**
 * @Project: LeRun
 * @Author: wschenyongyin
 * @Date: 2016年8月4日
 * @explain: 对反馈表的操作
 * @TestState:
 */

public class FeedBackDao {

	DBConnection DB = new DBConnection();
	Connection conn = null;

	public FeedBackDao() {
		conn = DB.getConnection();
	}

	// 用户反馈
	public int UserFeedback(String feedback_content, String user_id,
			String user_telphone) {
		int result = 0;
		String sql = "insert into feedbackTable(user_id,user_telphone,feedback_content,process_state)values('"
				+ user_id
				+ "','"
				+ user_telphone
				+ "','"
				+ feedback_content
				+ "','0')";

		Statement st;
		try {
			st = conn.createStatement();
			result = st.executeUpdate(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	// 获取所有反馈信息
	public List<FeedBackTable> getAllFeedBackInfo() throws SQLException {

		String sql = "select * from feedbackTable";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<FeedBackTable> list = new ArrayList<FeedBackTable>();
		while (rs.next()) {
			FeedBackTable bean = new FeedBackTable();
			bean.setFeedback_content(rs.getString("feedback_content"));
			bean.setFeedback_id(rs.getInt("feedback_id"));
			bean.setFeedback_time(rs.getString("feedback_time"));
			bean.setUser_telphone(rs.getString("user_telphone"));
			bean.setUser_id(rs.getString("user_id"));
			bean.setProcess_state(rs.getInt("process_state"));

			list.add(bean);

		}
		return list;

	}

	// 获取未处理的反馈信息
	public List<FeedBackTable> getNoProcessFeedBackInfo() throws SQLException {

		String sql = "select * from feedbackTable where process_state='0'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<FeedBackTable> list = new ArrayList<FeedBackTable>();
		while (rs.next()) {
			FeedBackTable bean = new FeedBackTable();
			bean.setFeedback_content(rs.getString("feedback_content"));
			bean.setFeedback_id(rs.getInt("feedback_id"));
			bean.setFeedback_time(rs.getString("feedback_time"));
			bean.setUser_telphone(rs.getString("user_telphone"));
			bean.setUser_id(rs.getString("user_id"));
			bean.setProcess_state(rs.getInt("process_state"));

			list.add(bean);

		}
		return list;

	}

	// 更新反馈的处理状态
	public int updateProcessState(int feedback_state) throws SQLException {
		String sql = "update feedbackTable set process='1' where feedback_id='"
				+ feedback_state + "'";
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);

		return result;
	}

	public static void main(String[] args) {
		FeedBackDao dao = new FeedBackDao();
		int result = dao.UserFeedback("sssssssss", "1234888", "876522");
		System.out.println("jieguo:" + result);
	}

}
