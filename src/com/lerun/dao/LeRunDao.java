package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lerun.bean.ResponseObject;
import com.lerun.model.LeRun;
import com.lerun.utils.DBConnection;
import com.lerun.utils.GsonTools;

public class LeRunDao {

	Connection conn = null;
	DBConnection DB = new DBConnection();

	public LeRunDao() {
		this.conn = DB.getConnection();
	}

	// 发布活动
	public int Release(LeRun lerun) throws SQLException {
		String sql = "insert into lerunTable(lerun_title,lerun_content,lerun_poster,lerun_time,lerun_map,lerun_routine,lerun_host,charge_id,lerun_process,lerun_ruler,lerun_state,lerun_type,lerun_dimage,lerun_address,lerun_city,lerun_sponsor,lerun_maxuser,lerun_video,lerun_begintime,lerun_endtime,freecharge_number,charge_mode,insurance_id,lerun_agent,lerun_province)values('"
				+ lerun.getLerun_title()
				+ "','"
				+ lerun.getLerun_content()
				+ "','"
				+ lerun.getLerun_poster()
				+ "','"
				+ lerun.getLerun_time()
				+ "','"
				+ lerun.getLerun_map()
				+ "','"
				+ lerun.getLerun_routine()
				+ "','"
				+ lerun.getLerun_host()
				+ "','"
				+ lerun.getCharge_id()
				+ "','"
				+ lerun.getLerun_process()
				+ "','"
				+ lerun.getLerun_ruler()
				+ "','"
				+ lerun.getLerun_state()
				+ "','"
				+ lerun.getLerun_type()
				+ "','"
				+ lerun.getLerun_dimage()
				+ "','"
				+ lerun.getLerun_address()
				+ "','"
				+ lerun.getLerun_city()
				+ "','"
				+ lerun.getLerun_sponsor()
				+ "','"
				+ lerun.getLerun_maxuser()
				+ "','"
				+ lerun.getLerun_video()
				+ "','"
				+ lerun.getLerun_begintime()
				+ "','"
				+ lerun.getLerun_endtime()
				+ "','"
				+ lerun.getFreecharge_number()
				+ "','"
				+ lerun.getCharge_mode()
				+ "','"
				+ lerun.getInsurance_id()
				+ "','"
				+ lerun.getLerun_agent()
				+ "','"
				+ lerun.getLerun_province()
				+ "')";

		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);

		return result;
	}

	// 获取全部活动
	public List<LeRun> QueryAllLerun() throws SQLException {
		String sql = "select * from lerunTable order by lerun_time desc";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LeRun> list = new ArrayList<LeRun>();
		List<LeRun> list2 = new ArrayList<LeRun>();
		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(Integer.parseInt(rs.getString("lerun_state")));
			lerun.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			lerun.setLerun_host(rs.getString("lerun_host"));
			lerun.setLerun_province(rs.getString("lerun_province"));
			lerun.setLerun_city(rs.getString("lerun_city"));
			lerun.setLerun_agent(rs.getString("lerun_agent"));
			lerun.setLerun_likenum(rs.getInt("lerun_likenum"));
			lerun.setLerun_browsenum(rs.getInt("lerun_browsenum"));

			list.add(lerun);

		}
		DB.closeAll(rs, st, conn);
		return list;
	}

	// 获取当前省的活动
	public List<LeRun> getProvinceLerun(String province_name)
			throws SQLException {
		String sql = "select * from lerunTable where lerun_province like '"
				+ province_name + "%' order by lerun_time desc";
		System.out.println("sql:" + sql);
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LeRun> list = new ArrayList<LeRun>();
		List<LeRun> list2 = new ArrayList<LeRun>();
		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(Integer.parseInt(rs.getString("lerun_state")));
			lerun.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			lerun.setLerun_host(rs.getString("lerun_host"));
			lerun.setLerun_province(rs.getString("lerun_province"));
			lerun.setLerun_city(rs.getString("lerun_city"));
			lerun.setLerun_agent(rs.getString("lerun_agent"));
			lerun.setLerun_likenum(rs.getInt("lerun_likenum"));
			lerun.setLerun_browsenum(rs.getInt("lerun_browsenum"));

			list.add(lerun);

		}
		DB.closeAll(rs, st, conn);
		System.out.println(list.size());
		return list;

	}

	// 获其他省省的活动
	public List<LeRun> getOhterProvinceLerun(String province_name)
			throws SQLException {
		String sql = "select * from lerunTable where lerun_province not like'"
				+ province_name + "%' order by lerun_time desc";
		System.out.println("sql2:" + sql);
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LeRun> list = new ArrayList<LeRun>();
		List<LeRun> list2 = new ArrayList<LeRun>();
		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(Integer.parseInt(rs.getString("lerun_state")));
			lerun.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			lerun.setLerun_host(rs.getString("lerun_host"));
			lerun.setLerun_province(rs.getString("lerun_province"));
			lerun.setLerun_city(rs.getString("lerun_city"));
			lerun.setLerun_agent(rs.getString("lerun_agent"));
			lerun.setLerun_likenum(rs.getInt("lerun_likenum"));
			lerun.setLerun_browsenum(rs.getInt("lerun_browsenum"));

			list.add(lerun);

		}
		DB.closeAll(rs, st, conn);
		return list;

	}

	// 将当前省份活动排在前面 其他活动往后排
	public List<LeRun> getLerun(String lerun_province) throws SQLException {

		List<LeRun> list = getProvinceLerun(lerun_province);
		List<LeRun> Ohterlist = getOhterProvinceLerun(lerun_province);

		for (int i = 0; i < Ohterlist.size(); i++) {
			LeRun bean = Ohterlist.get(i);
			list.add(bean);

		}

		return list;

	}

	// 查看所有未结束的活动内容
	public List<LeRun> QueryStartLerun() throws SQLException {
		String sql = "select * from lerunTable where lerun_state!='3' order by lerun_time desc";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LeRun> list = new ArrayList<LeRun>();
		List<LeRun> list2 = new ArrayList<LeRun>();
		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(Integer.parseInt(rs.getString("lerun_state")));
			lerun.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			lerun.setLerun_host(rs.getString("lerun_host"));

			list.add(lerun);
			return list;
		}
		DB.closeAll(rs, st, conn);
		return null;
	}

	// 查询单条活动的详细信息
	public LeRun QueryDetailLerun(int lerun_id) throws SQLException {
		String sql = "select * from lerunTable,chargeTable,insuranceTable where lerun_id='"
				+ lerun_id
				+ "' and lerunTable.charge_id=chargeTable.charge_id and insuranceTable.insurance_id=lerunTable.insurance_id";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			LeRun lerun = new LeRun();
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_begintime(rs.getString("lerun_begintime"));
			lerun.setCharge_id(Integer.parseInt(rs.getString("charge_id")));
			lerun.setLerun_city(rs.getString("lerun_city"));
			lerun.setLerun_content(rs.getString("lerun_content"));
			lerun.setLerun_dimage(rs.getString("lerun_dimage"));
			lerun.setLerun_endtime(rs.getString("lerun_endtime"));

			lerun.setLerun_host(rs.getString("lerun_host"));
			lerun.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));
			lerun.setLerun_map(rs.getString("lerun_map"));
			lerun.setLerun_maxuser(Integer.parseInt(rs
					.getString("lerun_maxuser")));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_process(rs.getString("lerun_process"));
			lerun.setLerun_routine(rs.getString("lerun_routine"));
			lerun.setLerun_ruler(rs.getString("lerun_ruler"));
			lerun.setLerun_sponsor(rs.getString("lerun_sponsor"));
			lerun.setLerun_state(Integer.parseInt(rs.getString("lerun_state")));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_type(rs.getString("lerun_type"));
			lerun.setLerun_video(rs.getString("lerun_video"));
			lerun.setFreecharge_number(Integer.parseInt(rs
					.getString("freecharge_number")));
			lerun.setCharge_mode(Integer.parseInt(rs.getString("charge_mode")));
			lerun.setInsurance_id(Integer.parseInt(rs.getString("insurance_id")));
			lerun.setCharge_free(Integer.parseInt(rs.getString("charge_free")));
			lerun.setCharge_common(Integer.parseInt(rs
					.getString("charge_common")));
			lerun.setCharge_vip(Integer.parseInt(rs.getString("charge_vip")));
			lerun.setFree_equipment(rs.getString("free_equipment"));
			lerun.setCommon_equipment(rs.getString("common_equipment"));
			lerun.setVip_equipment(rs.getString("vip_equipment"));
			lerun.setLerun_agent(rs.getString("lerun_agent"));
			lerun.setLerun_province(rs.getString("lerun_province"));

			lerun.setLerun_surplus(getSurplus(lerun_id));
			lerun.setLerun_freesurplus(getFreeSurplus(lerun_id));

			return lerun;
		}
		DB.closeAll(rs, st, conn);
		return null;
	}

	// 查看结束的活动
	public List<LeRun> QueryEndLerun() throws SQLException {
		String sql = "select * from lerunTable where lerun_state='3'  order by lerun_time desc";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LeRun> list = new ArrayList<LeRun>();
		List<LeRun> list2 = new ArrayList<LeRun>();
		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(Integer.parseInt(rs.getString("lerun_state")));
			lerun.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));

			list.add(lerun);
			return list;
		}
		DB.closeAll(rs, st, conn);
		return null;
	}

	// 根据主题查看结束的乐跑活动
	public List<LeRun> QueryHistoryLerun(int lerun_theme) throws SQLException {
		String sql = "select * from lerunTable where lerun_state='3' and lerun_theme='"
				+ lerun_theme + "' order by lerun_time desc";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		List<LeRun> list = new ArrayList<LeRun>();
		List<LeRun> list2 = new ArrayList<LeRun>();
		while (rs.next()) {
			LeRun lerun = new LeRun();

			lerun.setLerun_title(rs.getString("lerun_title"));
			lerun.setLerun_time(rs.getString("lerun_time"));
			lerun.setLerun_address(rs.getString("lerun_address"));
			lerun.setLerun_poster(rs.getString("lerun_poster"));
			lerun.setLerun_state(Integer.parseInt(rs.getString("lerun_state")));
			lerun.setLerun_id(Integer.parseInt(rs.getString("lerun_id")));

			list.add(lerun);
			return list;
		}
		DB.closeAll(rs, st, conn);
		return null;
	}

	// 历史回顾页面详情信息
	public LeRun HistoryDetail(int lerun_id) throws SQLException {
		LeRunEvaluateTableDao dao = new LeRunEvaluateTableDao();
		String sql = "select lerun_title,lerun_time,lerun_content,lerun_poster from lerunTable where lerun_id='"
				+ lerun_id + "'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			LeRun bean = new LeRun();
			bean.setAverageStar(dao.AverageStar(lerun_id)+"");
			bean.setLerun_content(rs.getString("lerun_content"));
			bean.setLerun_poster(rs.getString("lerun_poster"));
			bean.setLerun_time(rs.getString("lerun_time"));
			bean.setLerun_title(rs.getString("lerun_title"));
			return bean;
		}
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

	// 获取报名剩余名额
	public int getSurplus(int lerun_id) throws SQLException {
		String sql = "select (select lerun_maxuser from lerunTable where lerun_id='"
				+ lerun_id
				+ "')-(select count(lerun_id)from orderTable where lerun_id='"
				+ lerun_id + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(sql);
		while (rs.first()) {
			int result = rs.getInt(1);
			System.out.println("报名人数" + result);
			return result;
		}
		DB.closeAll(null, st, conn);
		return 0;
	}

	// 统计剩余的免费名额
	public int getFreeSurplus(int lerun_id) throws SQLException {
		String sql = "select (select freecharge_number from lerunTable where lerun_id='"
				+ lerun_id
				+ "')-(select count(lerun_id)from orderTable where lerun_id='"
				+ lerun_id + "' and signin_type='1')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(sql);
		while (rs.first()) {
			int result = rs.getInt(1);
			System.out.println("报名人数" + result);
			return result;
		}
		DB.closeAll(null, st, conn);
		return 0;
	}

	// 获取lerun的点赞量
	public int getLerunlikeNum(int lerun_id) throws SQLException {
		int like_num = 0;
		String sql = "select lerun_likenum from lerunTable where lerun_id='"
				+ lerun_id + "'";
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			like_num = rs.getInt(1);
		}
		return like_num;

	}

	// 获取lerun的浏览量
	public int getLerunBrowseBum(int lerun_id) throws SQLException {
		int browse_num = 0;
		String sql = "select lerun_browsenum from lerunTable where lerun_id='"
				+ lerun_id + "'";
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			browse_num = rs.getInt(1);
		}
		return browse_num;

	}

	// //用户点赞lerun
	//
	// public int likeLerun(int lerun_id ){
	//
	//
	//
	// return lerun_id;}
	//
	// //更新浏览量

	public static void main(String[] args) throws SQLException {
		// LeRunDao dao = new LeRunDao();
		// String result = dao.getLerun("江西");
		// System.out.println(result);
	}

}
