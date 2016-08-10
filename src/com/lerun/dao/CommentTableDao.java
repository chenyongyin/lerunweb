package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.bean.ResponseObject;
import com.lerun.model.commentTable;
import com.lerun.utils.DBConnection;
import com.lerun.utils.GsonTools;

/**
 *@Author: wschenyongyin
 *@Date: 2016-7-15
 *@explain:对评论表的操作
 *@TestState:
 */
public class CommentTableDao {
	DBConnection DB = new DBConnection();
	Connection conn = null;

	public CommentTableDao() {
		this.conn = DB.getConnection();
	}

	// 用户评论
	public int Releasecomment(int show_id, String user_id,
			String comment_content, String comment_userid) throws SQLException {
		String sql = "insert into commentTable(show_id,user_id,comment_content,comment_userid)values('"
				+ show_id
				+ "','"
				+ user_id
				+ "','"
				+ comment_content
				+ "','"
				+ comment_userid + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);

		return result;
	}

	// 删除评论
	public int deleteComment(int comment_id) throws SQLException {
		String sql = "delete from commentTable where comment_id='" + comment_id
				+ "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 查看show评论
	public List<commentTable> QueryComment(int show_id) throws SQLException {
		String sql = "select comment_content,comment_id,comment_time,comment_userid,userTable.user_id,user_header,user_name from userTable,commentTable where userTable.user_id=commentTable.user_id and show_id='"+show_id+"' order by comment_id desc";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<commentTable> list = new ArrayList<commentTable>();
		while (rs.next()) {
			commentTable bean = new commentTable();
			bean.setComment_content(rs.getString("comment_content"));
			bean.setComment_id(Integer.parseInt(rs.getString("comment_id")));
			bean.setComment_time(rs.getString("comment_time"));
			bean.setComment_userid(rs.getString("comment_userid"));// 被评论者的id
			bean.setUser_id(rs.getString("user_id"));// 评论者的id
			bean.setUser_header(rs.getString("user_header"));
			bean.setUser_name(rs.getString("user_name"));
			bean.setShow_id(show_id);
			
			
			System.out.println("用户头像"+bean.getUser_header());
			list.add(bean);
		}
		DB.closeAll(rs, st, conn);
		return list;
	}

	
	//统计show的评论数量
	public int countCommentNumber(int show_id) throws SQLException{
		String sql = "select count(user_id) from commentTable where show_id='"
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
		CommentTableDao dao = new CommentTableDao();
//		int result = dao.Releasecomment(999, "1234", "康康是个小煞笔", "123");
		// int result =dao.deleteComment(2);
		List<commentTable> list=dao.QueryComment(1000);
		ResponseObject object=new ResponseObject(1, list);
		String result=GsonTools.createJsonString(object);
		System.out.println("result:" + result +"list大小："+list.size());

	}

}
