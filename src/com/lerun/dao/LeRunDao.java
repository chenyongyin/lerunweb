package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.model.LeRun;
import com.lerun.utils.DBConnection;

public class LeRunDao {

	Connection conn = null;
	DBConnection DB = new DBConnection();

	public LeRunDao() {
		this.conn = DB.getConnection();
	}

	// 发布活动
	public int Release(LeRun lerun) throws SQLException {
		String sql = "insert into lerunTable(lerun_title,lerun_content,lerun_poster,lerun_time,lerun_map,lerun_routine,lerun_host,lerun_charge,lerun_process,lerun_ruler,lerun_state,lerun_type,lerun_dimage,lerun_address,lerun_city,lerun_sponsor,lerun_maxuser,lerun_video,lerun_begintime,lerun_endtime)values("
				+ lerun.getLerun_title()
				+ ","
				+ lerun.getLerun_content()
				+ ","
				+ lerun.getLerun_poster()
				+ ","
				+ lerun.getLerun_time()
				+ ","
				+ lerun.getLerun_map()
				+ ","
				+ lerun.getLerun_routine()
				+ ","
				+ lerun.getLerun_host()
				+ ","
				+ lerun.getLerun_charge()
				+ ","
				+ lerun.getLerun_process()
				+ ","
				+ lerun.getLerun_ruler()
				+ ","
				+ lerun.getLerun_state()
				+ ","
				+ lerun.getLerun_type()
				+ ","
				+ lerun.getLerun_dimage()
				+ ","
				+ lerun.getLerun_address()
				+ ","
				+ lerun.getLerun_city()
				+ ","
				+ lerun.getLerun_sponsor()
				+ ","
				+ lerun.getLerun_maxuser()
				+ ","
				+ lerun.getLerun_video()
				+ ","
				+ lerun.getLerun_begintime()
				+ ","
				+ lerun.getLerun_endtime() + ")";

		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);

		return result;
	}

	// 查看所有未结束的活动内容
	public List<LeRun> QueryAllLerun() throws SQLException {
		String sql = "select * from lerunTable where lerun_state!='3' order by lerun_id desc";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LeRun> list = new ArrayList<LeRun>();
		List<LeRun> list2=new ArrayList<LeRun>();
		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(rs.getString("lerun_state"));
			lerun.setLerun_id(rs.getString("lerun_id"));

			list.add(lerun);
			return list;
		}
		DB.closeAll(rs, st, conn);
		return null;
	}

	// 查询单条活动的详细信息
	public LeRun QueryDetailLerun(int lerun_id) throws SQLException {
		String sql = "select * from lerunTable where lerun_id='" + lerun_id
				+ "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			LeRun lerun = new LeRun();
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_begintime(rs.getString("lerun_begintime"));
			lerun.setLerun_charge(rs.getString("lerun_charge"));
			lerun.setLerun_city(rs.getString("lerun_city"));
			lerun.setLerun_content(rs.getString("lerun_content"));
			lerun.setLerun_dimage(rs.getString("lerun_dimage"));
			lerun.setLerun_endtime(rs.getString("lerun_endtime"));
			lerun.setLerun_endtime(rs.getString("lerun_endtime"));
			lerun.setLerun_host(rs.getString("lerun_host"));
			lerun.setLerun_id(rs.getString("lerun_id"));
			lerun.setLerun_map(rs.getString("lerun_map"));
			lerun.setLerun_maxuser(rs.getString("lerun_maxuser"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_process(rs.getString("lerun_process"));
			lerun.setLerun_routine(rs.getString("lerun_routine"));
			lerun.setLerun_ruler(rs.getString("lerun_ruler"));
			lerun.setLerun_sponsor(rs.getString("lerun_sponsor"));
			lerun.setLerun_state(rs.getString("lerun_state"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_type(rs.getString("lerun_type"));
			lerun.setLerun_video(rs.getString("lerun_video"));
			return lerun;
		}
		DB.closeAll(rs, st, conn);
		return null;
	}
	
	
	
	//查看结束的活动
	public List<LeRun> QueryEndLerun() throws SQLException {
		String sql = "select * from lerunTable where lerun_state='3' order by lerun_id desc";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LeRun> list = new ArrayList<LeRun>();
		List<LeRun> list2=new ArrayList<LeRun>();
		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(rs.getString("lerun_state"));
			lerun.setLerun_id(rs.getString("lerun_id"));

			list.add(lerun);
			return list;
		}
		DB.closeAll(rs, st, conn);
		return null;
	}
	
	

	// 修改活动信息

	public int updateLeRun(int lerun_id, String update_type,
			String update_values) throws SQLException {
		String sql = "update lerunTable set " + update_type + "='"
				+ update_values + "' where lerun_id='" + lerun_id + "'";

		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);

		DB.closeAll(null, st, conn);
		return result;
	}

	public static void main(String[] args) {

	}

}
