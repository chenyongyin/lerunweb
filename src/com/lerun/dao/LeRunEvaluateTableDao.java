package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.model.LeRunEvaluateTable;
import com.lerun.utils.DBConnection;

/**
 * @Project: LeRun
 * @Author: wschenyongyin
 * @Date: 2016年8月10日
 * @explain:
 * @TestState:
 */

public class LeRunEvaluateTableDao {
	DBConnection DB = new DBConnection();
	Connection conn = null;

	public LeRunEvaluateTableDao() {
		conn = DB.getConnection();
	}

	// 用户评价
	public int Evaluate(String user_id, int lerun_id, int evaluate_star,
			String evaluate_content) throws SQLException {
		String sql = "insert into lerunEvaluateTable(user_id,lerun_id,evaluate_star,evaluate_content)values('"
				+ user_id
				+ "','"
				+ lerun_id
				+ "','"
				+ evaluate_star
				+ "','"
				+ evaluate_content + "')";
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);

		return result;
	}

	// 查询lerun的评价内容
	public List<LeRunEvaluateTable> QueryLerunEvaluate(int lerun_id) throws SQLException {
		String sql = "select evaluate_content,evaluate_time,evaluate_time,user_name,user_header,userTable.user_id from lerunEvaluateTable,userTable where lerun_id='"+lerun_id+"' and lerunEvaluateTable.user_id=userTable.user_id ";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LeRunEvaluateTable> list=new ArrayList<LeRunEvaluateTable>();
		while (rs.next()) {
			LeRunEvaluateTable bean = new LeRunEvaluateTable();
			bean.setEvaluate_content(rs.getString("evaluate_content"));
			bean.setEvaluate_time(rs.getString("evaluate_time"));
			bean.setUser_name(rs.getString("user_name"));
			bean.setUser_header(rs.getString("user_header"));
			bean.setUser_id(rs.getString("user_id"));
			bean.setLerun_id(lerun_id);
			list.add(bean);
		}

		return list;

	}

	// 计算lerun评价的平均分

	public double AverageStar(int lerun_id) throws SQLException {
		double score = 0.0;
		String sql = "select ((select SUM(evaluate_star) from lerunEvaluateTable where lerun_id='"
				+ lerun_id
				+ "')/(select COUNT(evaluate_star) from lerunEvaluateTable where lerun_id='"
				+ lerun_id + "'))";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			score = rs.getDouble(1);
		}

		return score;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}
