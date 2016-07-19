package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.model.LunboTable;
import com.lerun.model.VideoTable;
import com.lerun.utils.DBConnection;

/**
 *@Author: wschenyongyin
 *@Date: 2016-7-15
 *@explain:
 *@TestState:
 */
public class LunboTableDao {

	DBConnection DB = new DBConnection();
	Connection conn = null;

	public LunboTableDao() {
		this.conn = DB.getConnection();
	}

	// 插入轮播信息

	public int insertLunbo(String lunbo_image, String lunbo_title,
			String lunbo_url) throws SQLException {
		String sql = "insert into lunboTable(lunbo_image,lunbo_title,lunbo_url)values('"
				+ lunbo_image + "','" + lunbo_title + "','" + lunbo_url + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 删除轮播信息
	public int deleteLunbo(int lunbo_id) throws SQLException {
		String sql = "delete from videoTable where video_id='" + lunbo_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 查看轮播信息
	public List<LunboTable> QueryLunbo() throws SQLException {
		String sql = "select * from lunboTable";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LunboTable> list = new ArrayList<LunboTable>();
		while (rs.next()) {
			LunboTable lunbo = new LunboTable();
			lunbo.setLunbo_id(rs.getInt("lunbo_id"));
			lunbo.setLunbo_image(rs.getString("lunbo_image"));
			lunbo.setLunbo_title(rs.getString("lunbo_title"));
			lunbo.setLunbo_url(rs.getString("lunbo_url"));

			list.add(lunbo);

		}
		DB.closeAll(rs, st, conn);
		return list;

	}

	// 更新轮播信息
	public int updateLunbo(int lunbo_id, String update_type,
			String update_values) throws SQLException {
		String sql = "update lunboTable set " + update_type + "='"
				+ update_values + "' where lunbo_id='" + lunbo_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	public static void main(String[] args) throws SQLException {
		LunboTableDao dao = new LunboTableDao();
		dao.insertLunbo("www.baidu.com", "天天特价", "kangkang.com");

	}

}
