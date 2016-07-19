package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.model.VideoTable;
import com.lerun.utils.DBConnection;

/**
 *@Author: wschenyongyin
 *@Date: 2016-7-15
 *@explain:对视频表的操作
 *@TestState: All Success
 */
public class VideoTableDao {
	DBConnection DB = new DBConnection();
	Connection conn = null;

	public VideoTableDao() {
		this.conn = DB.getConnection();
	}

	// 插入视频信息

	public int insertVideo(String video_image, String video_title,
			String video_url) throws SQLException {
		String sql = "insert into videoTable(video_image,video_title,video_url)values('"
				+ video_image + "','" + video_title + "','" + video_url + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 删除视频信息
	public int deleteVideo(int video_id) throws SQLException {
		String sql = "delete from videoTable where video_id='" + video_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	// 查看视频信息
	public List<VideoTable> QueryVideo() throws SQLException {
		String sql = "select * from videoTable";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<VideoTable> list = new ArrayList<VideoTable>();
		while (rs.next()) {
			VideoTable video = new VideoTable();
			video.setVideo_id(rs.getInt("video_id"));
			video.setVideo_image(rs.getString("video_image"));
			video.setVideo_title(rs.getString("video_title"));
			video.setVideo_url(rs.getString("video_url"));

			list.add(video);

		}
		DB.closeAll(rs, st, conn);
		return list;

	}

	// 更新视频信息
	public int updateVideo(int video_id, String update_type,
			String update_values) throws SQLException {
		String sql = "update videoTable set " + update_type + "='"
				+ update_values + "' where video_id='" + video_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		DB.closeAll(null, st, conn);
		return result;
	}

	public static void main(String[] args) throws SQLException {
//		VideoTableDao dao=new VideoTableDao();
////	int result=dao.insertVideo("www.baidu.com", "超越自我", "www.xinlang.com");
//		List<VideoTable> list=dao.QueryVideo();
//		int result=dao.deleteVideo(2);
//		System.out.println("result:"+result);
	}

}
