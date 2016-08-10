package com.lerun.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.lerun.model.ChargeTable;
import com.lerun.model.InsuranceTable;
import com.lerun.utils.DBConnection;

/**
 * @Project: LeRun
 * @Author: wschenyongyin
 * @Date: 2016年7月26日
 * @explain:活动收费表
 * @TestState:测试完毕
 */

public class ChargeTableDao {

	private Connection conn = null;
	private DBConnection DB = new DBConnection();

	public ChargeTableDao() {
		this.conn = DB.getConnection();
	}

	// 发布新的保险内容
	public int ReleaseCharge(ChargeTable info) throws SQLException {
		String sql = "insert into chargeTable(charge_free,charge_common,charge_vip,free_equipment,common_equipment,vip_eqeuipment)values('"
				+ info.getCharge_free()
				+ "','"
				+ info.getCharge_common()
				+ "','"
				+ info.getCharge_vip()
				+ "','"
				+ info.getFree_equipment()
				+ "','"
				+ info.getCommon_equipment()
				+ "','" + info.getVip_eqeuipment() + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		return result;

	}

	// 修改保险内容
	public int updateCharge(int charge_id, String update_type,
			String update_values) throws SQLException {

		String sql = "update chargeTable set " + update_type + "='"
				+ update_values + "' where insurance_id='" + charge_id + "'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
