package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.model.LeRun;
import com.lerun.model.ShowTable;
import com.lerun.utils.DBConnection;
import com.lerun.utils.JsonTools;

/**
 * 对秀表的操作 测试结果: 全部测试成功
 * 
 * @author wschenyongyin
 * 
 */
public class ShowTableDao {
	Connection conn = null;
	DBConnection DB = new DBConnection();

	public ShowTableDao() {
		this.conn = DB.getConnection();
	}

	// 用户发布show
	public int ReleaseShow(ShowTable show) throws SQLException {
		String sql = "insert into showTable(user_id,show_content,show_image)values('"
				+ show.getUser_id()
				+ "','"
				+ show.getShow_content()
				+ "','"
				+ show.getShow_image() + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 用户删除show
	public int deleteShow(int show_id) throws SQLException {

		String sql = "delete from showTable where show_id='" + show_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 查看所有用户的show

	public List<ShowTable> QueryAllShow() throws SQLException {
		String sql = "select * from showTable";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<ShowTable> list = new ArrayList<ShowTable>();
		LikeTableDao likeDao = new LikeTableDao();
		CommentTableDao commentDao = new CommentTableDao();
		while (rs.next()) {
			ShowTable show = new ShowTable();
			int flag = likeDao.QueryExistUser(rs.getInt("show_id"),
					rs.getString("user_id"));
			int like_num = likeDao.countLikeNumber(rs.getInt("show_id"));
			int comment_num =commentDao.countCommentNumber (rs.getInt("show_id"));
			System.out.println("show_id:"+rs.getInt("show_id"));
			System.out.println("点赞用户：" + like_num + ",评论用户:" + comment_num);
			show.setShow_content(rs.getString("show_content"));
			show.setShow_id(rs.getInt("show_id"));
			show.setShow_image(rs.getString("show_image"));
			show.setUser_id(rs.getString("user_id"));
			show.setLike_state(flag);
			show.setComment_num(comment_num);
			show.setLike_num(like_num);
			list.add(show);

		}

		return list;
	}

	// 查看自己发布的show
	public List<ShowTable> QueryPersonalShow(String user_id)
			throws SQLException {
		LikeTableDao dao = new LikeTableDao();

		String sql = "select * from showTable where user_id='" + user_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<ShowTable> list = new ArrayList<ShowTable>();
		while (rs.next()) {
			ShowTable show = new ShowTable();
			show.setShow_content(rs.getString("show_content"));
			show.setShow_id(rs.getInt("show_id"));
			show.setShow_image(rs.getString("show_image"));
			show.setUser_id(rs.getString("user_id"));

			list.add(show);
		}
		return list;
	}

	// 查询某时间段的show 时间格式为:07 15 2016
	public List<String> QuerySomeTimeShow(String startTime, String endTime)
			throws SQLException {
		String sql = "select show_id from showTable where show_time > '"
				+ startTime + "' and show_time< '" + endTime + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<String> list = new ArrayList<String>();
		while (rs.next()) {
			String user_id = rs.getString("user_id");
			list.add(user_id);

		}
		DB.closeAll(rs, st, conn);
		return list;
	}

	// 先判断视图是否存在
	public int QueryViewExist(String user_id) throws SQLException {
		int flag = 0;
		String sql = "SELECT * FROM sys.views WHERE name='viewshow_" + user_id
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
		String sql = "create view viewshow_"
				+ user_id
				+ " as select show_id ,user_id,show_content,show_image,show_time from showTable where user_id='"
				+ user_id + "'";
		Connection conn = DB.getConnection();

		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 查询视图
	public List<ShowTable> QueryShowView(String user_id) throws SQLException {
		List<ShowTable> list = new ArrayList<ShowTable>();
		String sql = "select * from viewshow_" + user_id + "";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			ShowTable show = new ShowTable();

			show.setShow_content(rs.getString("show_content"));
			show.setShow_id(Integer.parseInt(rs.getString("show_id")));
			show.setShow_image(rs.getString("show_image"));
			show.setShow_time(rs.getString("show_time"));
			show.setUser_id(rs.getString("user_id"));

			list.add(show);

		}
		DB.closeAll(rs, st, conn);
		return list;

	}

	public static void main(String[] args) throws SQLException {
		ShowTableDao dao = new ShowTableDao();
		ShowTable show = new ShowTable();
		// show.setUser_id("123");
		// show.setShow_content("kaixin");
		// show.setShow_image("wwww.baidu.com");
//		List<ShowTable> list = dao.QueryAllShow();
		// List<ShowTable> list=dao.QueryPersonalShow("123");
		// System.out.println("数据条数为:"+list.size());
		// int result=dao.deleteShow(1002);
		// System.out.println("result"+result);
		// int result=dao.createView("123");
		// List<ShowTable> list = dao.QueryShowView("1234");
//		System.out.println("result" + list.size());
//		String result = JsonTools.createJsonString("sss", list);

	}

}
