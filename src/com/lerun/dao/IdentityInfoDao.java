package com.lerun.dao;

import java.sql.Connection;

import com.lerun.model.IdentityInfoTable;
import com.lerun.utils.DBConnection;

/**
 *@Project: LeRun
 *@Author: wschenyongyin
 *@Date: 2016年7月25日
 *@explain:
 *@TestState:
 */

public class IdentityInfoDao {
	
	Connection conn=null;
	DBConnection DB=new DBConnection();
	public IdentityInfoDao(){
		this.conn=DB.getConnection();
	}
	
	//学生认证
	public int StudentCertification(IdentityInfoTable info){
		String sql="insert into ";
		
		
		return 0;}
	
	

}
