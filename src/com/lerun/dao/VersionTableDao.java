package com.lerun.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lerun.model.VersionTable;
import com.lerun.utils.DBConnection;

/**
 * @Project: LeRun
 * @Author: wschenyongyin
 * @Date: 2016年8月5日
 * @explain:
 * @TestState:
 */

public class VersionTableDao {
	Connection conn = null;
	DBConnection DB = new DBConnection();

	public VersionTableDao() {
		this.conn = DB.getConnection();
	}

	// 检查版本信息
	public int checkversion(String version_num) throws SQLException {
		int result = 0;
		String version = "";
		String sql = "select version_number from versioninfoTable order by version_id desc";
		Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			version = rs.getString("version_number");
			System.out.println("最新的版本为:" + version);
			if (version.endsWith(version_num)) {
				result = 1;
			}
			break;
		}

		
		return result;
	}

	// 获取新的版本内容
	public VersionTable getVersion(String version_num) throws SQLException {
		VersionTableDao dao = new VersionTableDao();
		int flag = dao.checkversion(version_num);
		VersionTable bean = new VersionTable();
		if (flag == 1) {
			bean = null;
		} else {
			String sql = "select * from versioninfoTable order by version_id desc";
			Statement st = conn
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				bean.setUpdate_content(rs.getString("update_content"));
				bean.setUpdate_time(rs.getString("update_time"));
				bean.setVersion_id(rs.getInt("version_id"));
				bean.setUpdate_url(rs.getString("update_url"));
				bean.setVersion_number(rs.getString("version_number"));
				bean.setUpdate_size(rs.getString("update_size"));

				System.out.println(bean.getVersion_number());
				break;
			}

		}

		return bean;
	}

	// 发布新的版本
	public int ReleaseVersion(String version_number, String update_content,
			String update_url) throws SQLException {
		String sql = "insert into versioninfoTable(version_number,update_content,update_url)values('"
				+ version_number
				+ "','"
				+ update_content
				+ "','"
				+ update_url
				+ "')";
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);

		return result;
	}

	public static void main(String args[]) throws SQLException {
		VersionTableDao tableDao = new VersionTableDao();
		tableDao.getVersion("v1.0");
	}

}
