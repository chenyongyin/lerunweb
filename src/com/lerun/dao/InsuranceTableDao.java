package com.lerun.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.lerun.model.InsuranceTable;
import com.lerun.utils.DBConnection;

/**
 * @Project: LeRun
 * @Author: wschenyongyin
 * @Date: 2016年7月26日
 * @explain:保险配置表
 * @TestState:测试完毕
 */

public class InsuranceTableDao {
	private Connection conn = null;
	private DBConnection DB = new DBConnection();

	public InsuranceTableDao() {
		this.conn = DB.getConnection();
	}

	// 发布新的保险内容
	public int ReleaseInsurance(InsuranceTable info) throws SQLException {
		String sql = "insert into insuranceTable(insurance_name,pay_money,insurance_money,insurancecompany_name)values('"
				+ info.getInsurance_name()
				+ "','"
				+ info.getPay_money()
				+ "','"
				+ info.getInsurance_money()
				+ "','"
				+ info.getInsurancecompany_name() + "')";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		return result;

	}

	// 修改保险内容
	public int updateInsurance(int insurance_id, String update_type,
			String update_values) throws SQLException {

		String sql = "update insuranceTable set "+update_type+"='"+update_values+"' where insurance_id='"+insurance_id+"'";
		Connection conn = DB.getConnection();
		Statement st = conn.createStatement();
		int result = st.executeUpdate(sql);
		return result;
	}

	public static void main(String[] args) throws SQLException {
		InsuranceTableDao dao=new InsuranceTableDao();
//		InsuranceTable info=new InsuranceTable();
//		info.setInsurance_money("10000");
//		info.setPay_money("15");
//		info.setInsurance_name("意外人生保险");
//		info.setInsurancecompany_name("平安保险");
//		int result=dao.ReleaseInsurance(info);
		int result=dao.updateInsurance(2, "pay_money", "10");
		
		System.out.println(result);
	}

}
